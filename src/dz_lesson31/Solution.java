package dz_lesson31;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception{
        //иду циклом по строке
        //беру символ, проверяю буква ли это
        //если ключ с таким символом есть, то заношу его в мапу и считаю сколько раз он повторяется
        //если нет заношу в мапу и ставлю 1
        String text = "I need you.";
        String text2 = "The urn was then carried for several rounds around the cremation site, for the last leg of the procession.";
        System.out.println(countSymbols(text));
        System.out.println(words(text2));

    }

    public static Map<Character, Integer> countSymbols(String text)throws Exception{
        if (text == null)
            throw new Exception("No text");

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)){
                if (map.containsKey(c)){
                    map.put(c, map.get(c) + 1);
                }
                else map.put(c, 1);
            }
        }
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
            if (el != null && isValidWord(el)){
                words.add(el);
            }
        }

        String word;
        for (int i = 0; i < words.size(); i++) {
            word = words.get(i);
            if (map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            }
            else map.put(word, 1);
        }
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
