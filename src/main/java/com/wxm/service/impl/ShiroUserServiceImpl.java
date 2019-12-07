package com.wxm.service.impl;

import com.wxm.mapper.ShiroUserMapper;
import com.wxm.model.ShiroUser;
import com.wxm.service.ShiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-12-01 16:16
 */
@Service("shiroUserService")
public class ShiroUserServiceImpl implements ShiroUserService {

    @Autowired
    private ShiroUserMapper shiroUserMapper;
    @Override
    public ShiroUser queryByName(String userName) {
        return shiroUserMapper.queryByName(userName);
    }

    /**
     * 新增用户
     *
     * @param record
     * @return
     */
    @Override
    public int insert(ShiroUser record) {
        return shiroUserMapper.insert(record);
    }

    /**
     * 根据用户id获取角色集合
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getRolesByUserId(Integer userId) {
        return shiroUserMapper.getRolesByUserId(userId);
    }

    /**
     * 根据用户id获取权限集合
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getPersByUserId(Integer userId) {
        return shiroUserMapper.getPersByUserId(userId);
    }
}
