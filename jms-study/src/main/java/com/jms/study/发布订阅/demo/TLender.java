package com.jms.study.发布订阅.demo;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liuzhilei on 2017/8/25.
 * 向一个主题发布新的抵押利率
 */
public class TLender {

    private TopicConnection connection;
    private TopicSession session;
    private Topic topic;

    public TLender(String connectionFactory, String topicName) {
        try {
            //连接到提供者，获得JMS连接
            Context context = new InitialContext();
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup(connectionFactory);
            connection = topicConnectionFactory.createTopicConnection();

            //创建JMS会话
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            //查找请求和相应队列
            topic = (Topic) context.lookup(topicName);

            //创建完成，启动连接
            connection.start();

        } catch (NamingException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void publishRate(double newRate) {
        try {
            //创建JMS消息
            BytesMessage bytesMessage = session.createBytesMessage();
            bytesMessage.writeDouble(newRate);

            //创建发布者并发布消息
            TopicPublisher publisher = session.createPublisher(topic);
            publisher.publish(bytesMessage);//默认，消息优先级设置为普通（4）；传送模式被设置成持久性消息；消息有效期为0，表示永不过期；可以用其他publish方法重写
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        String topicCF = null;
        String topicName = null;
        if (args.length == 2) {
            topicCF = args[0];
            topicName = args[1];
        } else {
            System.out.println("异常");
        }

        TLender lender = new TLender(topicCF, topicName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("TLender 应用启动...");
            System.out.println("按回车退出...");

            while (true) {
                System.out.println("> ");
                String rate = bufferedReader.readLine();
                if (rate == null || rate.trim().length() <= 0) {
                    lender.exit();
                }
                //解析交易说明
                double newRate = Double.valueOf(rate);
                lender.publishRate(newRate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
