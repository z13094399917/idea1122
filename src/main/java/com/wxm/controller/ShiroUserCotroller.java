package com.wxm.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wxm.model.ShiroUser;
import com.wxm.service.ShiroUserService;
import com.wxm.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-12-01 16:30
 */
@Controller
public class ShiroUserCotroller {
    @Autowired
    private ShiroUserService shiroUserService;

    /**
     * 登入
     *
     * @param req
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest req) {

        String uname = req.getParameter("username");
        String pwd = req.getParameter("password");
        //生成令牌
        UsernamePasswordToken token = new UsernamePasswordToken(uname, pwd);
        Subject subject = SecurityUtils.getSubject();//登录主体
        try {
            subject.login(token);
            req.getSession().setAttribute("username", uname);
            return "hello";
        } catch (Exception e) {
            req.setAttribute("message", "用户名或密码错误");
            return "login";

        }

    }

    /**
     * 登出
     *
     * @param req
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        Subject subject = SecurityUtils.getSubject();//拿到登入主体
        subject.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 注册
     *
     * @param shiroUser
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/add")
    public String add(ShiroUser shiroUser, HttpServletRequest req, HttpServletResponse resp) {
//获取jsp传过来的用户名和密码
        String uname = req.getParameter("username1");
        String pwd = req.getParameter("password1");
        shiroUser.setUsername(uname);
        //使用工具类生成盐
        String salt = PasswordHelper.createSalt();
        shiroUser.setSalt(salt);
        //盐加密后得到的密码
        String credentials = PasswordHelper.createCredentials(pwd, salt);
        shiroUser.setPassword(credentials);
//新增
        int insert = this.shiroUserService.insert(shiroUser);

        if (insert > 0) {
            req.setAttribute("message", "新增成功！！！");
            return "login";
        } else {
            req.setAttribute("message", "新增失败。。。。");
            return "login";

        }
    }


    @RequiresUser
    @ResponseBody
    @RequestMapping("/passUser")

    public String passUser() {

        return "........passUser...";
    }

    @RequiresRoles(value = {"2", "4"}, logical = Logical.AND)
    @ResponseBody
    @RequestMapping("/passRole")
    public String passRole() {
        return "passRole...";
    }

    @RequiresPermissions(value = {"user:update","user:view"},logical = Logical.OR)    @ResponseBody
    @RequestMapping("/passPer")
    public String passPer() {
        return "passPer...";

    }
}
