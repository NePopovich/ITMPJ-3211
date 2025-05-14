package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alesha", "Larkov", (byte) 30);
        userService.saveUser("Ivan", "Ivanov", (byte) 20);
        userService.saveUser("Dima", "Malinov", (byte) 25);
        userService.saveUser("Petr", "Petrov", (byte) 45);
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(1L);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
