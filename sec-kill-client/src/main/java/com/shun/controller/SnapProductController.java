package com.shun.controller;

import com.shun.entity.SnapProduct;
import com.shun.feign.ProductFeign;
import com.shun.service.SnapProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("snapProduct")
public class SnapProductController {
    @Autowired
    private SnapProductService snapProductService;
    @Autowired
    private ProductFeign productFeign;
    @RequestMapping("findByPage")
    @ResponseBody
    public Map findByPage(Boolean _search,String searchField,String searchString,String searchOper,Integer page,Integer rows,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return snapProductService.findAll(rows, page);
    }
}
