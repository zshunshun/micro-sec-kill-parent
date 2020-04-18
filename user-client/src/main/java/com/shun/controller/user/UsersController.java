package com.shun.controller.user;

import com.alibaba.fastjson.JSON;
import com.shun.entity.Manager;
import com.shun.entity.User;
import com.shun.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
@Log4j2
public class UsersController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    @ResponseBody
    public Map login(User user, String checkCode,String userToken, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader( "Set-Cookie", "_u=xxxx; Path=/Login; SameSite=None; Secure");
        Map map = new HashMap();
        if(checkCode==null||checkCode.length()!=4||userToken==null){
            map.put("status",400);
            return map;
        }else{
            String code = (String) stringRedisTemplate.opsForHash().get(userToken,"key");
            if(!checkCode.equals(code)){
                map.put("status",400);
                return map;
            }
        }
        User login = userService.login(user);
        if(login==null){
            map.put("status",500);
        }else{
            map.put("status",200);
            map.put("username",user.getUsername());
            stringRedisTemplate.opsForHash().delete(userToken,"key");
            stringRedisTemplate.opsForHash().put(userToken,"user", JSON.toJSONString(login));
            stringRedisTemplate.expire(userToken,30, TimeUnit.MINUTES);
        }
        return map;
    }
    @RequestMapping("getUsername")
    @ResponseBody
    public Map getUsername(String userToken,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader( "Set-Cookie", "_u=xxxx; Path=/Login; SameSite=None; Secure");
        Map map = new HashMap();
        String userJson = (String) stringRedisTemplate.opsForHash().get(userToken, "user");
        if(userJson!=null) {
            User user = JSON.parseObject(userJson, User.class);
            map.put("status",200);
            map.put("username",user.getUsername());
            map.put("pic",user.getPic());
        }else {
            map.put("status",500);
        }
        return map;
    }
    @RequestMapping("logout")
    @ResponseBody
    public Map logout(String userToken,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader( "Set-Cookie", "_u=xxxx; Path=/Login; SameSite=None; Secure");
        Boolean delete = stringRedisTemplate.delete(userToken);
        Map map = new HashMap();
        if(delete) {
            map.put("status", 200);
        }else{
            map.put("status",500);
        }
        return map;
    }
}
