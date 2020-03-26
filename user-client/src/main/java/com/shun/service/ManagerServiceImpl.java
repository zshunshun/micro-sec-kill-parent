package com.shun.service;

import com.shun.dao.ManagerDao;
import com.shun.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Override
    public Manager login(Manager manager) {
        Example example = new Example(Manager.class);
        example.createCriteria().andEqualTo("username",manager.getUsername());
        Manager reManager = managerDao.selectOneByExample(example);
        String salt = reManager.getSalt();
        //针对密码加盐后MD5加密
        String rePassword = DigestUtils.md5DigestAsHex((manager.getPassword() + salt).getBytes());
        if(rePassword.equals(reManager.getPassword())){
            return reManager;
        }else{
            return null;
        }
    }

    @Override
    public Boolean logout(Manager manager) {
        return null;
    }
}
