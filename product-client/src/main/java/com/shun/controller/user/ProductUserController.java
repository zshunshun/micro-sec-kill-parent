package com.shun.controller.user;

import com.shun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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
}
