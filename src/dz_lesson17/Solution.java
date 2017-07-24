package dz_lesson17;

public class Solution {
    public static void main(String[] args) {
        String str = "The has !been divided abs on98 the issue, with   moderates5789 concerned @about the on the most vulnerable.";

        System.out.println(countWords(str));

    }

    public static int countWords(String input){
        //разбить строку на массив слов по пробелу

        int count = 0;
        String[] words = input.split("\\s+");

        //посчитать слова, в которых содержатся только буквы
        for (String word : words) {
            if (isValidWord(word)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidWord(String word) {
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
}

