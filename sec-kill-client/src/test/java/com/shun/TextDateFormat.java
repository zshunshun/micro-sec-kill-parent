package com.shun;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;

import javax.jms.*;
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
    @Test
    public void testProduct() throws Exception {

        String brokeURL="tcp://101.200.57.35:61616";

        //1.连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokeURL);

        //2.创建连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //3.创建会话
        //第一个参数 代表第二参数是否生效开启事务  参数二 自动发送回执
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //4.创建生产者
        Destination destination=new ActiveMQQueue("javaQueue");
        MessageProducer producer = session.createProducer(destination);

        //5.创建消息
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("hello bbb");

        //6.使用生产者发生消息
        producer.send(textMessage);

        //7.提交
        session.commit();

        //8.关闭
        producer.close();
        session.close();
        connection.close();

    }
    //消费者
    @Test
    public void testConsumer() throws JMSException {

        String brokeURL="tcp://101.200.57.35:61616";

        //1.连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokeURL);

        //2.创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();  //启动连接

        //3.创建会话
        //第一个参数 代表第二参数是否生效开启事务  参数二 自动发送回执
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        //4.创建生产者
        Destination destination=new ActiveMQQueue("javaQueue");
        MessageConsumer consumer = session.createConsumer(destination);

        //6.使用生产者发生消息
        while(true){
            TextMessage message = (TextMessage)consumer.receive();
            String text = message.getText();
            if(message!=null){
                System.out.println(text);

                //7.提交
                session.commit();
            }else{
                break;
            }
        }

    }
}
