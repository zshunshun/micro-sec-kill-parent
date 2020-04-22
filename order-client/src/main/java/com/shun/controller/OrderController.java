package com.shun.controller;

import com.shun.entity.Order;
import com.shun.feign.AddrFeign;
import com.shun.feign.ProductFeign;
import com.shun.feign.SnapFeign;
import com.shun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddrFeign addrFeign;
    @Autowired
    private SnapFeign snapFeign;
    @Autowired
    private ProductFeign productFeign;
    @RequestMapping("findByPage")
    @ResponseBody
    public Map findByPage(Boolean _search, String searchField, String searchString, String searchOper, Integer page, Integer rows, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return orderService.findByPage(page, rows);
    }
    @JmsListener( destination= "snapOrder")
    public void Consumers(TextMessage msg){
        try {
            String text = msg.getText();
            String[] split = text.split("#");
            int userId = Integer.parseInt(split[0]);
            int snapId = Integer.parseInt(split[1]);
            int addrId = Integer.parseInt(split[2]);
            Map addrMap = addrFeign.getById(addrId);
            Map snapMap = snapFeign.getById(snapId);
            Map productMap = productFeign.getById((Integer) snapMap.get("productId"));
            Order order = new Order();
            order.setUserId(userId)
                    .setAddr(addrMap.get("addrInfor").toString())
                    .setCount(1)
                    .setName(addrMap.get("name").toString())
                    .setOriginalPrice(new BigDecimal(productMap.get("price").toString()))
                    .setPhone(addrMap.get("phone").toString())
                    .setProductName(productMap.get("name").toString())
                    .setSnapPrice(new BigDecimal(snapMap.get("price").toString()))
                    .setStatus("待确认")
                    .setTotalPrice(new BigDecimal(snapMap.get("price").toString()));
            orderService.add(order);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
