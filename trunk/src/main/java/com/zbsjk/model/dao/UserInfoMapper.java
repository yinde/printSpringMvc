package com.zbsjk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zbsjk.model.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	List<UserInfo> queryUserList(UserInfo userInfo);
}