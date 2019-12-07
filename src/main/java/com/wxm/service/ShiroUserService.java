package com.wxm.service;

import com.wxm.model.ShiroUser;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-12-01 16:15
 */
public interface ShiroUserService {
    /**
     * shiro认证
     * @param userName
     * @return
     */
    ShiroUser queryByName(String userName);

    /**
     * 新增用户
     * @param record
     * @return
     */
    public int insert(ShiroUser record);

    /**
     * 根据用户id获取角色集合
     * @param userId
     * @return
     */

    Set<String> getRolesByUserId(Integer userId);

    /**
     * 根据用户id获取权限集合
     * @param userId
     * @return
     */
    Set<String> getPersByUserId(Integer userId);

}
