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
        //добавить и сохранить категории по умолчанию
    }

    public Wallet(String walletPath) throws IOException {
        cats = new ArrayList<>();
        ArrayList<String> catsPath = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(walletPath));
        String currWallet;
        while ((currWallet = bf.readLine()) != null) {
            catsPath.add(currWallet);
        }
        for (String currPath : catsPath) {
            bf = new BufferedReader(new FileReader(currPath));
            byte type = Byte.parseByte(bf.readLine());
            String catName = bf.readLine();
            long totalMoney = Long.parseLong(bf.readLine());
            long limit = Long.parseLong(bf.readLine());

            if (type == 1) cats.add(new Incomes(catName, totalMoney));
            else if (type == 2) cats.add(new Exp(catName, totalMoney, limit));
            else {
                System.out.println("Ошибка при чтении данных о категориях");
                break;
            }
        }
    }

    public void addAction(Cats category, long sum) {

    }

    public void setLimit(Exp exp, long sum) {

    }

    public ArrayList<Exp> getAllExp() {
        return null;
    }

    public ArrayList<Incomes> getAllIncomes() {
        return null;
    }

    public void createCategory(String name, boolean type) {

    }

    public boolean removeCategory(Cats category) {
        return true;
    }

    public long getTotalExp() {
        return totalExp;
    }

    public long getTotalincomes() {
        return totalincomes;
    }
}
