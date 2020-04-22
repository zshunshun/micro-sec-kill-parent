package com.shun.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "addr-client")
public interface AddrFeign {
    @RequestMapping("/addr/getById")
    Map getById(@RequestParam("id") Integer id);
}
