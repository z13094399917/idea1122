package com.wxm.mapper;

import com.wxm.model.ShiroUser;
import com.wxm.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ShiroUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(ShiroUser record);

    int insertSelective(ShiroUser record);

    ShiroUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(ShiroUser record);

    int updateByPrimaryKey(ShiroUser record);

    ShiroUser queryByName(@Param("userName") String userName);

    Set<String> getRolesByUserId(@Param("userid") Integer userId);

    Set<String> getPersByUserId(@Param("userid") Integer userId);
}