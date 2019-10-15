package com.xwj.jwt.config;


import com.xwj.jwt.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //response.setContentType(“text/html; charset=utf-8”); html 
        response.setContentType("text/plain; charset=utf-8"); //文本 
        response.setContentType("text/JavaScript; charset=utf-8"); //json数据 
        //response.setContentType(“application/xml; charset=utf-8”); xml数据
        //执行认证
        if (token == null) {
            response.getWriter().write("无token，请重新登录");
            return false;
        }
        try {
            //验证token
            JwtUtil.parseJWT(token);
        }catch (ExpiredJwtException e){
            response.getWriter().write("token过期");
            return false;
        }catch (SignatureException e){
            response.getWriter().write("token无效");
            return false;
        }
        return true;
    }
}
