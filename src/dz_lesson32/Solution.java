package dz_lesson32;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args)throws Exception {

        readNumbers();

    }

    //ввести с клавиатуры 10 чисел в одной строке и посчитать их сумму
    //числа должны быть разделены пробелом
    //каждое число не может превышать 100
    //если при вводе допущена ошибка - вывести в консоль “Your numbers are wrong. You have n (подставить ко-во оставшихся попыток) attempts to try again”
    //и предложить заново ввести информацию
    //если после трёх попыток формат данных не верен, вывести в консоль “Your numbers are wrong. Number of attempts exceeded”

    private static void readNumbers()throws Exception{
        //1 проверить строку на введенные символы, цифры и пробел(должно быть 9 пробелов)
        //2 проверить правильность введения символов от 1 до 3 цифр потом пробел и число не может быть больше 100
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int sum = 0;
        int countErrors = 0;
        int maxErrors = 3;

        while (countErrors < 3){
            System.out.println("Enter ten numbers from 1 to 100");
            String str = br.readLine();
            if (checkDigital(str) && checkNumbers(str)){
                ArrayList<String> numbers = new ArrayList<>();
                String[] word = str.trim().split(" ");
                int index = 0;
                for (String s : word) {
                    if (s != null){
                        numbers.add(s);
                    }
                    index++;
                }

                for (int i = 0; i < numbers.size(); i++) {
                    int x = Integer.parseInt(numbers.get(i));
                    sum += x;
                }
                System.out.println("Sum of entered numbers: " + sum);
                break;
            }
            else
                {
                countErrors++;
                int n = maxErrors - countErrors;
                if (n == 0){
                    System.out.println("Your numbers are wrong. Number of attempts exceeded");
                }
                else {
                    System.out.println("Your numbers are wrong. You have " + n + " attempts to try again");
                }
            }
        }
    }

    private static boolean checkDigital(String word)throws Exception{
        if (word == null)
            throw new Exception("Not text");

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isDigit(c) && c != ' '){
                return false;
            }
        }
        return true;
    }

    private static boolean checkNumbers(String word)throws Exception{
        if (word == null)
            throw new Exception("Not text");

        ArrayList<String> numbers = new ArrayList<>();
        String[] digit = word.trim().split(" ");

        int index = 0;
        for (String s : digit) {
            if (s != null){
                numbers.add(s);
            }
            index++;
        }

        if (numbers.size() != 10){
            return false;
        }

        for (int i = 0; i < numbers.size(); i++) {
            int x = Integer.parseInt(numbers.get(i));
            if (x > 100) {
                return false;
            }
        }
        return true;
    }
}
