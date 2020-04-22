package com.shun.controller;

import com.shun.entity.Addr;
import com.shun.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("addr")
public class AddrController {
    @Autowired
    private AddrService addrService;
    @RequestMapping("getById")
    @ResponseBody
    public Map getById(Integer id, HttpServletResponse response){
        Map map = new HashMap();
        Addr addr = addrService.queryById(id);
        if(addr!=null){
            map.put("status",200);
            map.put("id",addr.getId());
            map.put("name",addr.getName());
            map.put("addrInfo",addr.getAddrInfo());
            map.put("phone",addr.getPhone());
            map.put("userId",addr.getUserId());
            map.put("defaultStatus",addr.getDefaultStatus());
        }else{
            map.put("status",500);
        }
        return map;
    }
}
