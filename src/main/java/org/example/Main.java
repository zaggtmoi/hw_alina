package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(null, 5);
        System.out.println(map.get(null));
    }
}