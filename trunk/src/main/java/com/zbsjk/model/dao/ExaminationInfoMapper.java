package com.zbsjk.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zbsjk.model.entity.ExaminationInfo;

@Mapper
public interface ExaminationInfoMapper {
    int deleteByPrimaryKey(Integer examinationId);

    int insert(ExaminationInfo record);

    int insertSelective(ExaminationInfo record);

    ExaminationInfo selectByPrimaryKey(Integer examinationId);

    int updateByPrimaryKeySelective(ExaminationInfo record);

    int updateByPrimaryKey(ExaminationInfo record);
}