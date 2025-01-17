package org.example;


import java.io.Serial;
import java.io.Serializable;

public class Incomes extends Cats implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Incomes(String name) {
        super(name);
    }

}
