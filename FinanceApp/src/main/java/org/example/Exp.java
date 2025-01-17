package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Exp extends Cats implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long limit = 0;

    public Exp(String name) {
        super(name);
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

}
