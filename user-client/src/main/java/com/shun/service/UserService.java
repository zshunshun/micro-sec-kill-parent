package com.shun.service;


import com.shun.entity.User;

import java.io.OutputStream;
import java.util.Map;

public interface UserService {
    Map findAll(Integer page, Integer rows);
    Map findByExample(String searchField,String searchString,String searchOper,Integer page,Integer rows);
    Boolean changeStatus(Integer id,String status);
    Map findNewUsers();
    void getExcel(OutputStream outputStream);

    User login(User user);
}
