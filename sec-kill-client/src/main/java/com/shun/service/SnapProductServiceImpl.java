package com.shun.service;

import com.shun.dao.SnapProductDao;
import com.shun.entity.SnapProduct;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SnapProductServiceImpl implements SnapProductService {
    @Autowired
    private SnapProductDao snapProductDao;

    @Override
    public Map add(SnapProduct snapProduct) {
        Map map = new HashMap();
        snapProduct.setCreateDate(new Date());
        int i = snapProductDao.insertSelective(snapProduct);
        if(i==1){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return map;
    }

    @Override
    public Map findAll(Integer rows, Integer page) {
        Map map = new HashMap<>();
        int start = (page-1)*rows;
        List<SnapProduct> snapProducts = snapProductDao.findByPage(start,rows);
        int records = snapProductDao.selectCount(new SnapProduct());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",snapProducts);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Map findByExample(String searchField, String searchString, String searchOper, Integer page, Integer rows) {
        Map map = new HashMap();
        Example example = new Example(SnapProduct.class);
        int start = (page-1)*rows;
        switch (searchOper){
            case "cn":example.createCriteria().andLike(searchField,"%"+searchString+"%");break;
            case "eq":example.createCriteria().andEqualTo(searchField,searchString);break;
            case "ne":example.createCriteria().andNotEqualTo(searchField,searchString);break;
            default:break;
        }
        List<SnapProduct> users = snapProductDao.selectByExampleAndRowBounds(example, new RowBounds(start, rows));
        int records = snapProductDao.selectCountByExample(example);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Boolean deleteById(Integer id) {
        int i = snapProductDao.deleteByPrimaryKey(id);
        return i==1?true:false;
    }

    @Override
    public Boolean update(SnapProduct snapProduct) {
        int i = snapProductDao.updateByPrimaryKeySelective(snapProduct);
        return i==1?true:false;
    }
}
