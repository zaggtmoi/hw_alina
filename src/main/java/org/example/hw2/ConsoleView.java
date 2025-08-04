package org.example.hw2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleView {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleView.class);
    private static final String SKIP_LINE = "-";
    private final BufferedReader reader;

    public ConsoleView() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }


    public ViewMenuItem getCommand() {
        do {
            System.out.println("Введите команду (get, add, update, delete, exit):");
            String command = "";
            try {
                do {
                    command = reader.readLine().toUpperCase();
                } while (command.isBlank());
                return ViewMenuItem.valueOf(command);
            } catch (IOException e) {
                logger.error("Can't get user command.", e);
                System.out.println("Не удалось получить команду.");
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                logger.error("Wrong input: {}", command);
                System.out.println("Неверная команда, попробуйте снова.");
            }
        } while (true);
    }

    public long getId() {
        System.out.println("Введите id:");
        try {
            return Long.parseLong(reader.readLine());
        } catch (IOException e) {
            logger.error("Can't get id", e);
            throw new RuntimeException(e);
        }
    }

    public void printUser(User user) {
        System.out.println(user == null ? "NO USER" : user.toString());
    }

    public User getNewUserData() {
        try {
            System.out.println("Введите имя:");
            String name = reader.readLine();
            System.out.println("Введите возраст:");
            String ageStr = reader.readLine();
            Integer age = ageStr.isBlank() ? null
                    : Integer.parseInt(ageStr);
            System.out.println("Введите e-mail:");
            String eMail = reader.readLine();
            return new User(name, eMail, age);
        } catch (IOException e) {
            logger.error("Can't get new user data", e);
            throw new RuntimeException(e);
        }
    }

    public void getUpdateData(User user) {
        System.out.print("Данные в системе: ");
        printUser(user);
        System.out.println("Укажите новые данные для полей или поставьте " + SKIP_LINE);
        try {
            System.out.println("Введите имя:");
            String name = reader.readLine();
            if (!SKIP_LINE.equals(name)) {
                user.setName(name);
            }
            System.out.println("Введите возраст:");
            String ageStr = reader.readLine();
            if (!SKIP_LINE.equals(ageStr)) {
                user.setAge(ageStr.isBlank() ? null : Integer.parseInt(ageStr));
            }
            System.out.println("Введите e-mail:");
            String eMail = reader.readLine();
            if (!SKIP_LINE.equals(eMail)) {
                user.setEmail(eMail);
            }
        } catch (IOException e) {
            logger.error("Can't get data for update", e);
            throw new RuntimeException(e);
        }
    }
}
