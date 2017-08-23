package com.jms.study.点对点.demo;

import com.alibaba.fastjson.JSON;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liuzhilei on 2017/8/22.
 * jms的基本通讯方式有三种，默认的两种是点对点，发布订阅，另一个不是默认的是请求应答模式
 * 该例子就是请求应答模式
 * 申请借款，并接收返回信息。
 */
public class QBorrower {

    private QueueConnection connection = null;
    private QueueSession session = null;
    private Queue responseQ = null;
    private Queue requestQ = null;

    public QBorrower(String connectionFactory, String requestQueue, String responseQueue) {
        try {
            //连接提供者并获取JMS连接
            Context context = new InitialContext();//构造初始化上下文
            QueueConnectionFactory qFactory = (QueueConnectionFactory) context.lookup(connectionFactory);//找到指定的类
            connection = qFactory.createQueueConnection();
            /**
             * 可以指定用户名和密码，认证失败抛出异常
             * connection = qFactory.createQueueConnection("username", "password");
             */

            /**
             * 创建JMS会话。JMS Session对象是JMS中的工作线程和事务性工作单元
             *
             * 第一个参数表示QueueSession是否是事务性的：
             *      true表示是事务性的，表示只有当session调用commit方法后，session发送到队列的消息才会传送给接收者，同样，
             * 在session上调用了rollback方法，会删除事务性会话期间发送的所有消息
             *
             * 第二个参数表示确认模式，如果会话是事务性的，就会忽略确认模式
             */
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            //查询请求和响应队列
            requestQ = (Queue) context.lookup(requestQueue);
            responseQ = (Queue) context.lookup(responseQueue);

            //现在完成创建，启动连接。如果只是发送消息，就不需要启动该连接，一般推荐启动
            connection.start();

            //这是获取连接的元数据，ConnectionMetaData包含了jms版本，jms提供者名称等有用信息
            ConnectionMetaData metaData = connection.getMetaData();
            System.out.println("JMS的一些有用信息：" + JSON.toJSONString(metaData));
        } catch (NamingException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 发送借款请求
     *
     * @param salary
     * @param loanAmt
     */
    private void sendLoanRequest(double salary, double loanAmt) {
        try {
            //创建JMS消息，message必须从session中获取
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setDouble("salary", salary);
            mapMessage.setDouble("loanAmount", loanAmt);
            /**
             * 为响应队列设置JMSReplyTo属性，进一步解除生产者和消费者之间的耦合
             * 在使用请求/应答模式的时候，通常在消息生产者中设置JMSReplyTo头属性，而不是在消息消费者指定应答队列，
             */
            mapMessage.setJMSReplyTo(responseQ);

            //创建发送者并 发送消息
            QueueSender sender = session.createSender(requestQ);
            sender.send(mapMessage);//默认 消息优先级设置为普通（4）；传送模式被设置成持久性消息；消息有效期为0，表示永不过期；可以用send其他方法重写
            //消息被发送出去，QBorrower就会被阻塞，并等待QLender关于贷款被批准或者拒绝的响应

            //等待查看贷款申请被接受或者拒绝。指定了过滤器，表明只有JMSCorrelationID和原始的JMSMessageID相等时才会接收消息
            String filter = "JMSCorrelationID = '" + mapMessage.getJMSMessageID() + "'";
            QueueReceiver receiver = session.createReceiver(responseQ, filter);
            TextMessage textMessage = (TextMessage) receiver.receive(30000);//阻塞等待，直到响应消息被接收为止
            if (textMessage == null) {
                System.out.println("QLender not responding");
            } else {
                System.out.println("Loan request was " + textMessage.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        try {
            //关闭connection对象，将会关闭所有和该了解相关的session对象
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        String connectionFactory = "java:comp/env/queue/connectionFactory";
        String requestQueue = "java:comp/env/queue/queue0";
        String responseQueue = "java:comp/env/queue/queue1";

        QBorrower borrower = new QBorrower(connectionFactory, requestQueue, responseQueue);

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("借款应用启动...");
            System.out.println("按下回车结束应用...");
            System.out.println("输入格式：金额，借款总额");

            while (true) {
                System.out.println("> ");
                String loanRequest = bufferedReader.readLine();
                if (loanRequest == null || loanRequest.trim().length() <= 0) {
                    borrower.exit();
                }

                //解析交易信息
                String[] split = loanRequest.split(",");
                double salary = Double.valueOf(split[0]);
                double loanAmt = Double.valueOf(split[1]);
                //发送消息
                borrower.sendLoanRequest(salary, loanAmt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
