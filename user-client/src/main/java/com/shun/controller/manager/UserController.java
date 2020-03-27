package com.shun.controller.manager;

import com.shun.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user-manager")
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("findAll")
    @ResponseBody
    public Map findByPage(Boolean _search,String searchField,String searchString,String searchOper,Integer page,Integer rows,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        if(_search){
            return userService.findByExample(searchField,searchString,searchOper,page,rows);
        }else {
            return userService.findAll(page, rows);
        }
    }
    @RequestMapping("changeStatus")
    @ResponseBody
    public Map changeStatus(Integer id,String status,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        log.info(id);
        log.info(status);
        userService.changeStatus(id,status);
        map.put("status","ok");
        return map;
    }
    @RequestMapping("findNewUsers")
    @ResponseBody
    public Map findNewUsers(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return userService.findNewUsers();
    }
    @RequestMapping("getEXCEL")
    public void getEXCEL(HttpServletResponse response) throws IOException {
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("users.xls","UTF-8"));
        userService.getExcel(response.getOutputStream());
    }
}
