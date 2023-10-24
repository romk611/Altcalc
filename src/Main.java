import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main { //основной
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражние: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        DataReader reader = new DataReader();
        Calculator sum = new Calculator();

        reader.proverka(text);

        String res = sum.calc(text);

        if (res.length() > 40) {
            System.out.println("\"" + res.substring(0, 40) + "..." + "\"");
        } else {
            System.out.println("\"" + res + "\"");
        }
    }
}

class Calculator {

    public String calc(String text) throws Exception {
        char operation = 0;
        String res = null;
        String text2 = text.replace("\"", "");

        if (text2.contains("+")) {
            String[] blocks = text2.split("\\+");
            res = blocks[0].trim().concat(blocks[1].trim());
        } else if (text2.contains("-")) {
            String[] blocks = text2.split("\\-");

            int index = blocks[0].trim().indexOf(blocks[1].trim());
            if (index == -1) {
                res = blocks[0].trim();
            } else {
                res = blocks[0].trim().replaceAll(blocks[1].trim(), "");

            }
        } else if (text2.contains("*")) {
            String[] blocks = text2.split("\\*");
            int number = Integer.parseInt(blocks[1].trim());
            res = blocks[0].trim().repeat(number);
        } else if (text2.contains("/")) {
            String[] blocks = text2.split(" / ");
            int number = Integer.parseInt(blocks[1]);
            int delitel = blocks[0].length() / number;
            res = blocks[0].substring(0, delitel);
        }
        return res;
    }

}

class DataReader {

    public void proverka(String text) throws Exception {

        int count = 0;
        int b1;
        int b2;
        if (text.contains("+")) {
            String[] block = text.split("\\+");
            b1 = block[0].replace("\"", "").trim().length();
            b2 = block[1].replace("\"", "").trim().length();
            if (b1 > 10 || b2 > 10) {
                throw new Exception("Длина строки не должна превышать 10 символов");
            }
            for (char element : text.toCharArray()) {
                if (element == '\"') count++;
            }
            if (count != 4) {
                throw new Exception("При сложнии складываются только строки");
            }
        } else if (text.contains("-")) {
            String[] block = text.split("\\-");
            b1 = block[0].replace("\"", "").trim().length();
            b2 = block[1].replace("\"", "").trim().length();
            if (b1 > 10 || b2 > 10) {
                throw new Exception("Длина строки не должна превышать 10 символов");
            }
            for (char element : text.toCharArray()) {
                if (element == '\"') count++;
            }
            if (count != 4) {
                throw new Exception("При вычитании отнимаются только строки");
            }
        } else if (text.contains("*")) {
            String[] block = text.split("\\* ");
            int a = Integer.parseInt(block[1]);
            b1 = block[0].replace("\"", "").trim().length();
            if (b1 > 10) {
                throw new Exception("Длина строки не должна превышать 10 символов");
            }
            for (char element : block[0].toCharArray()) {
                if (element == '\"') count++;
            }
            if (count != 2) {
                throw new Exception("Первым всегда должна быть строка");
            }
            if (a > 10 || a < 1) {
                throw new Exception("Число от 1 до 10");
            }
        } else if (text.contains("/")) {
            String[] block = text.split("\\/ ");
            int a = Integer.parseInt(block[1]);
            b1 = block[0].replace("\"", "").trim().length();
            if (b1 > 10) {
                throw new Exception("Длина строки не должна превышать 10 символов");
            }
            for (char element : block[0].toCharArray()) {
                if (element == '\"') count++;
            }
            if (count != 2) {
                throw new Exception("Первым всегда должна быть строка");
            }
            if (a > 10 || a < 1) {
                throw new Exception("Число от 1 до 10");
            }
        } else {
            throw new Exception("Нет подходящего арифметического знака");
        }
    }
}
