package org.example.hw2;

import java.time.Instant;

public class UserService {

    private final UserDAO dao;

    public UserService() {
        dao = new UserDAO();
    }

    public User getById(long id) {
        return dao.findById(id);
    }

    public void createNewUser(User user) {
        user.setCreatedAt(Instant.now());
        dao.save(user);
    }

    public void update(User user) {
        dao.update(user);
    }

    public void delete(long id) {
        dao.delete(dao.findById(id));
    }
}
