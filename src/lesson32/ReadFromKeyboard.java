package lesson32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadFromKeyboard {
    public static void main(String[] args)throws Exception {

        readKeyboardWithIOStream();
    }

    private static void readKeyboardWithScanner(){
        //using scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name");

        System.out.println("Hello, " + scanner.nextLine());

        scanner.close();
    }

    private static void readKeyboardWithIOStream() throws Exception {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        System.out.println("Please enter your name");
        String word = br.readLine();
        if (checkChars(word)){
            System.out.println("Hello, " + word + "!");
        }
        else System.out.println("You entered the wrong word");
    }

    private static boolean checkChars(String word)throws Exception{
        if (word == null)
            throw new Exception("Not text");

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLetter(c)){
                return true;
            }
        }
        return false;
    }
}
