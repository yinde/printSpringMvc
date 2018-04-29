package com.zbsjk.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zbsjk.model.entity.EquipmentInfo;

@Mapper
public interface EquipmentInfoMapper {
    int deleteByPrimaryKey(String equipmentNumber);

    int insert(EquipmentInfo record);

    int insertSelective(EquipmentInfo record);

    EquipmentInfo selectByPrimaryKey(String equipmentNumber);

    int updateByPrimaryKeySelective(EquipmentInfo record);

    int updateByPrimaryKey(EquipmentInfo record);
}