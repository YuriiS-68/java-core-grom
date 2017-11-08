package example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{

        readNumbers();
    }

        public static void readNumbers() throws Exception {
            int sum = 0;
            int n = 3;
            while (n <= 3 && n >= 0) {
                InputStreamReader reader = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(reader);
                ArrayList<Integer> numbers = new ArrayList<>();
                String values = br.readLine();
                String[] stringNumber = values.split(" ");
                if (!numberArrayLimitation(stringNumber, 10, 100)) {
                    if (n == 0) {
                        System.out.println("Your numbers are wrong. Number of attempts exceeded");
                        break;
                    } else {
                        System.out.println("Your numbers are wrong. You have " + n + " attempts to try again");
                        n--;
                    }
                } else {
                    for (String el : stringNumber) {
                        numbers.add(Integer.parseInt(el));
                    }
                    for (Integer el : numbers) {
                        sum += el;
                    }
                    System.out.println(sum);
                    break;
                }
            }
        }

        public static boolean checkNumber(String word) {
            if (word.length() == 0)
                return false;
            char[] chars = word.toCharArray();
            for (char ch : chars) {
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }
            return true;

        }

        public static boolean numberArrayLimitation(String[] strings, int limitationCountNumbers, int limitValueNumber) {

            if (strings.length != limitationCountNumbers)
                return false;
            ArrayList<Integer> numbers = new ArrayList<>();

            for (String el : strings){
                if(el.length() == 0)
                    return false;
            }
            for (String word : strings) {
                if (checkNumber(word) && Integer.parseInt(word) <= limitValueNumber) {
                    numbers.add(Integer.parseInt(word));
                }
            }
            if (numbers.size() == limitationCountNumbers) {
                return true;
            }
            return false;

        }
    }

