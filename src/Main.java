import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

                   // Name's and Cash
        int multiply = 1;
        float input = 1;
        int[] betChoice = {-1,-1};
        int rbetCount;
        float[] betCount = {1f,2f};
        String[] name = {"Первый игрок","Второй игрок"};
        for (int asdf = 0; asdf < 2; asdf++) {
            System.out.println(name[asdf] + ", введи имя: ");
            name[asdf] = new Scanner(System.in).next();
        }
        int[] cash = {5000,5000};

        while (cash[0]>0&cash[1]>0) {

            // BetCount
            System.out.println("Игроки, сейчас вы будете делать ставки, если ввести 0 то игра будет окончена ");
            for (int i = 0; i < 2; i++) {
                System.out.println(name[i] + ", введите ставку: ");
                try {
                    betCount[i] = new Scanner(System.in).nextFloat();
                } catch (Exception e) {
                    System.out.println("Ошибка, введите ставку положительным числом!");
                    i--;
                }
            }
            if (betCount[0] > cash[0] | betCount[1] > cash[1] |betCount[1] > cash[0] |betCount[0] > cash[1]) {
                System.out.println("Ставка не может быть больше чем кол-во вашего кэша! " +
                        "\n" + name[0] + " " + cash[0] + "\n" + name[1] + " " + cash[1]);
                continue;
            }
            if (betCount[0] < 0) betCount[0] *= -1;
            if (betCount[1] < 0) betCount[1] *= -1;
            if (betCount[0] == 0| betCount[1] == 0) {
                System.out.println("Игрок ввёл 0, игра окончена. \n" +name[0] + ", у вас осталось " + cash[0] +
                        "\n" +name[1] + ", у вас осталось " + cash[1]);
                System.exit(0);}

            // Bet1 == Bet2
            while (betCount[0] != betCount[1]) {
                for (int i = 0; i < 2; i++) {
                    if (betCount[i] == betCount[0]) {
                        rbetCount = 1;
                    } else rbetCount = 0;
                    if (betCount[i] < betCount[rbetCount]) {
                        System.out.println(name[i] + ", ваша ставка меньше ставки вашего оппонента." +
                                "\nВы поставили " + betCount[i] + "\nВаш оппонент поставил " + betCount[rbetCount] +
                                "\nСколько вы прибавите к своей ставке (если поставить \"0\", то игра завершится):");
                        try {
                            input = new Scanner(System.in).nextFloat();
                            if (input < 0) input *= -1;
                        betCount[i] += input;}
                        catch (Exception e) {System.out.println("Ошибка, введите число правильно!");
                        i--;}
                    }
                    if (input == 0) {System.out.println("Игрок ввёл 0, игра окончена. \n" +name[0] + ", у вас осталось " + cash[0] +
                            "\n" +name[1] + ", у вас осталось " + cash[1]);
                        System.exit(0);}
                }
            }
            if (input == 0) break;
            //ChoiceBet
            for (int i = 0;i < 2;i++){
                do {
                System.out.println(name[i] + ", Выбери на что ставить \n0) Зелёный 50х. 1)Чёрный 2х. 2)Красный 2х" +
                        "\nP.s: Выбирать нужно цифрой.");
                try {
                    betChoice[i] = new Scanner(System.in).nextInt();
                } catch (Exception e) {System.out.println("Ошибка, введите один из предложенных вариантов!");}
                } while (!(betChoice[i] >= 0 & betChoice[i] < 3));
            }

            // Result
            int random = new Random().nextInt(51);
            random = (random == 0 ? random = 0 : random < 26 ? random = 1 : random < 51 ? random = 2 : null);

            System.out.println("Кстати, если вы выбрали одинаково цвет и выиграли, то ваш баланс не измениться)))");


            if (random == 0) {multiply = 50;} else {multiply = 1;}
            if (betChoice[0] == betChoice[1]) {
                if (betChoice[0] == random) {
                    System.out.println("Оба выиграли, ваши счета не изменились! " +
                            "\n" + name[0] + " " + cash[0] + "\n" + name[1] + " " + cash[1]);
                } else {
                    cash[0] -= betCount[0];
                    cash[1] -= betCount[1];
                    System.out.println("Оба потеряли по " + betCount[0] +
                            "\n" + name[0] + " " + cash[0] + "\n" + name[1] + " " + cash[1]);
                }
            } else {
                for (int i = 0; i < 2 ;i++)
                if (betChoice[i] == random) {
                    cash[i] += (betCount[i] * multiply);
                    System.out.println( name[i] + ", твой счёт увеличился на " + (betCount[i]*multiply) +
                            "\n У тебя осталось " + cash[i]);
                }
                else {
                    cash[i] -= betCount[i];
                    System.out.println(name[i] + ", твой уменьшился на " + betCount[i] +
                            "\n У тебя осталось " + cash[i]);
                }
            }




        }
    }
}
