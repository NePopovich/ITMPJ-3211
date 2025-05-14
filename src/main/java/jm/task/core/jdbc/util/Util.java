package jm.task.core.jdbc.util;

import jm.task.core.jdbc.config.DataBaseConfig;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(PROPERTIES.getProperty(DataBaseConfig.DRIVER_KEY));
        Connection connection;
        connection = DriverManager.getConnection(
                PROPERTIES.getProperty(DataBaseConfig.URL_KEY),
                PROPERTIES.getProperty(DataBaseConfig.USERNAME_KEY),
                PROPERTIES.getProperty(DataBaseConfig.PASSWORD_KEY));
        return connection;
    }

    public static SessionFactory getSessionFactory(){
        return new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    private static void loadProperties() {
        try(var inputStream = Util.class.getClassLoader().getResourceAsStream("dbconfig.properties")){
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
