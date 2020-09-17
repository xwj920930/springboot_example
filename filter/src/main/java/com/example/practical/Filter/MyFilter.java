package com.example.practical.Filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * 过滤器是处于客户端和服务器资源文件之间的一道过滤网，帮助我们过滤掉一些不符合要求的请求，
 * 通常用作 Session 校验，判断用户权限，如果不符合设定条件，则会被拦截到特殊的地址或者基于特殊的响应
 */
//lombok 是用来避免每个文件创建 Logger 来打印日志
@Log4j2
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        String uri = request.getRequestURI();
        log.info("请求地址为：" + uri);
        if (uri.contains("addSession") || uri.contains("removeSession") || uri.contains("online")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            wrapper.sendRedirect("/online");
        }
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器");
    }
}
