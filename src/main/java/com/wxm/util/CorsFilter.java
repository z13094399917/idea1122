package com.wxm.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  配置tomcat允许跨域访问
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-25 16:30
 */
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        // Access-Control-Allow-Origin就是我们需要设置的域名
        // Access-Control-Allow-Headers跨域允许包含的头。
        // Access-Control-Allow-Methods是允许的请求方式
        resp.setHeader("Access-Control-Allow-Origin", "*");// *,任何域名
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        // resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,
        // Content-Type, Accept");
        // 允许客户端，发一个新的请求头jwt
        //允许客户端发送一个新的请求头
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, jwt, verificationJwt");
        //允许客户端处理一个新的响应头jwt
        resp.setHeader("Access-Control-Expose-Headers", "jwt");
        resp.setHeader("Access-Control-Expose-Headers", "verificationJwt");
        // String sss = resp.getHeader("Access-Control-Expose-Headers");
        // System.out.println("sss=" + sss);

        // 允许请求头Token
        // httpResponse.setHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,
        // Content-Type, Accept, Token");
        // System.out.println("Token=" + req.getHeader("Token"));

        if ("OPTIONS".equals(req.getMethod())) {// axios的ajax会发两次请求，第一次提交方式为：option，直接返回即可
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
