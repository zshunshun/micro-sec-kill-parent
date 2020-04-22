package com.shun.controller.user;

import com.shun.entity.SnapProduct;
import com.shun.feign.ProductFeign;
import com.shun.feign.UserFeign;
import com.shun.service.SnapProductService;
import lombok.extern.log4j.Log4j2;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("snap")
@Log4j2
public class SnapProductUserController {
    @Autowired
    private SnapProductService snapProductService;
    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private UserFeign userFeign;
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
    @RequestMapping("getById")
    @ResponseBody
    public Map getById(Integer id, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        SnapProduct snapProduct = snapProductService.findById(id);
        if(snapProduct==null){
            map.put("status",500);
        }else {
            map.put("status",200);
            map.put("id",snapProduct.getId());
            map.put("productId",snapProduct.getProductId());
            map.put("count",snapProduct.getCount());
            map.put("price",snapProduct.getPrice());
            map.put("createDate",snapProduct.getCreateDate());
            map.put("snapDate",snapProduct.getSnapDate());
        }
        return map;
    }
    @RequestMapping("snapStart")
    @ResponseBody
    public Map snapStart(String snapId,String userToken,String addrId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        //通过userToken获取用户id
        String userId = userFeign.getUserId(userToken);
        Map map = new HashMap();
        if(userId==null){
            map.put("status",500);
            return map;
        }
        String s = stringRedisTemplate.opsForList().leftPop("snapCount_id=" + snapId);
        if("1".equals(s)){
            ActiveMQTopic activeMQTopic = new ActiveMQTopic("snapOrder");
            jmsTemplate.convertAndSend(activeMQTopic,userId+"#"+snapId+"#"+addrId);
            map.put("status",200);
        }else{
            map.put("status",500);
        }
        return map;
    }

}
