package com.wxm.service;

import com.wxm.model.User;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-25 18:45
 */
public interface UserService {
    int deleteByPrimaryKey(String uname);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uname);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(User user);
}
