package com.shun;

import com.shun.dao.ManagerDao;
import com.shun.dao.UserDao;
import com.shun.entity.Manager;
import com.shun.entity.User;
import com.shun.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserClientApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private ManagerService managerService;
    @Test
    void contextLoads() {
        List<Manager> managers = managerDao.selectAll();
        managers.forEach(manager -> System.out.println(manager));
    }
    @Test
    void testManagerService(){
        Manager shun = managerService.login(new Manager(0, "sun", "123456", null));
        System.out.println(shun);
    }
    @Test
    public void testAddUser(){
        for(int i = 0;i<20;i++){
            addUser();
        }
    }
    public void addUser(){
        User user = new User();
        user.setUsername(getUsername()+UUID.randomUUID().toString().substring(0,5))
                .setSalt(UUID.randomUUID().toString().replace("-",""))
                .setNickname(getName())
                .setPhone(getMobile())
                .setPic("http://localhost:1208/static/manager/img/avatar.jpg")
                .setSex(getOneOfMore("男","女"))
                .setCreateDate(getDate())
                .setLoginDate(getDate())
                .setStatus("正常");
        user.setPassword(DigestUtils.md5DigestAsHex((getPassword(6)+user.getSalt()).getBytes()));
        userDao.insert(user);
    }
    //随机生成用户名
    public String getUsername(){
        String[] firstNameArray = { "Li", "Wang", "Zhang", "Liu", "Chen", "Yang", "Zhao", "Huang","Zhong", "ZHou", "Wu", "Xu", "Sun", "Hu", "Zhu", "Gao", "Ouyang",
                "TaiShi", "DuanMu", "Shang", "SiMa" };// 20个姓，其中5个复姓
        String[] lastNameArray = { "Wei", "Yong", "Jun", "Lei", "Tao", "Bin", "Qiang", "Peng", "Jie", "Feng", "Chao", "Bo", "Hui", "Gang", "Jian", "Ming", "Liang",
                "Jun", "Fei", "Kai", "Hao", "Hua", "Ping", "Xin", "Yi", "Lin", "Yang", "Yu", "Min", "Ning"};// 80个常用于名字的单字
        Random rd = new Random();
        int firstPos = rd.nextInt(21);
        StringBuilder name = new StringBuilder(firstNameArray[firstPos]);
        int lastLen = rd.nextInt(2)+1;
        /*
         * 为了各函数的统一性，此处也用for循环实现 int lastPos1 = (int) (80 * random()); String lastName =
         * lastNameArray[lastPos1]; if (lastLen == 2) { int lastPos2 = (int) (80 *
         * random()); lastName = lastName + lastNameArray[lastPos2]; }
         */
        for (int i = 0; i < lastLen; i++) {
            int lastPos = rd.nextInt(lastNameArray.length);
            System.out.println(lastPos);
            name.append(lastNameArray[lastPos]);
        }
        return name.toString();
    }
    //随机生成一个日期 当天 一周内 一月内 一年内
    public Date getDate() {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        int i = random.nextInt(4);
        int pow = (int)Math.pow(i, 4);
        calendar.add(Calendar.DAY_OF_YEAR,-pow);
        return calendar.getTime();
    }
    //随机生成一个省份
    public  String getOneProvince() {
        String[] cities = { "北京","天津","上海","重庆","河北","河南","云南","辽宁","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西","甘肃","山西","陕西","吉林","福建","贵州","广东","青海","西藏","四川","宁夏","海南","台湾","香港","澳门","内蒙古","黑龙江","南海诸岛"};
        Random random = new Random();
        int i = random.nextInt(cities.length);
        return cities[i];
    }
    //随机生成n个字符串的其中一个
    public  String getOneOfMore(String ...o) {
        Random random = new Random();
        int i = random.nextInt(o.length);
        return o[i];
    }
    //随机生成一个密码
    public  String getPassword(Integer size) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    //随机生成一个邮箱
    public  String getEmail() {
        StringBuffer m = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<8;i++) {
            m.append(rd.nextInt(10));
        }
        m.append("@qq.com");
        return m.toString();
    }
    //随机生成一个电话
    public  String getMobile() {
        StringBuffer m = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<11;i++) {
            m.append(rd.nextInt(10));
        }
        return m.toString();
    }
    //随机生成一个姓名
    public  String getName() {
        String[] firstNameArray = { "李", "王", "张", "刘", "陈", "杨", "赵", "黄","钟", "周", "吴", "徐", "孙", "胡", "朱", "高", "欧阳",
                "太史", "端木", "上官", "司马" };// 20个姓，其中5个复姓
        String[] lastNameArray = { "伟", "勇", "军", "磊", "涛", "斌", "强", "鹏", "杰", "峰", "超", "波", "辉", "刚", "健", "明", "亮",
                "俊", "飞", "凯", "浩", "华", "平", "鑫", "毅", "林", "洋", "宇", "敏", "宁", "建", "兵", "旭", "雷", "锋", "彬", "龙", "翔",
                "阳", "剑", "静", "敏", "燕", "艳", "丽", "娟", "莉", "芳", "萍", "玲", "娜", "丹", "洁", "红", "颖", "琳", "霞", "婷", "顺","慧",
                "莹", "晶", "华", "倩", "英", "佳", "梅", "雪", "蕾", "琴", "璐", "伟", "云", "蓉", "青", "薇", "欣", "琼", "宁", "平",
                "媛" };// 80个常用于名字的单字
        Random rd = new Random();
        int firstPos = rd.nextInt(21);
        StringBuilder name = new StringBuilder(firstNameArray[firstPos]);
        int lastLen = rd.nextInt(2)+1;
        /*
         * 为了各函数的统一性，此处也用for循环实现 int lastPos1 = (int) (80 * random()); String lastName =
         * lastNameArray[lastPos1]; if (lastLen == 2) { int lastPos2 = (int) (80 *
         * random()); lastName = lastName + lastNameArray[lastPos2]; }
         */
        for (int i = 0; i < lastLen; i++) {
            int lastPos = rd.nextInt(81);
            name.append(lastNameArray[lastPos]);
        }
        return name.toString();
    }
}
