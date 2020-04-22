package com.shun.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "sec-kill-client")
public interface SnapFeign {
    @RequestMapping("/snap/getById")
    Map getById(@RequestParam("id") Integer id);
}
