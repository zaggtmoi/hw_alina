package org.example.hw2;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public User findById(long id) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            session.beginTransaction();

            return session.find(User.class, id);
        } catch (Exception e) {
            logger.error("Find err user id {}. {}", id, e.getMessage());
            return null;
        }
    }

    public void save(User user) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.persist(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Save err on user {}. {}", user.toString(), e.getMessage());
        }
    }

    public void update(User user) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();
        } catch (Exception e) {
            logger.error("Update err on user {}. {}", user.toString(), e.getMessage());
        }
    }

    public void delete(User user) {
        try (Session session = HibernateRunner.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.remove(user);

            transaction.commit();
        } catch (Exception e) {
            logger.error("Delete err user id {}. {}", user.getId().toString(), e.getMessage());
        }
    }
}
