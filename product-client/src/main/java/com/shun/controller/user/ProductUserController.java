package com.shun.controller.user;

import com.shun.entity.Product;
import com.shun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("product")
public class ProductUserController {
    @Autowired
    private ProductService productService;
    @RequestMapping("findById")
    @ResponseBody
    public Map findById(Integer id, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return productService.findById(id);
    }
    @RequestMapping("getById")
    @ResponseBody
    public Map getById(Integer id, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setHeader("Access-Control-Allow-Origin","*");
        Product product = (Product) productService.findById(id).get("product");
        System.out.println(product);
        Map map = new HashMap();
        if(product!=null) {
            map.put("status",200);
            map.put("name", product.getName());
            map.put("price", product.getPrice());
        }else{
            map.put("status",500);
        }
        return map;
    }
}
