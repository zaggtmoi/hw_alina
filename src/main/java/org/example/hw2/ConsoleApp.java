package org.example.hw2;

import org.example.hw2.exception.DaoException;
import org.example.hw2.model.User;
import org.example.hw2.service.UserService;
import org.example.hw2.view.ConsoleView;
import org.example.hw2.view.ViewMenuItem;

public class ConsoleApp {

    public static void main(String[] args) {
        UserService userService = new UserService();
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
