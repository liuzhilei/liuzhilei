package com.jms.study.消息组处理;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2017/9/7.
 */
public class JMSReceiver implements MessageListener {

    private List<String> messageList = new ArrayList<String>();

    public JMSReceiver() {
        try {
            Context context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("connectionFactory");
            QueueConnection queueConnection = connectionFactory.createQueueConnection();
            QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("queue");
            QueueReceiver receiver = queueSession.createReceiver(queue);
            receiver.setMessageListener(this);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message.propertyExists("SequenceMaker")) {
                String marker = message.getStringProperty("SequenceMaker");

                //如果消息是启动标记，清除消息缓冲器
                if (marker.equals("start")) {
                    /**
                     * 由于消息是作为一个组来传送和确认的，任何故障都会导致第一个序列消息被标记为正在重新传送，我们并不关心其他消息
                     */
                    if (message.getJMSRedelivered()) {
                        processCompensatingTransaction();
                    }
                    messageList.clear();
                }
                //如果是结束标记，那么处理消息，并且手动确认
                if (marker.equals("end")) {
                    //处理消息
                    System.out.println("Messages: ");
                    for (String msg : messageList) {
                        System.out.println(msg);
                    }
                    //确认已经接受到所有消息
                    message.acknowledge();
                }

                //如果消息时候我们传递的有效消息，保存消息的内容
                if (message instanceof TextMessage) {
                    processInterimMessage(((TextMessage) message).getText());
                }

                System.out.println("等待下一条消息...");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    //对前面的消息设置进行反向处理
    public void processCompensatingTransaction() {
        messageList.clear();
    }

    //处理临时消息
    public void processInterimMessage(String msg) {
        messageList.add(msg);
    }


}
