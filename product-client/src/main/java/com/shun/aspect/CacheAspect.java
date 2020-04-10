package com.shun.aspect;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpSession;

@Aspect
@Configuration
public class CacheAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private HttpSession session;
    @Around("@annotation(com.shun.annotation.AddCache)")
    public Object addOrGetCache(ProceedingJoinPoint proceed) throws Throwable {
        String key = "snap-product"+session.getServletContext().getContextPath().replace("/","")+"-"+proceed.getSignature().getDeclaringTypeName();
        String key1 = proceed.getSignature().getName();
        Object[] args = proceed.getArgs();
        for (Object o : args) {
            key1+=o+",";
        }
        stringRedisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        HashOperations hashOperations = stringRedisTemplate.opsForHash();
        Object o = hashOperations.get(key, key1);
        if (o!=null) {
            return o;
        }else{
            try {
                Object p = proceed.proceed();
                stringRedisTemplate.opsForHash().put(key, key1, p);
                return p;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    @Around("@annotation(com.shun.annotation.DelCache)")
    public Object removeCache(ProceedingJoinPoint proceed) throws Throwable {
        String key = "snap-product"+session.getServletContext().getContextPath().replace("/","")+"-"+proceed.getSignature().getDeclaringTypeName();
        stringRedisTemplate.delete(key);
        return proceed.proceed();
    }
}
