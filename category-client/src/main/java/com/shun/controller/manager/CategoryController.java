package com.shun.controller.manager;

import com.shun.entity.Category;
import com.shun.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("category")
@Log4j2
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("findByPage")
    @ResponseBody
    public Map findByPage(Integer page, Integer rows, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return categoryService.findByPage(page,rows);
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(String[] id, String name, String parentId, String oper, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        if("edit".equals(oper)){
            map = categoryService.edit(new Category(Integer.valueOf(id[0]),name,null));
        }else if("add".equals(oper)){
            map = categoryService.add(name);
        }else if("del".equals(oper)){
            map = categoryService.del(id);
        }
        return map;
    }
    @RequestMapping("findByParentId")
    @ResponseBody
    public Map findByParentId(Integer page,Integer rows,Integer parentId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return categoryService.findByParentId(page,rows,parentId);
    }
    @RequestMapping("editChild")
    @ResponseBody
    public Map editChild(String[] id, String name, String parentId, String oper, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        if("edit".equals(oper)){
            map = categoryService.edit(new Category(Integer.valueOf(id[0]),name,null));
        }else if("add".equals(oper)){
            map = categoryService.addChild(Integer.valueOf(parentId),name);
        }else if("del".equals(oper)){
            map = categoryService.del(id);
        }
        return map;
    }
}
