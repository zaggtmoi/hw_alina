package org.example.hw2.service;

import org.example.hw2.dao.UserRepository;
import org.example.hw2.exception.DaoException;
import org.example.hw2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void createNewUser(User user) throws DaoException {
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
    }

    public void update(User user) throws DaoException {
        userRepository.save(user);
    }

    public void delete(long id) throws DaoException {
        userRepository.deleteById(id);
    }

}
