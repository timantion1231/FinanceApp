package org.example;

import java.io.*;
import java.util.ArrayList;

public class Manager {
    private ArrayList<User> users;
    User curruser;
    boolean isUserIn = false;
    private final String statePath = "state.dat";

    public Manager() throws IOException {
        String isFirstStartPath = "firstStart.dat";
        BufferedReader bf = new BufferedReader(new FileReader(isFirstStartPath));
        boolean isFirstStart = Boolean.parseBoolean(bf.readLine());
        if (!isFirstStart) load();
        else {
            users = new ArrayList<>();
            saveState();
        }
    }

    public String signUp(String login, String password) {
        for (User user : users) {
            if (user.login.equals(login)) {
                return "Пользователь с данным именем уже существует";
            }
        }
        curruser = new User(login, password);
        users.add(curruser);
        isUserIn = true;
        return "Вы успешно авторизовались";
    }

    public String logIn(String login, String password) {
        for (User user : users) {
            if (user.login.equals(login)) {
                if (password.hashCode() == user.password) {
                    curruser = user;
                    isUserIn = true;
                    return "Вы успешно авторизовались";
                } else return "Неверный пароль";
            }
        }
        return "Данного пользователя не сущкствует";
    }

    public String LogOut() {
        isUserIn = false;
        return "Вы успешно вышли из системы";
    }

    private void load() {
        try (FileInputStream fis = new FileInputStream(statePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Manager manager = (Manager) ois.readObject();
            this.users = manager.users;
            this.curruser = null;
            this.isUserIn = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveState() {
        try (FileOutputStream fos = new FileOutputStream(statePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public String setIncome(String catName, long sum) {
        if (isUserIn) {
            for (Incomes inc : curruser.wallet.getAllIncomes()) {
                if (inc.name.equals(catName)) {
                    curruser.setIncome(inc, sum);
                    return "Доход успешно добавлен";
                }
            }
            return "Данная категория не найдена";
        } else return "Вы не авторизованы.";
    }

    public String getAllIncomes() {
        if (isUserIn) {
            StringBuilder sb = new StringBuilder();
            for (Incomes inc : curruser.wallet.getAllIncomes()) {
                sb.append("\n" + inc.name + ": " + inc.totalMoney);
            }
            return sb.toString();
        } else return "Вы не авторизованы.";
    }

    public String getAllExp() {
        if (isUserIn) {
            StringBuilder sb = new StringBuilder();
            for (Exp exp : curruser.wallet.getAllExp()) {
                sb.append("\n" + exp.name + ": " + exp.totalMoney + ", лимит: " + exp.getLimit());
                return sb.toString();
            }
        }
        return "Вы не авторизованы.";
    }

    public String setExpLimit(String expName, long limit) {
        if (isUserIn) {
            for (Exp exp : curruser.wallet.getAllExp()) {
                if (exp.name.equals(expName)) {
                    exp.setLimit(limit);
                    return "Лимит успешно изменен";
                }
            }
            return "Данная категория не найдена";
        }
        return "Вы не авторизованы.";
    }

    public String createExp(String name) {
        if (isUserIn) {
            for (Exp exp : curruser.wallet.getAllExp()) {
                if (exp.name.equals(name)) return "Данная категория уже существует";
            }
            curruser.createExp(name);
            return "Категория " + name + " успешно создана";
        }
        return "Вы не авторизованы.";
    }

    public String createIncome(String name) {
        if (isUserIn) {
            for (Incomes inc : curruser.wallet.getAllIncomes()) {
                if (inc.name.equals(name)) return "Данная категория уже существует";
            }
            curruser.createIncomes(name);
            return "Категория " + name + " успешно создана";
        }
        return "Вы не авторизованы.";
    }

    public String getAllNotifications() {
        if (isUserIn) {
            return curruser.getAllNotifications();
        }
        return "Вы не авторизованы.";
    }
}
