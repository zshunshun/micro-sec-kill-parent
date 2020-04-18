package com.shun;

import org.junit.jupiter.api.Test;

import java.security.PrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextDateFormat {
    @Test
    public void formatDate() throws ParseException {
        String dateStr = "2020-04-13 12";
        String dateStr1 = "2020-04-13 00";
        System.out.println(formatString(dateStr));
        System.out.println(formatString(dateStr1));
    }
    public String formatString(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date parse = sdf.parse(dateStr);
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
        String format = sdf1.format(parse);
        return format;
    }
    @Test
    public void testDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String now_hour = sdf.format(date);
        String[] hours = new String[]{"0", "8", "10", "11", "12", "13", "14", "16", "18", "20", "22", "23"};
        for (String hour : hours) {
            if(now_hour.equals(hour)){
                System.out.println(now_hour);
            }
        }
    }
}
