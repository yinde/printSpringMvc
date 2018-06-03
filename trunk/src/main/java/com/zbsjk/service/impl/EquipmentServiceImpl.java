package com.zbsjk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbsjk.ext.ParamException;
import com.zbsjk.model.dao.EquipmentInfoMapper;
import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.vo.EquipmentListVo;
import com.zbsjk.service.EquipmentService;
import com.zbsjk.util.DateUtil;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentInfoMapper equipmentInfoMapper;
	
	@Override
	public Object addEquipment(EquipmentInfo equipmentInfo) {
		EquipmentListVo record = new EquipmentListVo();
		record.setEquipmentNumber(equipmentInfo.getEquipmentNumber());
		record.setRegisterNumber(equipmentInfo.getRegisterNumber());
		int count  = equipmentInfoMapper.checkEquipmentInfo(record);
		if(count!=0){
			throw new ParamException("equipmentNumber", "设备编号或登录编号已存在");
		}
		
		equipmentInfo.setCreateTime(new Date());
		return equipmentInfoMapper.insertSelective(equipmentInfo);
	}

	@Override
	public Object updateEquipment(EquipmentInfo equipmentInfo) {
		EquipmentInfo ei = equipmentInfoMapper.selectByPrimaryKey(equipmentInfo.getEquipmentId());
		if(null==ei || ei.getAuditStatus().equals(1)){
			throw new ParamException("equipmentInfo", "信息不存在或审核已通过");
		}
		return equipmentInfoMapper.updateByPrimaryKeySelective(equipmentInfo);
	}

	@Override
	public Object queryEquipment(String equipmentNumber,String userName) {
		EquipmentListVo record = new EquipmentListVo();
		record.setEquipmentNumber(equipmentNumber);
		record.setUserName(userName);
		List<EquipmentInfo> list = equipmentInfoMapper.queryByProperties(record);
		if(list.size()!=1){
			throw new ParamException("equipmentNumber", "信息不存在");
		}
		return list.get(0);
	}

	@Override
	public Object deleteEquipment(Integer equipmentId,Integer operationUser) {
		equipmentInfoMapper.insertBackInfo(equipmentId,operationUser);
		return equipmentInfoMapper.deleteByPrimaryKey(equipmentId);
	}

	@Override
	public Object queryEquipmentList(EquipmentListVo equipmentListVo, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize,"create_time desc");
		List<EquipmentInfo> equipmentList = equipmentInfoMapper.queryByProperties(equipmentListVo);
		PageInfo<EquipmentInfo> pageInfo = new PageInfo<>(equipmentList);  
		return pageInfo;
	}

	@Override
	public int updateByPrimaryKeySelective(EquipmentInfo equipmentInfo) {
		return equipmentInfoMapper.updateByPrimaryKeySelective(equipmentInfo);
	}

	@Override
	public EquipmentInfo selectByPrimaryKey(Integer equipmentId) {
		return equipmentInfoMapper.selectByPrimaryKey(equipmentId);
	}
	
	@Override
	public List<Map<String, Object>> createExcelRecord(List<EquipmentInfo> equipmentList) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        for (int j = 0; j < equipmentList.size(); j++) {
        	EquipmentInfo equipmentInfo=equipmentList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("equipmentNumber", equipmentInfo.getEquipmentNumber());
            mapValue.put("registerNumber", equipmentInfo.getRegisterNumber());
            mapValue.put("registerCompany", equipmentInfo.getRegisterCompany());
            mapValue.put("userName", equipmentInfo.getUserName());
            mapValue.put("userIdCard", equipmentInfo.getUserIdCard());
            mapValue.put("companyName", equipmentInfo.getCompanyName());
            mapValue.put("creditCode", equipmentInfo.getCreditCode());
            mapValue.put("userAddress", equipmentInfo.getUserAddress());
            mapValue.put("userPhone", equipmentInfo.getUserPhone());
           // mapValue.put("equipmentProvince", equipmentInfo.getEquipmentProvince());
            mapValue.put("equipmentCity", equipmentInfo.getEquipmentCity());
            mapValue.put("equipmentArea", equipmentInfo.getEquipmentArea());
            mapValue.put("equipmentBrand", equipmentInfo.getEquipmentBrand());
            mapValue.put("equipmentType", equipmentInfo.getEquipmentType());
            mapValue.put("equipmentModel", equipmentInfo.getEquipmentModel());
            mapValue.put("machineNumber", equipmentInfo.getMachineNumber());
            mapValue.put("engineNumber", equipmentInfo.getEngineNumber());
            mapValue.put("enginePower", equipmentInfo.getEnginePower());
            mapValue.put("bucketCapacity", equipmentInfo.getBucketCapacity());
            mapValue.put("machineQuality", equipmentInfo.getMachineQuality());
            mapValue.put("equipmentTyre", equipmentInfo.getEquipmentTyre());
            mapValue.put("equipmentTrack", equipmentInfo.getEquipmentTrack());
            mapValue.put("insuranceAgency", equipmentInfo.getInsuranceAgency());
            mapValue.put("insuranceInsurance", equipmentInfo.getInsuranceInsurance());
            mapValue.put("equipmentLong", equipmentInfo.getEquipmentLong());
            mapValue.put("equipmentWide", equipmentInfo.getEquipmentWide());
            mapValue.put("equipmentHight", equipmentInfo.getEquipmentHight());
            
            //购买方式
            String purchaseMethod = "";
            if(null!=equipmentInfo.getPurchaseMethod()){
            	if(equipmentInfo.getPurchaseMethod().equals("0")){
            		purchaseMethod="全款购买";
            	}else if(equipmentInfo.getPurchaseMethod().equals("1")){
            		purchaseMethod="分期购买";
            	}else if(equipmentInfo.getPurchaseMethod().equals("2")){
            		purchaseMethod="转让购买";
            	}
            }
            mapValue.put("purchaseMethod", purchaseMethod);
            
            mapValue.put("fuelVariety", equipmentInfo.getFuelVariety());
            mapValue.put("fuelSource", equipmentInfo.getFuelSource());
            mapValue.put("equipmentContaminants", equipmentInfo.getEquipmentContaminants());
            
            //噪音检测
            String noiseDetection = "";
            if(null!=equipmentInfo.getNoiseDetection()){
            	if(equipmentInfo.getNoiseDetection().equals("1")){
            		noiseDetection="合格";
            	}else if(equipmentInfo.getNoiseDetection().equals("0")){
            		noiseDetection="不合格";
            	}
            }
            mapValue.put("noiseDetection", noiseDetection);
            
            mapValue.put("dischargeStage", equipmentInfo.getDischargeStage());
            mapValue.put("exhaustEmission", equipmentInfo.getExhaustEmission());
           
            //购买时间
            String purchaseTime="";
            if(null!=equipmentInfo.getPurchaseTime()){
            	purchaseTime=DateUtil.DateFormat("yyyy-MM-dd", equipmentInfo.getPurchaseTime());
            }
            mapValue.put("purchaseTime", purchaseTime);
           
            mapValue.put("fuelConsumption", equipmentInfo.getFuelConsumption());
            listmap.add(mapValue);
        }
        return listmap;
    }
}
