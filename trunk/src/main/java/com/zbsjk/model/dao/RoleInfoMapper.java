package com.zbsjk.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zbsjk.model.entity.RoleInfo;

@Mapper
public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}