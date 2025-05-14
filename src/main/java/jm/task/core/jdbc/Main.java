package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        UserService userService = getUserService(new UserDaoJDBCImpl());
        userService.createUsersTable();

        for (int i = 0; i < 2; i++) {
            User user = createUser();
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        }

        userService.getAllUsers().forEach(e -> logger.info(e.toString()));
        userService.removeUserById(1L);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

    private static UserService getUserService(UserDao dao){
        return new UserServiceImpl(dao);
    }

    private static User createUser() {
        Scanner in = new Scanner(System.in);
        System.out.print("Имя: ");
        String name = in.nextLine();
        System.out.print("Фамилия: ");
        String lastName = in.nextLine();
        System.out.print("Возраст: ");
        Byte age = in.nextByte();
        return new User(name, lastName, age);
    }
}
