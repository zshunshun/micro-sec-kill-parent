package com.shun;

import com.shun.dao.ProductCategoryDao;
import com.shun.dao.ProductDao;
import com.shun.entity.Product;
import com.shun.service.ProductCategoryService;
import com.shun.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductClientApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    void contextLoads() {
        List<Product> byPage = productDao.findByPage(0, 10);
        byPage.forEach(product -> System.out.println(product));
        System.out.println("aa");
    }
    @Test
    public void testProductDao(){
        Integer[] ids = {7};
        productCategoryService.del(ids);
    }
    @Test
    public void testProductService(){
        Integer[] ids = {9};
        productService.del(ids);
    }

}
