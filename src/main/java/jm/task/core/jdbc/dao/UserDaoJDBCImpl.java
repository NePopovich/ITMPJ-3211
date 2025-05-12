package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Constants;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        onlyDoSQL(Constants.createUsersTableSQL);
    }

    public void dropUsersTable() {
        onlyDoSQL(Constants.dropUsersTableSQL);
    }

    public void saveUser(String name, String lastName, byte age) {
        onlyDoSQL(Constants.insertUsersInTableSQL, name, lastName, age);
    }

    public void removeUserById(long id) {
        onlyDoSQL(Constants.deleteUserByIdSQL, id);
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        ResultSet resultSet = doSQLWithReturn(Constants.selectAllUsersSQL);
        try {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                );
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        onlyDoSQL(Constants.cleanUsersTableSQL);
    }

    private static void onlyDoSQL(String sql, Object... param) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    statement.setObject(i + 1, param[i]);
                }
            }
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet doSQLWithReturn(String sql, Object... param) {
        ResultSet res = null;
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    statement.setObject(i + 1, param[i]);
                }
            }
            res = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
