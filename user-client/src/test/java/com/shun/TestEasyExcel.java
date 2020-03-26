package com.shun;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.shun.dao.UserDao;
import com.shun.entity.User;
import com.shun.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEasyExcel {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    private String fileName;
    @Before
    public void setzFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        fileName = "D:\\test_1.xls";
    }
    @Test
    public void test06() {
        ExcelWriter build = EasyExcel.write(new File(fileName), User.class).build();
        WriteSheet wr = EasyExcel.writerSheet("微秒杀用户数据").build();
        List<User> userList = new ArrayList<>();
        int records = userDao.selectCount(new User());
        int rows = 10;
        int total = records%rows==0?records/rows:records/rows+1;
        for(int i=0;i<total;i++) {
            userList = userDao.selectByRowBounds(new User(), new RowBounds(i*rows, rows));
            System.out.println(userList);
            build.write(userList, wr);
        }
        build.finish();
    }
}
