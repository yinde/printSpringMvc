package com.zbsjk.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbsjk.ext.ParamException;
import com.zbsjk.ext.SecurityException;
import com.zbsjk.model.dao.EquipmentInfoMapper;
import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.entity.UserInfo;
import com.zbsjk.model.vo.EquipmentListVo;
import com.zbsjk.service.EquipmentService;
import com.zbsjk.util.ExcelUtil;
import com.zbsjk.util.QRCodeUtil;

@RestController
public class EquipmentContoller {

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private EquipmentInfoMapper equipmentInfoMapper;
	
	@RequestMapping(value ="/equipment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addEquipment(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EquipmentInfo equipmentInfo) throws Exception{
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		equipmentInfo.setCreateUser(user.getUserId());
		Object object = equipmentService.addEquipment(equipmentInfo);
		
		if(equipmentInfo.getRescueStatus().equals(1)){
			//新增应急设备
			String strBackUrl = "http://" + request.getServerName() + ":"
					+ request.getServerPort()
					+ request.getContextPath();
					
			String text=strBackUrl+"/jsp/sjk/qrcode.html?equipmentNumber="+equipmentInfo.getEquipmentNumber()+"&userName="+equipmentInfo.getUserName();
			String path = request.getSession().getServletContext().getRealPath("/")+"Report/";
			String logoPath = request.getSession().getServletContext().getRealPath("/")+"jsp/1.png";
			String s = QRCodeUtil.encode(text, logoPath,path,true);
			equipmentInfo.setQrCode(s);
			equipmentInfo.setQrCodePath(strBackUrl+"/Report/"+s);
			equipmentInfo.setAuditStatus(1);
			equipmentInfo.setPayStatus(1);
			equipmentInfo.setRescueStatus(1);
			equipmentService.updateByPrimaryKeySelective(equipmentInfo);
		}
		return object;
	}
	
	@RequestMapping(value ="/equipment/{equipmentId}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateEquipment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("equipmentId") Integer equipmentId,
			@RequestBody EquipmentInfo equipmentInfo){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		equipmentInfo.setEquipmentId(equipmentId);
		return equipmentService.updateEquipment(equipmentInfo);
	}
	
	@RequestMapping(value ="/equipment/equipmentInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object queryEquipment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=false) String equipmentNumber,
			@RequestParam(required=false) String userName){
		return equipmentService.queryEquipment(equipmentNumber,userName);
	}
	
	@RequestMapping(value ="/equipment/{equipmentId}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	public Object deleteEquipment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("equipmentId") Integer equipmentId){
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		if(!user.getRoleId().equals(1) && !user.getRoleId().equals(2) && !user.getRoleId().equals(3)){
			throw new SecurityException("user", "只有管理员能够删除");
		}
		
		EquipmentInfo ei = equipmentInfoMapper.selectByPrimaryKey(equipmentId);
		if(null==ei){
			throw new ParamException("equipmentNumber", "设备不存在");
		}
		
		String fileName = ei.getQrCode();
		
		int count = (int)equipmentService.deleteEquipment(equipmentId,user.getUserId());
		
		String path = request.getSession().getServletContext().getRealPath("/")+"Report/";
		
		File file = new File(path+fileName);
		if (file.exists()) {
		    file.delete();
		}
		
		return count;
	}
	
