package com.jms.study.点对点;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liuzhilei on 2017/5/17.
 */
@Service("/send")
public class Send extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            InitialContext context = new InitialContext();
            Queue queue = (Queue) context.lookup("java:comp/env/queue/queue0");

            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("java:comp/env/queue/connectionFactory");

            QueueConnection queueConnection = connectionFactory.createQueueConnection();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

            QueueSender queueSender = queueSession.createSender(queue);
            queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage textMessage = queueSession.createTextMessage("hello world");

            queueSender.send(textMessage);

            writer.write("message sent: " + textMessage.getText());

            ConnectionMetaData metaData = queueConnection.getMetaData();
            System.out.println("JMS的一些有用信息：" + JSON.toJSONString(metaData));

            queueConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
