package com.shun.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.shun.dao.UserDao;
import com.shun.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.OutputStream;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Map findAll(Integer page, Integer rows) {
        Map map = new HashMap<>();
        int start = (page-1)*rows;
        List<User> users = userDao.selectByRowBounds(new User(), new RowBounds(start, rows));
        int records = userDao.selectCount(new User());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Map findByExample(String searchField, String searchString, String searchOper, Integer page, Integer rows) {
        Map map = new HashMap();
        Example example = new Example(User.class);
        int start = (page-1)*rows;
        switch (searchOper){
            case "cn":example.createCriteria().andLike(searchField,"%"+searchString+"%");break;
            case "eq":example.createCriteria().andEqualTo(searchField,searchString);break;
            case "ne":example.createCriteria().andNotEqualTo(searchField,searchString);break;
            default:break;
        }
        List<User> users = userDao.selectByExampleAndRowBounds(example, new RowBounds(start, rows));
        int records = userDao.selectCountByExample(example);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Boolean changeStatus(Integer id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userDao.updateByPrimaryKeySelective(user);
        return true;
    }

    @Override
    public Map findNewUsers() {
        Map map = new HashMap();
        //当天用户注册数
        Example example = new Example(User.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),0, 0, 0);
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","男");
        int countTodayMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","女");
        int countTodayFeman = userDao.selectCountByExample(example);
        //近一周用户注册数
        example.clear();
        calendar.add(Calendar.DATE,-7);
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","男");
        int countWeekMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","女");
        int countWeekFeman = userDao.selectCountByExample(example);
        //近一月用户注册数
        example.clear();
        calendar.add(Calendar.MONTH,-1);
        calendar.add(Calendar.DATE,7);
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","男");
        int countMonthMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","女");
        int countMonthFeman = userDao.selectCountByExample(example);
        //近一年用户注册数
        example.clear();
        calendar.add(Calendar.YEAR,-1);
        calendar.add(Calendar.MONTH,1);
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","男");
        int countYearMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createDate",calendar.getTime()).andEqualTo("sex","女");
        int countYearFeman = userDao.selectCountByExample(example);

        List listMan = Arrays.asList(countTodayMan,countWeekMan,countMonthMan,countYearMan);
        List listFeman = Arrays.asList(countTodayFeman,countWeekFeman,countMonthFeman,countYearFeman);

        map.put("listMan",listMan);
        map.put("listFeman",listFeman);
        map.put("status",200);
        return map;
    }

    @Override
    public void getExcel(OutputStream outputStream) {
        ExcelWriter build = EasyExcel.write(outputStream, User.class).build();
        WriteSheet wr = EasyExcel.writerSheet("微秒杀用户数据").build();
        List<User> userList = new ArrayList<>();
        int records = userDao.selectCount(new User());
        int rows = 10000;
        int total = records%rows==0?records/rows:records/rows+1;
        for(int i=0;i<total;i++) {
            userList = userDao.selectByRowBounds(new User(), new RowBounds(i*rows, rows));
            build.write(userList, wr);
        }
        build.finish();
    }

}
