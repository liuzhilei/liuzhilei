package com.jms.study.发布订阅;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liuzhilei on 2017/5/18.
 */
public class Subscribe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            InitialContext context = new InitialContext();

            Topic topic = (Topic) context.lookup("java:comp/env/topic/topic0");

            TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("java:comp/env/topic/connectionFactory");

            TopicConnection connection = connectionFactory.createTopicConnection();

            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            TopicSubscriber subscriber = session.createSubscriber(topic);

            connection.start();

            TextMessage message = (TextMessage) subscriber.receive();

            writer.write("message received : " + message.getText());

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
