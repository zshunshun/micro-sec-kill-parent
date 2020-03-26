package com.shun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.SecurityCode;
import util.SecurityImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("security")
public class SecurityController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("getCode")
    public void getCode(String jsessionid, HttpServletResponse response) throws IOException {
        String code = SecurityCode.getSecurityCode();
        stringRedisTemplate.opsForHash().put(jsessionid,"key",code);
        stringRedisTemplate.expire(jsessionid,30, TimeUnit.MINUTES);
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
    }
}
