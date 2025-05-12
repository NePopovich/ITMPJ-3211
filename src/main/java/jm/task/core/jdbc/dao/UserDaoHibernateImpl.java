package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Constants;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        noReturnAction(session -> session.createNativeQuery(Constants.createUsersTableSQL).executeUpdate());
    }

    @Override
    public void dropUsersTable() {
        noReturnAction(session -> session.createNativeQuery(Constants.dropUsersTableSQL).executeUpdate());
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        noReturnAction(session -> session.save(new User(name, lastName, age)));
    }

    @Override
    public void removeUserById(long id) {
        noReturnAction(session -> session.delete(session.get(User.class, id)));
    }

    @Override
    public List<User> getAllUsers() {
        return returnAction(session -> session.createQuery("from User").getResultList());
    }

    @Override
    public void cleanUsersTable() {
        noReturnAction(session -> session.createNativeQuery(Constants.cleanUsersTableSQL));
    }

    private static void noReturnAction(Consumer<Session> action) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static <T> T returnAction(Function<Session, T> action) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = action.apply(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