	@RequestMapping(value ="/equipment", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object queryEquipmentList(HttpServletRequest request, HttpServletResponse response,
			EquipmentListVo equipmentListVo,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		if(user.getRoleId().equals(2) || user.getRoleId().equals(4) || user.getRoleId().equals(6)){
			equipmentListVo.setEquipmentCity(user.getUserCity());
		}
		if(user.getRoleId().equals(3) || user.getRoleId().equals(5) || user.getRoleId().equals(7)){
			equipmentListVo.setEquipmentCity(user.getUserCity());
			equipmentListVo.setEquipmentArea(user.getUserArea());
		}
		return equipmentService.queryEquipmentList(equipmentListVo,pageNo,pageSize);
	}
	
	/**
	 * 修改审核状态接口
	 * @param request
	 * @param response
	 * @param equipmentId
	 * @param equipmentListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/equipment/{equipmentId}/auditstatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateAuditstatus(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("equipmentId") Integer equipmentId,
			@RequestBody EquipmentListVo equipmentListVo) throws Exception{
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		if(!user.getRoleId().equals(1) && !user.getRoleId().equals(2)
				&& !user.getRoleId().equals(3)
				&& !user.getRoleId().equals(4)
				&& !user.getRoleId().equals(5)){
			throw new SecurityException("user", "只有管理员和审核员能审核");
		}
		
		EquipmentInfo equipmentInfo = new EquipmentInfo();
		
		equipmentInfo.setEquipmentId(equipmentId);
		equipmentInfo.setAuditStatus(equipmentListVo.getAuditStatus());
		
		EquipmentInfo ei = equipmentService.selectByPrimaryKey(equipmentId);
		
		String strBackUrl = "http://" + request.getServerName() + ":"
				+ request.getServerPort()
				+ request.getContextPath();
		String text=strBackUrl+"/jsp/sjk/qrcode.html?equipmentNumber="+ei.getEquipmentNumber()+"&userName="+ei.getUserName();
		String path = request.getSession().getServletContext().getRealPath("/")+"Report/";
		String logoPath = request.getSession().getServletContext().getRealPath("/")+"jsp/1.png";
		String s = QRCodeUtil.encode(text, logoPath,path,true);
		equipmentInfo.setQrCode(s);
		equipmentInfo.setQrCodePath(strBackUrl+"/Report/"+s);
		equipmentInfo.setAuditAuditor(user.getUserId());
		equipmentInfo.setAuditTime(new Date());
		return equipmentService.updateByPrimaryKeySelective(equipmentInfo);
	}
	
	/**
	 * 设置应急状态接口
	 * @param request
	 * @param response
	 * @param equipmentId
	 * @param equipmentListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/equipment/{equipmentId}/rescuestatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateRescuestatus(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("equipmentId") Integer equipmentId,
			@RequestBody EquipmentListVo equipmentListVo) throws Exception{
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		EquipmentInfo equipmentInfo = new EquipmentInfo();
		equipmentInfo.setEquipmentId(equipmentId);
		equipmentInfo.setRescueStatus(equipmentListVo.getRescueStatus());
		
		return equipmentService.updateByPrimaryKeySelective(equipmentInfo);
	}
	
	/**
	 * 设置缴费状态接口
	 * @param request
	 * @param response
	 * @param equipmentId
	 * @param equipmentListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/equipment/{equipmentId}/paystatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updatePayStatus(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("equipmentId") Integer equipmentId,
			@RequestBody EquipmentListVo equipmentListVo) throws Exception{
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		EquipmentInfo equipmentInfo = new EquipmentInfo();
		equipmentInfo.setEquipmentId(equipmentId);
		equipmentInfo.setPayStatus(equipmentListVo.getPayStatus());
		
		return equipmentService.updateByPrimaryKeySelective(equipmentInfo);
	}
	
	/**
	 * 设备导出
	 * @param request
	 * @param response
	 * @param equipmentListVo
	 * @return
	 */
	@RequestMapping(value="/equipment/download",method = RequestMethod.GET,produces = {"application/vnd.ms-excel;charset=UTF-8"})
    public String download(HttpServletRequest request,HttpServletResponse response,EquipmentListVo equipmentListVo){
        try {
        	UserInfo user = (UserInfo)request.getSession().getAttribute("user");
    		if(null==user){
    			throw new SecurityException("user", "请先登录");
    		}
    		
    		if(user.getRoleId().equals(2) || user.getRoleId().equals(4) || user.getRoleId().equals(6)){
    			equipmentListVo.setEquipmentCity(user.getUserCity());
    		}
    		if(user.getRoleId().equals(3) || user.getRoleId().equals(5) || user.getRoleId().equals(7)){
    			equipmentListVo.setEquipmentCity(user.getUserCity());
    			equipmentListVo.setEquipmentArea(user.getUserArea());
    		}
    		
    		List<EquipmentInfo> equipmentList = equipmentInfoMapper.queryByProperties(equipmentListVo);
            String fileName="设备基本信息表";
            List<Map<String,Object>> list= equipmentService.createExcelRecord(equipmentList);
			String columnNames[] = { "设备编号", "登记编号", "登记单位", "姓名", "身份证", "单位名称", "信用代码", "地址", "联系电话", "市", "区",
					"设备品牌", "设备类型", "设备型号", "整机编号", "发动机编号", "发动机功率", "铲斗容量", "整机质量", "轮胎", "履带", "保险机构", "保险险种", "长",
					"宽", "高" , "购买方式", "燃料品种", "燃料来源", "污染物", "噪音检测", "排放阶段", "尾气排放", "购买时间", "耗油量"};// 列名
			String keys[] = { "equipmentNumber", "registerNumber", "registerCompany", "userName", "userIdCard",
					"companyName", "creditCode", "userAddress", "userPhone",  "equipmentCity",
					"equipmentArea", "equipmentBrand", "equipmentType", "equipmentModel", "machineNumber",
					"engineNumber", "enginePower", "bucketCapacity", "machineQuality", "equipmentTyre",
					"equipmentTrack","insuranceAgency","insuranceInsurance","equipmentLong","equipmentWide","equipmentHight","purchaseMethod","fuelVariety","fuelSource","equipmentContaminants","noiseDetection","dischargeStage","exhaustEmission","purchaseTime","fuelConsumption"};// map中的key
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
        }catch (Exception e){

        }
        return null;
    }
}
