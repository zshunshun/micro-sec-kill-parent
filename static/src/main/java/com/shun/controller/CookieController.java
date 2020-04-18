package com.shun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("cookie")
public class CookieController {
    @RequestMapping("getJsessionid")
    @ResponseBody
    public String getJsessionId(HttpServletRequest request, HttpServletResponse response){
        response.setHeader( "Set-Cookie", "_u=xxxx; Path=/Login; SameSite=None; Secure");
        return request.getSession().getId();
    }
}
