package org.example.hw2;

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
                        userService.createNewUser(user);
                        break;
                    }
                    case UPDATE: {
                        long id = view.getId();
                        User user = userService.getById(id);
                        view.getUpdateData(user);
                        userService.update(user);
                        break;
                    }
                    case DELETE: {
                        long id = view.getId();
                        userService.delete(id);
                        break;
                    }
            }
        }
    }
}
