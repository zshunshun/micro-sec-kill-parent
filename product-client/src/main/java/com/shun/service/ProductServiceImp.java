package com.shun.service;

import com.shun.dao.ProductCategoryDao;
import com.shun.dao.ProductDao;
import com.shun.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findByPage(Integer rows, Integer page) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        List<Product> products = productDao.findByPage(start, rows);
        //List<Product> products = productDao.selectByRowBounds(new Product(), new RowBounds(start, rows));
        int records = productDao.selectCount(new Product());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",products);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Boolean update(Product product) {
        Example example = new Example(Product.class);
        example.createCriteria().andEqualTo("id",product.getId());
        productDao.updateByExampleSelective(product,example);
        return true;
    }

    @Override
    public Integer insert(Product product) {
        productDao.insert(product);
        return product.getId();
    }

    @Override
    public void del(Integer[] ids) {
        productDao.deleteByIdList(Arrays.asList(ids));
    }

    @Override
    public Map searchByField(String searchField, String searchString, String searchOper, Integer page, Integer rows) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        List<Product> users = productDao.searchByField(searchField,searchString,searchOper,start,rows);
        int records = productDao.selectCountByField(searchField,searchString,searchOper,start,rows);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Map searchByCategoryIds(List<Integer> ids, Integer page, Integer rows) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        List<Product> users = productDao.searchByCategoryIds(ids,start,rows);
        int records = productDao.selectCountByCategoryIds(ids,start,rows);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }
}