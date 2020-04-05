package com.shun.controller;

import com.shun.util.UploadUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("file")
@Log4j2
public class FileController {
    @RequestMapping("upload")
    @ResponseBody
    public Map upload(MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        try {
            String url = UploadUtil.getUrl("/img/cover", imgFile, request);
            map.put("error", 0);
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("url", "上传失败！");
        }
        return map;
    }
    @RequestMapping("uploadProduct")
    @ResponseBody
    public Map uploadProduct(MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        try {
            String url = UploadUtil.getUrl("/img/product", imgFile, request);
            map.put("error", 0);
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("url", "上传失败！");
        }
        return map;
    }
    @RequestMapping("findAllImage")
    @ResponseBody
    public Map findAllImage(HttpServletResponse response, HttpServletRequest request) throws UnknownHostException {
        response.setHeader("Access-Control-Allow-Origin","*");
        Map map = new HashMap();
        String ip = InetAddress.getLocalHost().toString().split("/")[1];
        int port = request.getServerPort();
        map.put("current_url","http://"+ip+":"+port+"/static/img/product/");
        List list = new ArrayList();
        String realPath = request.getServletContext().getRealPath("/img/product");
        File file = new File(realPath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            Map fileMap = new HashMap();
            fileMap.put("is_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            fileMap.put("filetype", FilenameUtils.getExtension(file1.getName()));
            fileMap.put("filename",file1.getName());
            String s = file1.getName().split("_")[0];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(s)));
            fileMap.put("datetime",format);
            list.add(fileMap);
            log.info(fileMap);
        }
        map.put("file_list",list);
        map.put("total_count",list.size());
        return map;
    }
}
