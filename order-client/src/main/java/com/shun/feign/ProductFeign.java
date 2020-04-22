package com.shun.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(name = "product-client")
public interface ProductFeign {
    @RequestMapping("/product/getById")
    Map getById(Integer id);
}
