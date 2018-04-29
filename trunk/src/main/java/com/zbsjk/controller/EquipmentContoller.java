package com.zbsjk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.vo.EquipmentListVo;
import com.zbsjk.service.EquipmentService;
import com.zbsjk.util.QRCodeUtil;

@RestController
public class EquipmentContoller {

	@Autowired
	private EquipmentService equipmentService;
	
	@RequestMapping(value ="/equipment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addEquipment(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EquipmentInfo equipmentInfo){
		return equipmentService.addEquipment(equipmentInfo);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateEquipment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber,
			@RequestBody EquipmentInfo equipmentInfo){
		equipmentInfo.setEquipmentNumber(equipmentNumber);
		return equipmentService.updateEquipment(equipmentInfo);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object queryEquipment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber){
		return equipmentService.queryEquipment(equipmentNumber);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	public Object deleteEquipment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber){
		return equipmentService.deleteEquipment(equipmentNumber);
	}
	
	@RequestMapping(value ="/equipment", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Object queryEquipmentList(HttpServletRequest request, HttpServletResponse response,
			EquipmentListVo equipmentListVo,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize){
		return equipmentService.queryEquipmentList(equipmentListVo,pageNo,pageSize);
	}
	
	@RequestMapping(value ="/equipment/{equipmentNumber}/auditstatus", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public Object updateAuditstatus(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String equipmentNumber,
			@RequestBody EquipmentListVo equipmentListVo) throws Exception{
		
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
	
}
