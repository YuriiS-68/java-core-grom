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
        int countErrors = 3;

        while (countErrors > 0){
            System.out.println("Enter ten numbers from 1 to 100");

            String str = br.readLine();

            if (checkInput(str)){
                String[] numbers = str.split(" ");

                for (String number : numbers) {
                    int x = Integer.parseInt(number);
                    sum += x;
                }
                System.out.println("Sum of entered numbers: " + sum);
                break;
            }

            if (!checkInput(str)) {
                countErrors--;
                if (countErrors == 0){
                    System.out.println("Your numbers are wrong. Number of attempts exceeded");
                }
                else {
                    System.out.println("Your numbers are wrong. You have " + countErrors + " attempts to try again");
                }
            }
        }
    }

    private static boolean checkInput(String input){
        String[] numbers = input.split(" ");

        if (numbers.length != 10){
            return false;
        }

        for (String number : numbers) {
            for (char ch : number.toCharArray()) {
                if (!Character.isDigit(ch)){
                    return false;
                }
            }
            int x = Integer.parseInt(number);
            if (x > 100){
                return false;
            }
        }
        return true;
    }
}
