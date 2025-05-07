package jm.task.core.jdbc.util;

import jm.task.core.jdbc.config.DataBaseConfig;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DataBaseConfig.DRIVER);
        Connection connection;
        connection = DriverManager.getConnection(DataBaseConfig.URL,
                DataBaseConfig.USERNAME,
                DataBaseConfig.PASSWORD);
        return connection;
    }

    public static SessionFactory getSessionFactory(){
        return new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
