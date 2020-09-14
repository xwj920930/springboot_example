package com.example.practical.Interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Java中的拦截器是动态拦截 action 调用的对象，然后提供了可以在 action 执行前后增加一些操作，也可以在 action 执行前停止操作，
 * 功能与过滤器类似，但是标准和实现方式不同。
 *
 * 登录认证：在一些应用中，可能会通过拦截器来验证用户的登录状态，如果没有登录或者登录失败，就会给用户一个友好的提示或者返回登录页面，
 * 当然大型项目中都不采用这种方式，都是调单点登录系统接口来验证用户。
 *
 * 记录系统日志：我们在常见应用中，通常要记录用户的请求信息，比如请求 ip，方法执行时间等，通过这些记录可以监控系统的状况，以便于对
 * 系统进行信息监控、信息统计、计算 PV、性能调优等。
 *
 * 通用处理：在应用程序中可能存在所有方法都要返回的信息，这是可以利用拦截器来实现，省去每个方法冗余重复的代码实现
 */
@Log4j2
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("【MyInterceptor】调用了:{}", request.getRequestURI());
        request.setAttribute("requestTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!request.getRequestURI().contains("/online")) {
            HttpSession session = request.getSession();
            String sessionName = (String) session.getAttribute("name");
            if ("xwj".equals(sessionName)) {
                log.info("【MyInterceptor】当前浏览器存在 session:{}",sessionName);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long duration = (System.currentTimeMillis() - (Long)request.getAttribute("requestTime"));
        log.info("【MyInterceptor】[{}]调用耗时:{}ms",request.getRequestURI(), duration);
    }
}
