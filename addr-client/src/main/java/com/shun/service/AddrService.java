package com.shun.service;

import com.shun.entity.Addr;

import java.util.List;

public interface AddrService {
    void add(Addr addr);
    void del(String[] ids);
    void update(Addr addr);
    Addr queryById(Integer id);
    List<Addr> queryByUserId(Integer userId);
}
