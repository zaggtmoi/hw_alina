package org.example.hw2;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository {

    public User findById(int id) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            return session.find(User.class, id);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public void save(User user) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.

            transaction.commit();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void update(User user) {
        Session session = HibernateRunner.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateRunner.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }
}
