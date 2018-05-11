package com.zbsjk.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbsjk.ext.ParamException;
import com.zbsjk.model.dao.EquipmentInfoMapper;
import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.vo.EquipmentListVo;
import com.zbsjk.service.EquipmentService;

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

}
