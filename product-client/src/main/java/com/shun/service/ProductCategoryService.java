package com.shun.service;

import com.shun.entity.ProductCategory;

import java.util.Map;

public interface ProductCategoryService {
    Map findByProductId(Integer productId);
    Boolean updateByProductId(ProductCategory productCategory);
    Boolean insert(ProductCategory productCategory);
    void del(Integer[] productIds);
}
