package com.wxm.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.wxm.model.User;
import com.wxm.service.UserService;
import com.wxm.util.ImageUtil;
import com.wxm.util.JSONResult;
import com.wxm.util.JwtUtils;
import com.wxm.util.VerifyCodeUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-25 18:55
 */

@Controller
@RequestMapping("/vue/user")
public class UserController {
    private static final String VERIFICATION_CODE = "verificationCode_";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(User u, HttpServletRequest request, HttpServletResponse response){
        //获取用户输入的验证码
        String userVerificationCode = request.getParameter("verificationCode");
        //获取验证码jwt令牌
        String userJwt = request.getHeader(JwtUtils.JWT_VERIFICATION_KEY);
        //获取到保存在redis中的验证码
        Object redisVerificationCode =  redisTemplate.opsForValue().get(VERIFICATION_CODE + userJwt) ;

//        这里存在两种情况：1、令牌超时   2、验证码超时
        if(StringUtils.isEmpty(redisVerificationCode)){
            return JSONResult.errorMsg("你的验证码已超时");
        }

        if(!redisVerificationCode.toString().equalsIgnoreCase(userVerificationCode)){
            return JSONResult.errorMsg("验证码错误");
        }

        User user = userService.login(u);
        //判断是否登录成功
        if(user != null){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("User", user);
            //这是颁发用户登录成功的jwt令牌
            String jwt= JwtUtils.createJwt(map, JwtUtils.JWT_WEB_TTL);
            response.setHeader(JwtUtils.JWT_HEADER_KEY, jwt);
            return JSONResult.ok(user);
        }else {
            return JSONResult.errorMsg("密码或账户错误");
        }

    }




    /**生成图片验证码*/
    @RequestMapping("/verificationCode")
    @ResponseBody
    public String verificationCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //生成验证码随机数
        String word = VerifyCodeUtil.produceNumAndChar(4);
//        获取用户的jwt令牌
        String userVerificationJwt = req.getHeader(JwtUtils.JWT_VERIFICATION_KEY);
        //验证码令牌
        Claims claims = JwtUtils.validateJwtToken(userVerificationJwt);
        if(claims == null){
            //如果用户令牌过期那么对应存放在redis中的数据也要清空
            if(!StringUtils.isEmpty(userVerificationJwt)){
                redisTemplate.expire(VERIFICATION_CODE + userVerificationJwt, 1, TimeUnit.DAYS);
            }
            userVerificationJwt =  JwtUtils.createJwt(new HashMap<String, Object>() ,JwtUtils.JWT_WEB_TTL);
            //将jwt令牌放入 response head中
            resp.setHeader(JwtUtils.JWT_VERIFICATION_KEY, userVerificationJwt);
        }
        //刷新缓存，更新验证码
        redisTemplate.opsForValue().set(VERIFICATION_CODE + userVerificationJwt , word,60, TimeUnit.SECONDS);
        //生成图片
        String code = "data:image/png;base64," + ImageUtil.createImageWithVerifyCode(word, 116,40);

        return code;
    }
}