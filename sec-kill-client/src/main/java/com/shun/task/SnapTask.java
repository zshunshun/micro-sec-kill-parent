package com.shun.task;

import com.shun.entity.SnapProduct;
import com.shun.service.SnapProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Async
public class SnapTask {
    @Autowired
    private SnapProductService snapProductService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Scheduled(cron = "0 0 0,8,10,11,12,13,14,16,18,20,22,23 * * ?")
    public void setSnap() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String dateStr = sdf.format(date);
        Date HourDate = sdf.parse(dateStr);
        Map map = snapProductService.searchByTime(HourDate, 1, 1000);
        List<SnapProduct> snapProducts = (List<SnapProduct>) map.get("rows");
        Set<String> keys = stringRedisTemplate.keys("snapCount*");
        if(keys!=null&&keys.size()>0){
            stringRedisTemplate.delete(keys);
        }
        if (snapProducts != null && snapProducts.size() > 0) {
            ListOperations<String, String> stringStringListOperations = stringRedisTemplate.opsForList();
            snapProducts.forEach(snapProduct -> {
                if (snapProduct.getVolume() < snapProduct.getCount()) {
                    int num = snapProduct.getCount()-snapProduct.getVolume();
                    List<String> nums = new ArrayList<>();
                    for (int i = 0;  i<num ; i++) {
                        nums.add("1");
                    }
                    stringStringListOperations.leftPushAll("snapCount_id="+snapProduct.getId(),nums);
                }
            });
        }
    }
}
