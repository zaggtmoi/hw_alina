package org.example.hw2.service;

import org.example.hw2.dao.UserDAO;
import org.example.hw2.exception.DaoException;
import org.example.hw2.model.User;

import java.time.Instant;

public class UserService {

    private final UserDAO dao;

    public UserService() {
        dao = new UserDAO();
    }

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public User getById(long id) {
        return dao.findById(id);
    }

    public void createNewUser(User user) throws DaoException {
        user.setCreatedAt(Instant.now());
        dao.save(user);
    }

    public void update(User user) throws DaoException {
        dao.update(user);
    }

    public void delete(long id) throws DaoException {
        dao.delete(dao.findById(id));
    }

}
