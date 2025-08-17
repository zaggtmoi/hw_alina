package org.example.hw2.dao;

import org.example.hw2.exception.DaoException;
import org.example.hw2.model.User;
import org.example.hw2.utils.UserUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private static final String clazzName = UserDAO.class.getName();
    private final SessionFactory sessionFactory;

    public UserDAO() {
        sessionFactory = HibernateRunner.getSessionFactory();
    }

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            return session.find(User.class, id);
        } catch (Exception e) {
            logger.error("Find err user id {}. {}", id, e.getMessage());
            return null;
        }
    }

    public void save(User user) throws DaoException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(user);
            logger.info(user.getId().toString());

            transaction.commit();
        } catch (Exception e) {
            logger.error("Save err on user {}. {}", UserUtils.print(user), e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException();
        }
    }

    public void update(User user) throws DaoException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();
        } catch (Exception e) {
            logger.error("Update err on user {}. {}", UserUtils.print(user), e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException();
        }
    }

    public void delete(User user) throws DaoException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.remove(user);

            transaction.commit();
        } catch (Exception e) {
            String id = (user == null || user.getId() == null) ? "null" : user.getId().toString();
            logger.error("Delete err user id {}. {}", id, e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException();
        }
    }
}
