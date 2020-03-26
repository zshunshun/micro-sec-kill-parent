package com.shun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("cookie")
public class CookieController {
    @RequestMapping("getJsessionid")
    @ResponseBody
    public String getJsessionId(HttpServletRequest request){
        return request.getSession().getId();
    }
}
