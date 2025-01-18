package org.example;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public final String login;
    public final int password;
    Wallet wallet;
    private ArrayList<Notifications> notifications;

    public User(String login, String password) {
        this.login = login;
        this.password = password.hashCode();
        this.notifications = new ArrayList<>();
        wallet = new Wallet();
    }


    public ArrayList<Cats> getAllCategories() {
        checkForNotifications();
        return wallet.getAllCats();
    }

    public void setIncome(Incomes income, long sum) {
        wallet.addIncome(income, sum);
        checkForNotifications();
    }

    public void setExp(Exp exp, long sum) {
        wallet.addExp(exp, sum);
        checkForNotifications();
    }

    public void removeCat(Cats cat) {
        wallet.removeCategory(cat);
    }

    public String getAllNotifications() {
        StringBuilder sb = new StringBuilder();
        checkForNotifications();
        for (Notifications notify : notifications) {
            sb.append(notify.getNotificationText());
        }
        return String.valueOf(sb);
    }

    private void checkForNotifications() {
        ArrayList<Notifications> currNotifications = new ArrayList<>();
        if (wallet.getTotalExp() > wallet.getTotalIncomes()) {
            Notifications notis = new Notifications((byte) 1);
            notis.setExpGraderIncNotification(wallet.getTotalIncomes(), wallet.getTotalExp());
            currNotifications.add(notis);
        }
        for (Exp exp : wallet.getAllExp()) {
            if (exp.getLimit() < exp.getTotalMoney()) {
                Notifications notis = new Notifications((byte) 2);
                notis.setLimitLastNotification(exp);
                currNotifications.add(notis);
            }
        }
        this.notifications = currNotifications;
    }

    public void createExp(String name) {
        wallet.createCategory(name, false);
        checkForNotifications();
    }

    public void createIncomes(String name) {
        wallet.createCategory(name, true);
        checkForNotifications();
    }
}
