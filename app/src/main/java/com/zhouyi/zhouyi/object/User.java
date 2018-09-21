package com.zhouyi.zhouyi.object;

public class User {
    private static String name;
    private static String account;
    private static String password;

    public static void setName(final String name) {
        User.name = name;
    }
    public static String getName() {
        return User.name;
    }

    public static void setAccount(final String account) {
        User.account = account;
    }
    public static String getAccount() {
        return User.account;
    }

    public static void setPassword(final String password) {
        User.password = password;
    }
    public static String getPassword() {
        return User.password;
    }

    public static void setToken() {

    }
}
