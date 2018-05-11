package com.zbsjk.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
			String s = QRCodeUtil.encode(text, path);
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
		
		String strBackUrl = "http://" + request.getServerName() + ":"
				+ request.getServerPort()
				+ request.getContextPath();
				
		String text=strBackUrl+"/equipment/"+equipmentId;
		String path = request.getSession().getServletContext().getRealPath("/")+"Report/";
		String s = QRCodeUtil.encode(text, path);
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
	
}
