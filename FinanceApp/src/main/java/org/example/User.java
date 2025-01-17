package org.example;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public final String login;
    private final int password;
    Wallet wallet;
    private final ArrayList<Notifications> notifications;

    public User(String login, String password) {//доделать создание кошелька и тд
        this.login = login;
        this.password = password.hashCode();
        this.notifications = new ArrayList<>();
    }

    public User(String userPath) throws Exception {
        String login = "";
        int password = 0;
        String walletPath = "";

        try(FileInputStream fis = new FileInputStream(userPath);
        ObjectInputStream ois = new ObjectInputStream(fis)){
            User user = (User) ois.readObject();
            login = user.login;
            password = user.password;
            wallet = user.wallet;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

        this.login = login;
        this.password = password;
        notifications = this.checkLoadNotifications();
    }

    public ArrayList<Cats> getAllCategories() {
        return null;
    }

    public void setIncome(Incomes income, long sum) {

    }

    public void setExp(Exp exp, long sum) {

    }

    public void setExpLimit(Exp exp, long limit) {

    }

    public boolean removeCat(Cats cat) {
        return true;
    }

    public long getLimitLeft(Exp exp) {
        return 0;
    }

    public String getAllNotifications() {
        return null;
    }

    private void setNewNotification(byte type) {

    }

    private void loadWallet() {

    }

    private ArrayList<Notifications> checkLoadNotifications() {
        ArrayList<Notifications> currNotifications = new ArrayList<>();
        if (wallet.getTotalExp() > wallet.getTotalincomes()) {
            currNotifications.add(new Notifications((byte) 1));
        }
        for (Exp exp : wallet.getAllExp()) {
            if (exp.getLimit() < exp.getTotalMoney()) {
                currNotifications.add(new Notifications((byte) 2));
            }
        }
        return currNotifications;
    }

}
