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
import com.zbsjk.model.entity.UserInfo;
import com.zbsjk.model.vo.EquipmentListVo;
import com.zbsjk.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentInfoMapper equipmentInfoMapper;
	
	@Override
	public Object addEquipment(EquipmentInfo equipmentInfo) {
		equipmentInfo.setCreateTime(new Date());
		return equipmentInfoMapper.insertSelective(equipmentInfo);
	}

	@Override
	public Object updateEquipment(EquipmentInfo equipmentInfo) {
		EquipmentInfo ei = equipmentInfoMapper.selectByPrimaryKey(equipmentInfo.getEquipmentNumber());
		if(null==ei || ei.getAuditStatus().equals(1)){
			throw new ParamException("equipmentInfo", "信息不存在或审核已通过");
		}
		return equipmentInfoMapper.updateByPrimaryKeySelective(equipmentInfo);
	}

	@Override
	public Object queryEquipment(String equipmentNumber) {
		return equipmentInfoMapper.selectByPrimaryKey(equipmentNumber);
	}

	@Override
	public Object deleteEquipment(String equipmentNumber) {
		return equipmentInfoMapper.deleteByPrimaryKey(equipmentNumber);
	}

	@Override
	public Object queryEquipmentList(EquipmentListVo equipmentListVo, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize,"create_time desc");
		List<EquipmentInfo> equipmentList = equipmentInfoMapper.queryByProperties(equipmentListVo);
		PageInfo<EquipmentInfo> pageInfo = new PageInfo<>(equipmentList);  
		return pageInfo;
	}

	@Override
	public Object updateAuditstatus(EquipmentListVo equipmentListVo) {
		return equipmentInfoMapper.updateByPrimaryKeySelective(equipmentListVo);
	}

}
