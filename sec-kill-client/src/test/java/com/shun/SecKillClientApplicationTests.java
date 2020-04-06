package com.shun;

import com.shun.dao.SnapProductDao;
import com.shun.entity.SnapProduct;
import com.shun.feign.ProductFeign;
import com.shun.service.SnapProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SecKillClientApplicationTests {

    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private SnapProductService snapProductService;
    @Autowired
    private SnapProductDao snapProductDao;
    @Test
    void contextLoads() {
        Map map = snapProductService.findAll(10, 1);
        System.out.println(map);
    }
    @Test
    public void testDel(){
        Boolean aBoolean = snapProductService.deleteById(3);
        System.out.println(aBoolean);
    }
    @Test
    public void testFeign(){
        Map map = productFeign.testFeigns(10, 1);
        System.out.println(map);
    }
    @Test
    public void testDao(){
        List<SnapProduct> snap = snapProductDao.findByPage(0, 10);
        for (SnapProduct snapProduct : snap) {
            System.out.println(snapProduct);
        }
    }

}
