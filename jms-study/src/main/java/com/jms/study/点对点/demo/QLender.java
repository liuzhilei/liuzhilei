package com.jms.study.点对点.demo;

import org.apache.ibatis.jdbc.Null;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liuzhilei on 2017/8/23.
 * 监听贷款申请队列上的贷款请求，判断工资额是否满足必要的商业要求，并最终将结果发回给借款方
 * <p/>
 * 该消费者实现了MessageListener接口监听消息，为异步方式
 * 如果消费者使用receive()方法，就是同步方式。例如{@link com.jms.study.点对点.demo.QBorrower#sendLoanRequest(double, double)}的
 * receive()，就是在同步等待QLender给QBorrower回复响应消息
 */
public class QLender implements MessageListener {

    private QueueConnection connection = null;
    private QueueSession session = null;
    private Queue requestQ = null;

    public QLender(String connectionFactory, String requestQueue) {
        try {
            //连接到提供者并获取JMS连接
            Context context = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup(connectionFactory);
            connection = queueConnectionFactory.createQueueConnection();

            //创建JMS会话
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            //获取请求队列
            requestQ = (Queue) context.lookup(requestQueue);

            //创建已经完成，启动连接
            connection.start();

            //创建消息监听器，创建完毕之后，就启动了一个单独的监听器线程，该线程将一直等待，直到接收到一条消息为止
            QueueReceiver receiver = session.createReceiver(requestQ);
            receiver.setMessageListener(this);//通过this关键字将QLender类设置为消息监听器。

            System.out.println("正在等待贷款申请......");

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            boolean accept = false;
            //从消息中获取数据
            MapMessage msg = (MapMessage) message;
            double salary = msg.getDouble("salary");
            double loanAmount = msg.getDouble("loanAmount");

            //决定是否接受或者拒绝贷款申请
            if (loanAmount <= 200000) {
                accept = true;
            }
            System.out.println(accept ? "接受" : "拒绝");

            //构造要发送给借款方的消息
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(accept ? "accept" : "reject");
            /**
             * 此处为消息关联。消息关联是用于确保能接收正确消息的一项技术。最常用的方法是
             * 将JMSCorrelationID消息头属性和JMSMessageID消息头属性结合使用。
             * JMSCorrelationID包含了一个发送者和接收者都直到的唯一String值，通常传入JMSMessageID，因为他是唯一的，而且
             * 发送者和接受者都可用
             */
            textMessage.setJMSCorrelationID(msg.getJMSMessageID());

            //创建发送者并发送消息
            QueueSender sender = session.createSender((Queue) message.getJMSReplyTo());
            sender.send(textMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        try {
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            String connectionFactory = "java:comp/env/queue/connectionFactory";
            String requestQueue = "java:comp/env/queue/queue0";
            QLender qLender = new QLender(connectionFactory, requestQueue);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("QLender 启动...");
            System.out.println("按下回车键退出...");
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.trim().length() <= 0) {
                qLender.exit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
