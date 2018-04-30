package com.zbsjk.model.dao;

import java.util.List;

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

	List<RoleInfo> queryRoleList(List<Integer> list);
}