package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    public void createUsersTable() {
        dao.createUsersTable();
        logger.severe("Таблица users создана!");
    }

    public void dropUsersTable() {
        dao.dropUsersTable();
        logger.severe("Таблица users удалена!");
    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
        String msg = "User с именем - " + name + " добавлен в базу данных";
        logger.severe(msg);
    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
        String msg = "User с id - " + id + " удален из базы данных";
        logger.severe(msg);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
        logger.severe("Таблица users очищена!");
    }
}
