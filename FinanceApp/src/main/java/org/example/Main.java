package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String mainMenu = "Выход: 1\nДобавить доход: 2\n" +
                "Добавить расход: 3\nСоздать категорию дохода: 4\n" +
                "Создать категорию расхода: 5\nУстановить лимит для категории: 6\n" +
                "Общая сумма расходов: 7\nОбщая сумма доходов: 8\n" +
                "Сумма доходов по категориям: 9\nСумма расходов по категориям: 10\n" +
                "Все категории доходов: 11\nВсе категории расходов: 12";
        String authorizationMenu = "Регистрация: 1\nВход: 2\nЗавершение работы: 3";
        int key = 0;
        Manager manager = new Manager();
        Scanner in = new Scanner(System.in);
        while (key != -1) {
            if (manager.isUserIn) {
                System.out.println(mainMenu);
                try {
                    key = in.nextInt();
                } catch (Exception e) {
                    key = 0;
                }
                in.nextLine();
                switch (key) {
                    case 1 -> {
                        System.out.println(manager.logOut());
                    }
                    case 2 -> {
                        System.out.println("Введите название категории");
                        String catName = in.nextLine();
                        System.out.println("Введите сумму");
                        int sum = in.nextInt();
                        System.out.println(manager.setIncome(catName, sum));
                    }
                    case 3 -> {
                        System.out.println("Введите название категории");
                        String catName = in.nextLine();
                        System.out.println("Введите сумму");
                        int sum = in.nextInt();
                        System.out.println(manager.setExp(catName, sum));
                    }
                    case 4 -> {
                        System.out.println("Введите название категории");
                        String catName = in.nextLine();
                        System.out.println(manager.createIncome(catName));
                    }
                    case 5 -> {
                        System.out.println("Введите название категории");
                        String catName = in.nextLine();
                        System.out.println(manager.createExp(catName));
                    }
                    case 6 -> {
                        System.out.println("Введите название категории");
                        String catName = in.nextLine();
                        System.out.println("Введите сумму");
                        int sum = in.nextInt();
                        in.nextLine();
                        System.out.println(manager.setExpLimit(catName, sum));
                    }
                    case 7 -> {
                        System.out.println(manager.getTotalExp());
                    }
                    case 8 -> {
                        System.out.println(manager.getTotalIncomes());
                    }
                    case 9 -> {
                        System.out.println(manager.getAllIncomes());
                        System.out.println("Введите имена категорий для подсчета через Enter, после перечисления напишите" +
                                "end");
                        String currStr = in.nextLine();
                        ArrayList<String> cats = new ArrayList<>();
                        while (!currStr.equals("end")) {
                            cats.add(currStr);
                        }
                        manager.getTotalSumByCats((byte) 1, cats);
                    }
                    case 10 -> {
                        System.out.println(manager.getAllIncomes());
                        System.out.println("Введите имена категорий для подсчета через Enter, после перечисления напишите" +
                                "end");
                        String currStr = in.nextLine();
                        ArrayList<String> cats = new ArrayList<>();
                        while (!currStr.equals("end")) {
                            cats.add(currStr);
                        }
                        manager.getTotalSumByCats((byte) 2, cats);
                    }
                    case 11 ->{
                        System.out.println(manager.getAllIncomes());
                    }
                    case 12 ->{
                        System.out.println(manager.getAllExp());
                    }
                }
            } else {
                System.out.println(authorizationMenu);
                try {
                    key = in.nextInt();
                } catch (Exception e) {
                    key = 0;
                }
                in.nextLine();
                switch (key) {
                    case 1 -> {
                        System.out.println("Введите имя пользователя");
                        String login = in.nextLine();
                        while (manager.isThatUserExists(login)) {
                            System.out.println("Пользователь с таким именем существует. Введите другое имя");
                            login = in.nextLine();
                        }
                        System.out.println("Придумайте пароль");
                        manager.signUp(login, in.nextLine());
                    }
                    case 2 -> {
                        System.out.println("Введите логин");
                        String login = in.nextLine();
                        System.out.println("Введите пароль");
                        System.out.println(manager.logIn(login, in.nextLine()));
                    }
                    case 3 -> {
                        System.exit(0);
                    }
                }
            }
        }
        System.out.println("Hello world!");
    }

}