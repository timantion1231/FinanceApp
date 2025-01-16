package org.example;

import java.util.ArrayList;

public class User {
    public final String login;
    private final int password;
    Wallet wallet;
    private final String walletPath;
    private final ArrayList<Notifications> notifications;

    public User(String login, String password) {
        this.login = login;
        this.password = password.hashCode();
        walletPath = "";//указать ссылку на новый кошелек
        this.notifications = new ArrayList<>();
    }

    public User(String userPath){
        login = null;
        password = 0;
        walletPath = null;
        notifications = null;
        //все вышеперечисленные поля заполнятся после реализации загрузки
    }

    public ArrayList<Cats> getAllCategories(){
        return null;
    }

    public void setIncome(Incomes income, long sum){

    }

    public void setExp(Exp exp, long sum){

    }

    public void setExpLimit(Exp exp, long limit){

    }

    public boolean removeCat(Cats cat){
        return true;
    }

    public long getLimitLeft(Exp exp){
        return 0;
    }

    public String getAllNotifications(){
        return null;
    }

    private void setNewNotification(byte type){

    }

    private void loadWallet(){

    }

}
