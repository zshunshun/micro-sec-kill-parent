package com.shun.interceptor;

import com.shun.entity.Manager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setHeader("Access-Control-Allow-Origin","*");
        String jsessionid =  request.getParameter("jsessionid");
        if(jsessionid==null){
            log.info("Manager不存在jsessionid");
            return false;
        }else{
            System.out.println(jsessionid);
            System.out.println(stringRedisTemplate);
            String manager = (String) stringRedisTemplate.opsForHash().get(jsessionid, "manager");
            if(manager==null){
                log.info("Manager的jsessionid过期");
                return false;
            }else {
                log.info("放行："+manager);
                stringRedisTemplate.expire(jsessionid,30, TimeUnit.MINUTES);
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("=====2=====");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println("=====3=====");
    }
}
