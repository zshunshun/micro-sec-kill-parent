package com.shun;

import com.alibaba.fastjson.JSON;
import com.shun.entity.Manager;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class UtilTest {
    @Test
    public void testSerializeUtil(){
        Manager manager = new Manager();
        manager.setId(0).setUsername("shun").setPassword("123343").setSalt("jksdfjsd");
        String s = JSON.toJSONString(manager);
        Manager m = JSON.parseObject(s, Manager.class);
        System.out.println(m);
    }
    @Test
    public void testMD5(){
        String salt = UUID.randomUUID().toString().replace("-", "");
        System.out.println(salt);
        String s = DigestUtils.md5DigestAsHex(("123456"+salt).getBytes());
        System.out.println(s);
    }
    @Test
    public void testJson(){
    }
}
