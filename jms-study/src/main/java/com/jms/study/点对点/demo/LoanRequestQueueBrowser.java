package com.jms.study.点对点.demo;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Enumeration;

/**
 * Created by liuzhilei on 2017/8/24.
 * 利用QueueBrowser分析一个队列。
 * QueueBrowser获取的是队列的一个快照，不会消费消息，也不能保证消息的准确清单
 */
public class LoanRequestQueueBrowser {

    public static void main(String[] args) {
        String connectionFactory = "java:comp/env/queue/connectionFactory";
        String requestQueue = "java:comp/env/queue/queue0";
        try {
            //创建一个JMS连接
            Context context = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup(connectionFactory);
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            //创建会话
            Queue queue = (Queue) context.lookup(requestQueue);
            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            //获取QueueBrowser对象，分析这个队列。
            QueueBrowser queueBrowser = queueSession.createBrowser(queue);
            Enumeration enumeration = queueBrowser.getEnumeration();
            if (enumeration.hasMoreElements()) {
                TextMessage textMessage = (TextMessage) enumeration.nextElement();
                System.out.println(textMessage.getText());
            }

            queueBrowser.close();
            queueConnection.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
