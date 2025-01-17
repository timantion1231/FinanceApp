package org.example;

public class Notifications {

    String notificationText;
    byte type;
    Cats cat;

    public Notifications(byte type) {
        this.type = type;
    }

    public void setLimitLastNotification(Exp exp, long limitLast) {
        notificationText = "Расходы по категории " + exp.name + " составляют: " + exp.getTotalMoney()
                + ", что превышает лимит в " + exp.getLimit() + " на " + limitLast;
    }

    public void setExpGraderIncNotification(long inc, long exp) {
        notificationText = "Расходы: " + exp + " превысили доходы: " + inc + " на " + (exp - inc);
    }

    public String getNotificationText() {
        return notificationText;
    }


}
