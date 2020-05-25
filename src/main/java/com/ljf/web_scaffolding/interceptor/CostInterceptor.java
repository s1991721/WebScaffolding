package com.ljf.web_scaffolding.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mr.lin on 2020/5/23
 * 拦截器
 */
@Slf4j
@Component
public class CostInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            Long startTime = (Long) request.getAttribute("startTime");
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("{}{} 耗时：{}", handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(), System.currentTimeMillis() - startTime);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
