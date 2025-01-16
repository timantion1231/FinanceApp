package org.example;

import java.security.PublicKey;
import java.util.ArrayList;

public class Manager {
    private final ArrayList<User> users;
     User curruser;
     boolean isUserIn;
     private final ArrayList<String> usersPaths;

     private final String allUsersPath = "";

     public Manager(){
         users = null;
         usersPaths = null;
         //установить на реальные null заглушки
     }

     public String signUp(String login, String password){
         return null;
     }

     public String logIn(String login, String password){
         return null;
     }

     public String LogOut(){
         return null;
     }

     private void loadUsers(){

     }

     public String setIncome(String catName, long sum){
         return null;
     }

     public String getAllIncomes(){
         return null;
     }

     public String getAllExp(){
         return null;
     }

     public String setExpLimit(String expName, long limit){
         return null;
     }

     public String createExp(String name){
         return null;//проверить на уникальность
     }

     public String createIncome(String name){
         return null; // проверить на уникальность
     }

     public String getAllNotifications(){
         return null;
     }
}
