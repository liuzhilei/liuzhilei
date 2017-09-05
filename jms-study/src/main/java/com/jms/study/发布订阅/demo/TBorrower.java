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
 */
public class TBorrower implements MessageListener {

    private TopicConnection connection;
    private TopicSession session;
    private Topic topic;
    private double currentRate;//当前利率

    public TBorrower(String connectionFactory, String topicName, String rate) {
        currentRate = Double.valueOf(rate);

        try {
            //连接到提供者，并获得和jms的连接
            Context context = new InitialContext();
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup(connectionFactory);
            connection = topicConnectionFactory.createTopicConnection();

            //创建JMS会话
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            //查看请求和响应队列
            topic = (Topic) context.lookup(topicName);

            //创建消息监听器
            TopicSubscriber topicSubscriber = session.createSubscriber(topic);//这是非持久订阅者，当没活动的时候，就不会接收到消息
            /**
             * 持久订阅者是通过在JMS提供者中指定订阅者名称（通过配置或管理界面），并使用createDurableSubscriber方法来创建，可以让订阅名称作为参数之一。为<b>受管订阅者</b>
             * 当持久订阅者不是活的的时候，消息会被保存，一旦订阅者是活动的，就会发送保存的消息。如果长时间不活动，消息会积压造成资源浪费。
             * 对于持久订阅者来说，如果订阅者未连接，那么无论是持久性消息，还是非持久性消息，都会将消息保存到磁盘中，但是有细微差别：
             * 1.对于非持久性消息，当发送者发送到消息服务器后，消息服务器会立即通知发送者，如果在这段时间还没有将消息持久化，发生故障，就可能造成消息丢失
             * 2.对于持久性消息，当发送者发送到消息服务器后，消息服务器会先进行持久化，然后再通知发送者，所以消息不会丢失。
             *
             * 如果只调用该方法而不进行配置，就证明创建了一个<b>动态持久订阅者</b>,如果想让动态订阅者borrow1取消订阅，可以调用session.unsubscribe("borrower1")进行删除。
             * 因为如果不删除，当动态订阅者不再使用的时候，因为没有取消订阅，所以就必须为它来存储消息，占用资源，有可能耗尽内存
             * session.createDurableSubscriber(topic,"borrower1");
             */
            topicSubscriber.setMessageListener(this);

            //创建已经完成，启动连接。一旦启动连接，就可以接收消息，所以在启动前必须创建上面的消息监听器，将该订阅者注册为消息监听器
            connection.start();

            System.out.println("正在等待贷款申请...");

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            //从消息中获取数据
            BytesMessage msg = (BytesMessage) message;
            double newRate = msg.readDouble();

            //如果该比率比当前利率至少低于1个百分点，不能贷款
            if ((currentRate - newRate) >= 1.0) {
                System.out.println("不能贷款");
            } else {
                System.out.println("可以申请贷款");
            }
            System.out.println("等待利率更新...");
            /**
             * Session.CLIENT_ACKNOWLEDGE
             * 对于客户端确实模式，则需要使用msg.acknowledge();进行手动确认，如果在确实期间出现故障，会被JMSException异常
             * 捕获，这样我们可以在catch中应该做忽略重新传送消息的逻辑。
             * 利用这个模式，如果有很多条消息，确认了最后一条消息，也就意味着之前所有的消息都得到了确认
             */
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
        String rate = null;
        if (args.length == 3) {
            topicCF = args[0];
            topicName = args[1];
            rate = args[2];
        }
        TBorrower tBorrower = new TBorrower(topicCF, topicName, rate);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("TBorrower 应用启动");
        try {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.trim().length() == 0) {
                System.out.println("退出...");
                tBorrower.exit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
