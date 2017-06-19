package com.liu.springtest.事务管理.传统jdbc事务管理;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by liuzhilei on 2017/6/18.
 * 传统jdbc对事务的管理
 */
public class JdbcDemo {

    private Connection getConnection() {
        Connection connection = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://test";
        String userName = "root";
        String password = "123456";

        try {
            //加载mysql驱动
            Class.forName(driver);
            //创建连接
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void update() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = this.getConnection();
            //设置不自动提交
            connection.setAutoCommit(false);
            String sql = "update user set user_name = 'username' where user_id = '1'";
            statement = connection.prepareStatement(sql);
            statement.execute();
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            //出现异常，事务回滚
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
