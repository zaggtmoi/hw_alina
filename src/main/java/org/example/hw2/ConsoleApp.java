package org.example.hw2;

import org.example.hw2.exception.DaoException;
import org.example.hw2.model.User;
import org.example.hw2.service.UserService;
import org.example.hw2.view.ConsoleView;
import org.example.hw2.view.ViewMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        ConsoleView view = new ConsoleView();
        ViewMenuItem command;
        while ((command = view.getCommand()) != ViewMenuItem.EXIT) {
            switch (command) {
                case GET: {
                    long id = view.getId();
                    User user = userService.getById(id);
                    view.printUser(user);
                    break;
                }
                case ADD: {
                    User user = view.getNewUserData();
                    try {
                        userService.createNewUser(user);
                    } catch (DaoException e) {
                        view.printError("Пользователь не сохранён.");
                    }
                    break;
                }
                case UPDATE: {
                    long id = view.getId();
                    User user = userService.getById(id);
                    view.getUpdateData(user);
                    try {
                        userService.update(user);
                    } catch (DaoException e) {
                        view.printError("Данные пользователя не обновлены.");
                    }
                    break;
                }
                case DELETE: {
                    long id = view.getId();
                    try {
                        userService.delete(id);
                    } catch (DaoException e) {
                        view.printError("Пользователь не удалён.");
                    }
                    break;
                }
            }
        }
    }
}
