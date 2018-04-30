package com.zbsjk.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zbsjk.util.QRCodeUtil;

@RestController
public class EquipmentContoller {

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private EquipmentInfoMapper equipmentInfoMapper;
	
	@RequestMapping(value ="/equipment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addEquipment(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EquipmentInfo equipmentInfo){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		return equipmentService.addEquipment(equipmentInfo);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateEquipment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber,
			@RequestBody EquipmentInfo equipmentInfo){
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		equipmentInfo.setEquipmentNumber(equipmentNumber);
		return equipmentService.updateEquipment(equipmentInfo);
	}
	
	@RequestMapping(value ="/equipment/equipmentInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object queryEquipment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String equipmentNumber,
			@RequestParam String userName){
		return equipmentService.queryEquipment(equipmentNumber,userName);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	public Object deleteEquipment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber){
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		if(!user.getRoleId().equals(1) && !user.getRoleId().equals(2) && !user.getRoleId().equals(3)){
			throw new SecurityException("user", "只有管理员能够删除");
		}
		
		EquipmentInfo ei = equipmentInfoMapper.selectByPrimaryKey(equipmentNumber);
		if(null==ei){
			throw new ParamException("equipmentNumber", "设备不存在");
		}
		
		String fileName = ei.getQrCode();
		
		int count = (int)equipmentService.deleteEquipment(equipmentNumber);
		
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
	
	@RequestMapping(value ="/equipment/{equipmentNumber}/auditstatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateAuditstatus(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber,
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
		
		equipmentListVo.setEquipmentNumber(equipmentNumber);
		
		String strBackUrl = "http://" + request.getServerName() + ":"
				+ request.getServerPort()
				+ request.getContextPath();
				
		String text=strBackUrl+"/equipment/"+equipmentNumber;
		String path = request.getSession().getServletContext().getRealPath("/")+"Report/";
		String s = QRCodeUtil.encode(text, path);
		equipmentListVo.setQrCode(s);
		equipmentListVo.setQrCodePath(strBackUrl+"/Report/"+s);
		return equipmentService.updateAuditstatus(equipmentListVo);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}/rescuestatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateRescuestatus(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber,
			@RequestBody EquipmentListVo equipmentListVo) throws Exception{
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		equipmentListVo.setEquipmentNumber(equipmentNumber);
		return equipmentService.updateAuditstatus(equipmentListVo);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}/paystatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updatePayStatus(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber,
			@RequestBody EquipmentListVo equipmentListVo) throws Exception{
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(null==user){
			throw new SecurityException("user", "请先登录");
		}
		
		equipmentListVo.setEquipmentNumber(equipmentNumber);
		return equipmentService.updateAuditstatus(equipmentListVo);
	}
	
}
