package com.shun;

import com.shun.dao.OrderDao;
import com.shun.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderClientApplicationTests {
    @Autowired
    private OrderDao orderDao;

    @Test
    void contextLoads() {
        List<Order> orders = orderDao.selectAll();
        orders.forEach(order -> System.out.println(order) );
    }

}
