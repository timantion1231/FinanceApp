package org.example;

public class Exp extends Cats{
    private long limit;
    public Exp(String name){
        super(name);
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }
    
}
