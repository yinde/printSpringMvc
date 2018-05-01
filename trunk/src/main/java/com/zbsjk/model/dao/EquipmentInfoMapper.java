package com.zbsjk.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zbsjk.model.entity.EquipmentInfo;
import com.zbsjk.model.vo.EquipmentListVo;

@Mapper
public interface EquipmentInfoMapper {
    int deleteByPrimaryKey(Integer equipmentId);

    int insert(EquipmentInfo record);

    int insertSelective(EquipmentInfo record);

    EquipmentInfo selectByPrimaryKey(Integer equipmentId);

    int updateByPrimaryKeySelective(EquipmentInfo record);

    int updateByPrimaryKey(EquipmentInfo record);

	List<EquipmentInfo> queryByProperties(EquipmentListVo record);
}