package com.shun.service;

import com.shun.dao.OrderDao;
import com.shun.entity.Order;
import org.apache.ibatis.session.RowBounds;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findByPage(Integer page, Integer rows) {
        Map map = new HashMap<>();
        int start = (page-1)*rows;
        List<Order> orders = orderDao.selectByRowBounds(new Order(),new RowBounds(start,rows));
        int records = orderDao.selectCount(new Order());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",orders);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public void add(Order order) {
        order.setCreateDate(new Date());
        orderDao.insert(order);
    }
}
