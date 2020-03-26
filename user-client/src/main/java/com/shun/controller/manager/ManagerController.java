package com.shun.controller.manager;

import com.alibaba.fastjson.JSON;
import com.shun.entity.Manager;
import com.shun.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("manager")
@Slf4j
public class ManagerController {
    @Value("${shun.host}")
    private String staticHost;
    @Value("${shun.port}")
    private String staticPort;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ManagerService managerService;
    @RequestMapping("login")
    @ResponseBody
    public Map login(Manager manager, String checkCode, String jsessionid, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        if(checkCode==null||checkCode.length()!=4||jsessionid==null){
            map.put("status",400);
            return map;
        }else{
            String code = (String) stringRedisTemplate.opsForHash().get(jsessionid,"key");
            if(!checkCode.equals(code)){
                map.put("status",400);
                return map;
            }
        }
        Manager login = managerService.login(manager);
        if(login==null){
            map.put("status",500);
        }else{
            map.put("status",200);
            map.put("username",manager.getUsername());
            stringRedisTemplate.opsForHash().delete(jsessionid,"key");
            stringRedisTemplate.opsForHash().put(jsessionid,"manager", JSON.toJSONString(manager));
            stringRedisTemplate.expire(jsessionid,30, TimeUnit.MINUTES);
        }
        return map;
    }
    @RequestMapping("logout")
    @ResponseBody
    public String logout(String jsessionid,HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");
        Boolean delete = stringRedisTemplate.delete(jsessionid);
        if(delete){
            return "ok";
        }else{
            return "error";
        }
    }
    @RequestMapping("getToken")
    @ResponseBody
    public String getToken(String jsessionid,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        String managerStr = (String) stringRedisTemplate.opsForHash().get(jsessionid, "manager");
        if(managerStr!=null){
            Manager manager = JSON.parseObject(managerStr, Manager.class);
            return manager.getUsername();
        }else{
            return null;
        }

    }

}
