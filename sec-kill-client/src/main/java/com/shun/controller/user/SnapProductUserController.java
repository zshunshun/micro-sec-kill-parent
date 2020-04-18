package com.shun.controller.user;

import com.shun.entity.SnapProduct;
import com.shun.feign.ProductFeign;
import com.shun.service.SnapProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("snap")
public class SnapProductUserController {
    @Autowired
    private SnapProductService snapProductService;
    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("findById")
    @ResponseBody
    public Map findById(Integer id, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        SnapProduct snapProduct = snapProductService.findById(id);
        if(snapProduct==null){
            map.put("status",500);
        }else {
            Map products = productFeign.findById(snapProduct.getProductId());
            map.put("snapProduct", snapProduct);
            map.putAll(products);
            map.put("status",200);
        }
        return map;
    }
    @RequestMapping("snapStart")
    @ResponseBody
    public Map snapStart(String snapId){
        String s = stringRedisTemplate.opsForList().leftPop("snapCount_id=" + snapId);
        Map map = new HashMap();
        if("1".equals(s)){

            map.put("status",200);
        }else{
            map.put("status",500);
        }
        return map;
    }

}
