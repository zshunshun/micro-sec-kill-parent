package com.shun.controller.manager;

import com.shun.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("pro-cat-manager")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @RequestMapping("findByProductId")
    @ResponseBody
    public Map findByProductId(Integer productId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return productCategoryService.findByProductId(productId);
    }
}
