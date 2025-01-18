package org.example;

import java.io.*;
import java.util.ArrayList;

public class Manager implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<User> users;
    User curruser;
    public boolean isUserIn = false;
    private final String statePath = "\\state.dat";

    public Manager() {
        String isFirstStartPath = "\\firstStart.dat";
        BufferedReader bf = null;
        boolean isFirstStart = true;
        try {
            bf = new BufferedReader(new FileReader(isFirstStartPath));
            isFirstStart = Boolean.parseBoolean(bf.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (!isFirstStart) load();
        else {
            users = new ArrayList<>();
            saveState();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(isFirstStartPath, false))) {
                bufferedWriter.write(String.valueOf(false));
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }
    }

    public String signUp(String login, String password) {
        curruser = new User(login, password);
        if (users == null) users = new ArrayList<>();
        users.add(curruser);
        isUserIn = true;
        saveState();
        return "Вы успешно авторизовались";
    }

    public boolean isThatUserExists(String login){
        for(User user: users){
            if(user.login.equals(login)) return true;
        }
        return false;
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

    public String logOut() {
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
                    saveState();
                    return "Доход успешно добавлен";
                }
            }
            return "Данная категория не найдена";
        } else return "Вы не авторизованы.";
    }

    public String setExp(String catName, long sum) {
        if (isUserIn) {
            for (Exp exp : curruser.wallet.getAllExp()) {
                if (exp.name.equals(catName)) {
                    curruser.setExp(exp, sum);
                    saveState();
                    return "Расход успешно добавлен";
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
            }
            return sb.toString();      }
        return "Вы не авторизованы.";
    }

    public String getTotalExp() {
        if (isUserIn) {
            return "Всего расходов: " + curruser.wallet.getTotalExp() + "\n";
        }
        return "Вы не авторизованы.";
    }

    public String getTotalIncomes() {
        if (isUserIn) {
            return "Всего доходов: " + curruser.wallet.getTotalIncomes() + "\n";
        }
        return "Вы не авторизованы.";
    }

    /**
     * @param type  1-income, 2-exp
     * @param names names of categories
     * @return string
     */
    public String getTotalSumByCats(byte type, ArrayList<String> names) {//types: 1-inc 2-exp
        if (isUserIn) {
            long totalSum = 0;
            if (type == 1) {
                for (Cats cat : curruser.getAllCategories()) {
                    if (cat instanceof Incomes) {
                        for (String name : names) {
                            if (cat.name.equals(name)) {
                                totalSum += cat.getTotalMoney();
                            }
                        }
                    }
                }
                return "Всего доходов: " + totalSum;
            } else {
                for (Cats cat : curruser.getAllCategories()) {
                    if (cat instanceof Exp) {
                        for (String name : names) {
                            if (cat.name.equals(name)) {
                                totalSum += cat.getTotalMoney();
                            }
                        }
                    }
                }
                return "Всего расходов: " + totalSum;
            }
        }
        return "Ошибка";
    }


    public String setExpLimit(String expName, long limit) {
        if (isUserIn) {
            for (Exp exp : curruser.wallet.getAllExp()) {
                if (exp.name.equals(expName)) {
                    exp.setLimit(limit);
                    saveState();
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
            saveState();
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
            saveState();
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
