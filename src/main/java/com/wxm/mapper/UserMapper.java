package com.wxm.mapper;

import com.wxm.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uname);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uname);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(User user);

}