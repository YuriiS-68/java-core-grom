package dz_lesson18;

import java.util.Arrays;

public class Solution1 {
    public static void main(String[] args) {
        String str = "12,65 pkmvb 35 74 12.0 87,9 200 ";

        System.out.println(Arrays.toString(findNumbers(str)));

    }

    //строка может быть "12 34kv i89k dlx77 34,5 fkgjhvn 19.7"
    //разделить строку на массив слов по пробелу
    //определить длину нового массива цифр
    //для каждого не целого числа вывести в консоль надпись

    public static int[] findNumbers(String text){
        String[] words = text.trim().split(" ");

        int[] numbers = new int[words.length];
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if (checkDigit(words[i])) {
                numbers[index] = Integer.valueOf(words[i]);
                index++;
            }
            else System.out.println("not a number");
        }
        return numbers;
    }

    public static boolean checkDigit(String word){
        char[] chars = word.trim().toCharArray();
        for (char ch : chars) {
            if (!Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }
}
