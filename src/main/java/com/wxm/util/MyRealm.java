package com.wxm.util;

import com.wxm.model.ShiroUser;
import com.wxm.service.ShiroUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 充当了ini文件，也就是数据源
 * 认证的过程
 * 1、数据源 (ini->>数据库)
 * 2、doGetAuthenticationInfo将数据库的用户信息给subject主体做shiro认证的
 *      2.1、需要在当前的realm中调用service来验证，当前用户是否在数据库中存在
 *      2.2、盐加密
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-12-01 16:18
 */
@Component
public class MyRealm extends AuthorizingRealm {

    private ShiroUserService shiroUserService;

    public ShiroUserService getShiroUserService() {
        return shiroUserService;
    }

    public void setShiroUserService(ShiroUserService shiroUserService) {
        this.shiroUserService = shiroUserService;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("用户据授权");
        //获取到的用户名
        String username=principalCollection.getPrimaryPrincipal().toString();
        //根据用户名获取当前用户
        ShiroUser user=shiroUserService.queryByName(username);
        //获取用户的角色列表
        Set<String> roles=shiroUserService.getRolesByUserId(user.getUserid());
        //获取当前用户的权限列表
        Set<String> pers=shiroUserService.getPersByUserId(user.getUserid());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将用户的授权信息保持到info对象中

        info.setRoles(roles);
        info.setStringPermissions(pers);
        return info;



    }

    /**
     * 认证
     *
     * @param token 从jsp传递过来的用户名，密码组合成的一个token对象
     * @return
     * @throws AuthenticationException token是controller层传递过来的，也就是说一做登录操作，就会访问这个方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //jsp用户名和密码
        String userName = token.getPrincipal().toString();
        //匹配
        ShiroUser shiroUser = this.shiroUserService.queryByName(userName);
        //认证
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                shiroUser.getUsername(),
                shiroUser.getPassword(),
                ByteSource.Util.bytes(shiroUser.getSalt()),
                this.getName()

                );
        return info;
    }
}
