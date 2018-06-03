package com.zbsjk.service;

import java.util.List;
import java.util.Map;

import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.vo.EquipmentListVo;

public interface EquipmentService {

	Object addEquipment(EquipmentInfo equipmentInfo);

	Object updateEquipment(EquipmentInfo equipmentInfo);

	Object queryEquipment(String equipmentNumber,String userName);

	Object deleteEquipment(Integer equipmentId,Integer operationUser);

	Object queryEquipmentList(EquipmentListVo equipmentListVo, Integer pageNo, Integer pageSize);

	int updateByPrimaryKeySelective(EquipmentInfo equipmentInfo);

	EquipmentInfo selectByPrimaryKey(Integer equipmentId);

	List<Map<String, Object>> createExcelRecord(List<EquipmentInfo> equipmentList);

}
