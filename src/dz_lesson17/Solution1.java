package dz_lesson17;

public class Solution1 {
    public static void main(String[] args) {
        String str = "The has !been divided abs on98 the issue, with   moderates5789 concerned @about the on the most vulnerable.";
        String str1 = "End";
        String str2 = null;

        System.out.println(countWords(str));
        System.out.println(countWords(str1));

        System.out.println("Max word: " + maxWord(str));
        System.out.println("Min word: " + minWord(str));

        System.out.println("Max word: " + maxWord(str2));
        System.out.println("Min word: " + minWord(str2));

        System.out.println("The most commonly used word: " + mostCountedWord(str));
        System.out.println("The most commonly used word: " + mostCountedWord(str1));
        System.out.println("The most commonly used word: " + mostCountedWord(str2));

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
        return !word.trim().isEmpty();
    }

    public static String maxWord(String input){

        if (input == null){
            return null;
        }

        String[] words = input.split(" ");

        String max = "";
        for (String word : words) {
            if (isValidWord(word)) {
                max = word;
                break;
            }
        }

        if (max.equals("")){
            return null;
        }

        for (String word : words) {
            if (isValidWord(word) && word.length() > max.length()) {
                max = word;
            }
        }
        return max;
    }

    public static String minWord(String input){

        if (input == null){
            return null;
        }

        String[] words = input.split(" ");

        String min = "";
        for (String word : words) {
            if (isValidWord(word)){
                min = word;
                break;
            }
        }

        if (min.equals("")){
            return null;
        }

        for (String word : words) {
            if (isValidWord(word) && word.length() < min.length()){
                min = word;
            }
        }
        return min;
    }

    public  static String mostCountedWord(String input){
        if (input == null){
            return null;
        }

        if (input.isEmpty()){
            return null;
        }

        String[] words = input.trim().split(" ");
        String mostWord = "";

        for (String s : words) {
            if (isValidWord(s)){
                mostWord = s;
                break;
            }
        }

        if (mostWord.equals("")){
            return null;
        }

        int maxCount = 0;
        for (String word : words) {
            int count = 0;
            for (String item : words) {
                if (word.equals(item) && word.length() > 0 && isValidWord(item)){
                    count++;
                }
            }
            if (count > maxCount){
                maxCount = count;
                mostWord = word;
            }
        }

        return mostWord;
    }
}
