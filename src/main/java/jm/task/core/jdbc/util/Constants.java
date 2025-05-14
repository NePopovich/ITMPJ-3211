package jm.task.core.jdbc.util;

public class Constants {
    public static final String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR, last_name VARCHAR, age smallint)";
    public static final String dropUsersTableSQL = "DROP TABLE IF EXISTS users";
    public static final String insertUsersInTableSQL = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
    public static final String deleteUserByIdSQL = "DELETE FROM users WHERE id=?";
    public static final String selectAllUsersSQL = "SELECT * FROM users";
    public static final String cleanUsersTableSQL = "DELETE FROM users";
}
