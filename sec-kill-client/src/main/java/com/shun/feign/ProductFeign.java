package com.shun.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@FeignClient(name = "product-client")
public interface ProductFeign {
    //多个参数    一定要使用 @RequestParam
    @RequestMapping("/product-manager/findByPage")
    Map testFeigns(@RequestParam("rows")Integer rows, @RequestParam("page")Integer page);
    @RequestMapping("/product/findById")
    Map findById(@RequestParam("id") Integer id);
}
