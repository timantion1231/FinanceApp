package org.example;

public abstract class Cats {
    private long totalMoney;
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
