package com.shun;

import com.shun.dao.ProductCategoryDao;
import com.shun.dao.ProductDao;
import com.shun.entity.Product;
import com.shun.feign.CategoryFeign;
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
    @Autowired
    private CategoryFeign categoryFeign;
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
    @Test
    public void testSearchProduct() {
        List<Product> products = productDao.searchByField("p.id", "1", "eq", 0, 10);
        products.forEach(product -> System.out.println(product));
        int i = productDao.selectCountByField("p.id", "1", "eq", 0, 10);
        System.out.println(i);
    }
    @Test
    public void testFeign(){
        List<Integer> ids = categoryFeign.searchByName("小","cn");
        System.out.println(ids.size());
        ids.forEach(id-> System.out.println(id));
    }

}
