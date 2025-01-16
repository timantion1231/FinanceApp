package org.example;

public class Notifications {

    String notificationText;
    byte type;
    Cats cat;

    public Notifications(byte type){
        this.type = type;
    }

    public void setLimitLastNotification(Exp exp, long limitLast){

    }

    public void setExpGraderIncNotification(long inc, long exp){

    }

    public String getNotificationText(){
        return notificationText;
    }


}
