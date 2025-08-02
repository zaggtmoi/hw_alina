package org.example.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {
    private final BufferedReader reader;

    private static final String SKIP_LINE = "-";

    public ConsoleView() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }


    public ViewMenuItem getCommand() {
        do {
            System.out.println("Введите команду (get, add, update, delete, exit):");
            String command;
            try {
                do {
                    command = reader.readLine().toUpperCase();
                } while (command.isBlank());
                return ViewMenuItem.valueOf(command);
            } catch (IOException e) {
                throw new RuntimeException(e);
                //todo log
            } catch (IllegalArgumentException e) {
                System.out.println("Неверная команда, попробуйте снова.");
                //todo log
            }
        } while (true);
    }

    public long getId() {
        System.out.println("Введите id:");
        try {
            return Long.parseLong(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
            //todo log
        }
    }

    public void printUser(User user) {
        System.out.println(user.toString());
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
            throw new RuntimeException(e);
            //todo log
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
            throw new RuntimeException(e);
            //todo log
        }
    }
}
