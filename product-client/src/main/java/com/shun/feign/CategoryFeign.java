package com.shun.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//name=所调用的微服务实例名
@FeignClient(name = "category-client")
public interface CategoryFeign {
    //生声明式    伪客户端
    @RequestMapping("/category/searchByName")
    public List<Integer> searchByName(@RequestParam("searchString") String searchString,@RequestParam("searchOper") String searchOper);
}
