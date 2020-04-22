package com.shun.service;

import com.shun.dao.AddrDao;
import com.shun.entity.Addr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class AddrServiceImpl implements AddrService {
    @Autowired
    private AddrDao addrDao;
    @Override
    public void add(Addr addr) {

    }

    @Override
    public void del(String[] ids) {

    }

    @Override
    public void update(Addr addr) {

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Addr queryById(Integer id) {
        Addr addr = new Addr();
        addr.setId(id);
        return addrDao.selectOne(addr);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Addr> queryByUserId(Integer userId) {
        return null;
    }
}
