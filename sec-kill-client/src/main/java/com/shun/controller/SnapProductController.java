package com.shun.controller;

import com.shun.entity.SnapProduct;
import com.shun.feign.ProductFeign;
import com.shun.service.SnapProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(Integer productId, BigDecimal price, Integer count, String snapDate,String id, String oper, HttpServletResponse response) throws ParseException {
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        if("del".equals(oper)){
            snapProductService.deleteById(Integer.valueOf(id));
            map.put("status",200);
        }else{
            Date date = null;
            if(snapDate!=null&&snapDate.length()>0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                date = sdf.parse(snapDate.replace("T"," "));
            }
            SnapProduct snapProduct = new SnapProduct();
            snapProduct.setProductId(productId)
                    .setPrice(price)
                    .setCount(count)
                    .setSnapDate(date);
            if("edit".equals(oper)){
                snapProduct.setId(Integer.valueOf(id));
                Boolean updateResult = snapProductService.update(snapProduct);
                if (updateResult == true) {
                    map.put("status", 200);
                } else {
                    map.put("status", 500);
                }
            }else{
                map = snapProductService.add(snapProduct);
            }
        }
        return map;
    }
}
