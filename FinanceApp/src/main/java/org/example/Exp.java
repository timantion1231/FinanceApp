package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Exp extends Cats implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long limit;
    public Exp(String name){
        super(name);
    }
    public Exp(String name, long totalMoney, long limit){
        super(name);
        super.totalMoney = totalMoney;
        this.limit = limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }
    
}
