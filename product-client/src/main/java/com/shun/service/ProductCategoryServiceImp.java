package com.shun.service;

import com.shun.annotation.AddCache;
import com.shun.annotation.DelCache;
import com.shun.dao.ProductCategoryDao;
import com.shun.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;


@Service
@Transactional
public class ProductCategoryServiceImp implements ProductCategoryService{
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache(value = "通过商品id查询")
    public Map findByProductId(Integer productId) {
        Map map = new HashMap();
        ProductCategory productCategory = productCategoryDao.selectOne(new ProductCategory(null, productId, null));
        if(productCategory==null){
            map.put("status",500);
        }else {
            map.put("status", 200);
            map.put("productCategory", productCategory);
        }
        return map;
    }

    @Override
    @DelCache(value = "通过商品id更新")
    public Boolean updateByProductId(ProductCategory productCategory) {
        Example example = new Example(ProductCategory.class);
        example.createCriteria().andEqualTo("productId",productCategory.getProductId());
        productCategoryDao.updateByExampleSelective(productCategory,example);
        return true;
    }

    @Override
    @DelCache(value = "添加商品的分类")
    public Boolean insert(ProductCategory productCategory) {
        productCategoryDao.insertSelective(productCategory);
        return true;
    }

    @Override
    @DelCache(value = "删除商品的分类")
    public void del(Integer[] productIds) {
        Example example = new Example(ProductCategory.class);
        example.createCriteria().andIn("productId",Arrays.asList(productIds));
        productCategoryDao.deleteByExample(example);
    }
}
