package com.jms.study.点对点;

import org.springframework.stereotype.Service;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liuzhilei on 2017/5/17.
 */
@Service
public class Receive extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            InitialContext context = new InitialContext();
            Queue queue = (Queue) context.lookup("java:comp/env/queue/queue0");

            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("java:comp/env/queue/connectionFactory");

            QueueConnection connection = connectionFactory.createQueueConnection();

            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueReceiver receiver = session.createReceiver(queue);

            connection.start();

            TextMessage message = (TextMessage) receiver.receive();

            writer.write("receive：" + message.getText());
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
