package dz_lesson31;

import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        //иду циклом по строке
        //беру символ, проверяю буква ли это
        //если ключ с таким символом есть, то заношу его в мапу и считаю сколько раз он повторяется
        //если нету заношу в мапу и ставлю 1
        String text = "Aaaaaaa aaa mmmmm";
        String text2 = "the the the the the the tg";
        System.out.println(countSymbols(text));
        System.out.println(words(text2));
    }

    public static Map<Character, Integer> countSymbols(String text)throws Exception{
        //сделать отсортированный список
        //посчитать количество повторений каждого символа и занести в переменную
        //результат добавить в мапу
        if (text == null)
            throw new Exception("No text");

        Map<Character, Integer> map = new HashMap<>();

        ArrayList<Character> chars = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.toLowerCase().charAt(i);
            if (Character.isLetter(c)){
                chars.add(c);
            }
        }
        Collections.sort(chars);

        //надо взять первый элемент массива, пройти им по всему списку и если повторяется то плюсовать счетчик
        //если не повторился то счетчик равен 1
        Character c = chars.get(0);
        int count = 1;
        for (int i = 1; i < chars.size(); i++) {
            // проверяю текущий символ
            if (chars.get(i).equals(c)) {
                // символ тот же - счетчик увеличиваю
                count++;
            } else {
                // другой символ, добавляю предыдущий и запоминаю новый
                map.put(c, count);
                c = chars.get(i);
                count = 1;
            }
        }
        map.put(c, count);

        return map;
    }

    public static Map<String, Integer> words(String text)throws Exception{
        //оставить в строке буквы, цифры и пробелы
        if (text == null)
            throw new Exception("No text");

        Map<String, Integer> map = new HashMap<>();

        ArrayList<String> words = new ArrayList<>();
        String[] str = text.split("\\W+");
        for (String el : str){
            if (el != null && isValidWord(el) && el.length() > 1){
                words.add(el);
            }
        }
        Collections.sort(words);

        String word = words.get(0);
        int count = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(word)){
                count++;
            }
            else {
                map.put(word, count);
                word = words.get(i);
                count = 1;
            }
        }
        map.put(word, count);

        return map;
    }

    private static boolean isValidWord(String word) {
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return !word.trim().isEmpty();
    }

}
