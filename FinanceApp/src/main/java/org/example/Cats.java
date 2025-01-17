package org.example;

import java.io.Serial;
import java.io.Serializable;

public abstract class Cats implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected long totalMoney;
    public final String name;

    public Cats(String name) {
        this.name = name;
        this.totalMoney = 0;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setAction(long sum) {
        this.totalMoney += sum;
    }
}
