package org.example.hw2.utils;

import org.example.hw2.model.User;

public class UserUtils {
    public static String print(User user) {
        return user == null ? "NO USER" : user.toString();
    }
}
