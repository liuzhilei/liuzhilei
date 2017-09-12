package com.jms.study.消息组处理;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by liuzhilei on 2017/9/7.
 * 点对点方式  演示消息组的处理
 */
public class JMSSender {
    private QueueConnectionFactory connectionFactory;
    private QueueConnection connection;
    private QueueSession session;
    private QueueSender sender;

    public JMSSender() {
        try {
            Context context = new InitialContext();
            connectionFactory = (QueueConnectionFactory) context.lookup("connectionFactory");
            connection = connectionFactory.createQueueConnection();
            connection.start();
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("");
            sender = session.createSender(queue);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JMSSender jmsSender = new JMSSender();
        jmsSender.sendMessageGroup();
    }

    public void sendMessageGroup() {
        try {
            sendSequenceMarker("start");
            sendMessage("1");
            sendMessage("2");
            sendMessage("3");
            sendSequenceMarker("end");

            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    //发送一条带有序列标记的消息
    private void sendSequenceMarker(String marker) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        message.setStringProperty("SequenceMaker", marker);
        message.setStringProperty("JMSXGroupId", "group1");
        sender.send(message);
    }

    //发送一条普通的消息
    private void sendMessage(String text) {
        try {
            TextMessage textMessage = session.createTextMessage();
            //如果使用消息组，就必须使用JMSXGroupId属性
            textMessage.setStringProperty("JMSXGroupId", "group1");
            textMessage.setText(text);
            sender.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
