package com.shun.controller;

import com.shun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("findByPage")
    @ResponseBody
    public Map findByPage(Boolean _search, String searchField, String searchString, String searchOper, Integer page, Integer rows, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return orderService.findByPage(page, rows);
    }
}
