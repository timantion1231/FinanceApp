package org.example;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public final String login;
    public final int password;
    Wallet wallet;
    private final ArrayList<Notifications> notifications;

    public User(String login, String password) {//доделать создание кошелька и тд
        this.login = login;
        this.password = password.hashCode();
        this.notifications = new ArrayList<>();
        wallet = new Wallet();
    }


    public ArrayList<Cats> getAllCategories() {
        return wallet.getAllCats();
    }

    public void setIncome(Incomes income, long sum) {
        wallet.addIncome(income, sum);
    }

    public void setExp(Exp exp, long sum) {
        wallet.addExp(exp, sum);
    }

    public void setExpLimit(Exp exp, long limit) {
        wallet.setLimit(exp, limit);
    }

    public boolean removeCat(Cats cat) {
        wallet.removeCategory(cat);
        return true;
    }

    public long getLimitLeft(Exp exp) {
        return wallet.getLimitLeft(exp);
    }

    public String getAllNotifications() {
        StringBuilder sb = new StringBuilder();
        for (Notifications notify : notifications) {
            sb.append(notify);
        }
        return sb.toString();
    }

    private ArrayList<Notifications> checkForNotifications() {
        ArrayList<Notifications> currNotifications = new ArrayList<>();
        if (wallet.getTotalExp() > wallet.getTotalIncomes()) {
            currNotifications.add(new Notifications((byte) 1));
        }
        for (Exp exp : wallet.getAllExp()) {
            if (exp.getLimit() < exp.getTotalMoney()) {
                currNotifications.add(new Notifications((byte) 2));
            }
        }
        return currNotifications;
    }

    public void createExp(String name) {
        wallet.createCategory(name, false);
    }

    public void createIncomes(String name) {
        wallet.createCategory(name, true);
    }
}
