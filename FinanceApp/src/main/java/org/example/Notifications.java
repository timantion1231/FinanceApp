package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Notifications implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    String notificationText;
    byte type;

    /**
     *
     * @param type 1- расходы больше доходов, 2- превышен лимит
     */
    public Notifications(byte type) {
        this.type = type;
    }

    public void setLimitLastNotification(Exp exp) {
        notificationText = "Расходы по категории " + exp.name + " составляют: " + exp.getTotalMoney()
                + ", что превышает лимит в " + exp.getLimit() + " на " + (exp.getTotalMoney()-exp.getLimit()) + "\n";
    }

    public void setExpGraderIncNotification(long inc, long exp) {
        notificationText = "Расходы: " + exp + " превысили доходы: " + inc + " на " + (exp - inc) + "\n";
    }

    public String getNotificationText() {
        return notificationText;
    }


}
