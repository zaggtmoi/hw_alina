package org.example.hw2;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {

    public User findById(long id) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            session.beginTransaction();

            return session.find(User.class, id);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public void save(User user) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.persist(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void update(User user) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void delete(User user) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.remove(user);

            transaction.commit();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
