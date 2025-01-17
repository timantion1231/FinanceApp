package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

public class Manager {
    private final ArrayList<User> users;
    User curruser;
    boolean isUserIn;
    private final ArrayList<String> usersPaths;

    private final String allUsersPath = "AllUsers.users";

    public Manager() {
        users = new ArrayList<>();
        usersPaths = new ArrayList<>();
    }

    public String signUp(String login, String password) {
        return null;
    }

    public String logIn(String login, String password) {
        return null;
    }

    public String LogOut() {
        return null;
    }

    private void loadUsers() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(allUsersPath));
            String currUserPath;
            while ((currUserPath = bf.readLine()) != null) {
                usersPaths.add(currUserPath);
            }
            for (String path : usersPaths) {
                this.users.add(new User(path));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при загрузке пользователей. Файл AllUsers.users не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке пользователей. Файл AllUsers.users пуст");
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке пользователей");
        }
    }

    public String setIncome(String catName, long sum) {
        return null;
    }

    public String getAllIncomes() {
        return null;
    }

    public String getAllExp() {
        return null;
    }

    public String setExpLimit(String expName, long limit) {
        return null;
    }

    public String createExp(String name) {
        return null;//проверить на уникальность
    }

    public String createIncome(String name) {
        return null; // проверить на уникальность
    }

    public String getAllNotifications() {
        return null;
    }
}
