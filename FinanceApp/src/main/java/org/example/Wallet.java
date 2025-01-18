package org.example;

import java.io.*;
import java.util.ArrayList;

public class Wallet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ArrayList<Cats> cats;
    private long totalincomes;
    private long totalExp;

    public Wallet() {
        cats = new ArrayList<>();
        cats.add(new Incomes("Зарплата"));
        cats.add(new Incomes("Фриланс/подработка"));
        cats.add(new Incomes("Инвестиции"));
        cats.add(new Exp("Жилье"));
        cats.add(new Exp("Еда"));
        cats.add(new Exp("Транспорт"));
        totalincomes = 0;
        totalExp = 0;
    }

    public void addIncome(Incomes inc, long sum) {
        for (Incomes incomes : getAllIncomes()) {
            if (incomes.name.equals(inc.name)) {
                incomes.setAction(sum);
                totalincomes+=sum;
            }
        }
    }

    public void addExp(Exp exp, long sum) {
        for (Exp exps : getAllExp()) {
            if (exps.name.equals(exp.name)) {
                exps.setAction(sum);
                totalExp+=sum;
            }
        }
    }


    public ArrayList<Exp> getAllExp() {
        ArrayList<Exp> exp = new ArrayList<>();
        for (Cats cat : cats) {
            if (cat instanceof Exp) exp.add((Exp) cat);
        }
        return exp;
    }

    public ArrayList<Incomes> getAllIncomes() {
        ArrayList<Incomes> inc = new ArrayList<>();
        for (Cats cat : cats) {
            if (cat instanceof Incomes) inc.add((Incomes) cat);
        }
        return inc;
    }

    public void createCategory(String name, boolean type) {
        if (type) {
            cats.add(new Incomes(name));
        } else cats.add(new Exp(name));
    }

    public void removeCategory(Cats category) {
        for (Cats cat : cats) {
            if (cat.name.equals(category.name)) {
                cats.remove(cat);
                return;
            }
        }
    }

    public long getTotalExp() {
        long totexp =0;
        for(Exp exp:getAllExp()){
            totexp +=exp.totalMoney;
        }
        totalExp = totexp;
        return totalExp;
    }

    public long getTotalIncomes() {
        long totinc = 0;
        for(Incomes incs: getAllIncomes()){
            totinc+=incs.totalMoney;
        }
        totalincomes = totinc;
        return totalincomes;
    }


    public ArrayList<Cats> getAllCats() {
        return cats;
    }
}
