package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String mainMenu = """
                Выход: 1
                Добавить доход: 2
                Добавить расход: 3
                Создать категорию дохода: 4
                Создать категорию расхода: 5
                Установить лимит для категории: 6
                Общая сумма расходов: 7
                Общая сумма доходов: 8
                Сумма доходов по категориям: 9
                Сумма расходов по категориям: 10
                Все категории доходов: 11
                Все категории расходов: 12
                Удалить категорию: 13
                """;
        String authorizationMenu = "Регистрация: 1\nВход: 2\nЗавершение работы: 3";
        int key = 0;
        Manager manager = new Manager();
        Scanner in = new Scanner(System.in);
        while (key != -1) {
            if (manager.isUserIn) {
                System.out.println(mainMenu);
                System.out.println("Уведомления:\n"+manager.getAllNotifications());
                try {
                    key = in.nextInt();
                } catch (Exception e) {
                    key = 0;
                }
                in.nextLine();
                switch (key) {
                    case 1 -> System.out.println(manager.logOut());
                    case 2 -> {
                        System.out.println(manager.getAllIncomes());
                        System.out.println("Введите название категории");
                        String catName = in.nextLine();
                        System.out.println("Введите сумму");
                        int sum = in.nextInt();
                        System.out.println(manager.setIncome(catName, sum));
                    }
                    case 3 -> {
                        System.out.println(manager.getAllExp());
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
                    case 7 -> System.out.println(manager.getTotalExp());
                    case 8 -> System.out.println(manager.getTotalIncomes());
                    case 9 -> {
                        System.out.println(manager.getAllIncomes());
                        System.out.println("Введите имена категорий для подсчета через Enter, после перечисления напишите" +
                                " end");
                        String currStr = in.nextLine();
                        ArrayList<String> cats = new ArrayList<>();
                        while (!currStr.equals("end")) {
                            cats.add(currStr);
                            currStr = in.nextLine();
                        }
                        System.out.println(manager.getTotalSumByCats((byte) 1, cats));
                    }
                    case 10 -> {
                        System.out.println(manager.getAllExp());
                        System.out.println("Введите имена категорий для подсчета через Enter, после перечисления напишите" +
                                " end");
                        String currStr = in.nextLine();
                        ArrayList<String> cats = new ArrayList<>();
                        while (!currStr.equals("end")) {
                            cats.add(currStr);
                            currStr = in.nextLine();
                        }
                        System.out.println(manager.getTotalSumByCats((byte) 2, cats));
                    }
                    case 11 -> System.out.println(manager.getAllIncomes());
                    case 12 -> System.out.println(manager.getAllExp());
                    case 13 ->{
                        System.out.println(manager.getAllExp());
                        System.out.println(manager.getAllIncomes());
                        System.out.println("Введите название категории");
                        System.out.println(manager.removeCat(in.nextLine()));
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
                        System.out.println(manager.signUp(login, in.nextLine()));
                    }
                    case 2 -> {
                        System.out.println("Введите логин");
                        String login = in.nextLine();
                        System.out.println("Введите пароль");
                        System.out.println(manager.logIn(login, in.nextLine()));
                    }
                    case 3 -> System.exit(0);
                }
            }
        }
        System.out.println("Hello world!");
    }

}
