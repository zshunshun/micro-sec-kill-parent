package com.shun.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-client")
public interface UserFeign {
    @RequestMapping("/user/getUserId")
    String getUserId(@RequestParam("userToken") String userToken);
}
