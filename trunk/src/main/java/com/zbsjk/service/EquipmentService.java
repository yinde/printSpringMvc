package com.zbsjk.service;

import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.vo.EquipmentListVo;

public interface EquipmentService {

	Object addEquipment(EquipmentInfo equipmentInfo);

	Object updateEquipment(EquipmentInfo equipmentInfo);

	Object queryEquipment(String equipmentNumber,String userName);

	Object deleteEquipment(Integer equipmentId);

	Object queryEquipmentList(EquipmentListVo equipmentListVo, Integer pageNo, Integer pageSize);

	Object updateAuditstatus(EquipmentListVo equipmentListVo);

}
