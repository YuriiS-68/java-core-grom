package lesson33;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadFromKeyboard {
    public static void main(String[] args) {

        readKeyboardWithIOStream2();
    }

    private static void readKeyboardWithIOStream1() {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        System.out.println("Please enter your name");

        try {
            String word = br.readLine();
            if (checkChars(word)) {
                System.out.println("Hello, " + word + "!");
            } else System.out.println("You entered the wrong word");
        } catch (IOException e) {
            System.err.println("Reading from keyboard failed");
        } finally {
            try {
                reader.close();
                br.close();
            } catch (IOException e) {
                System.err.println("Closing streams failed");
            }
        }
    }

    private static void readKeyboardWithIOStream2() {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        System.out.println("Please enter your name");

        try {
            String word = br.readLine();
            if (checkChars(word)) {
                System.out.println("Hello, " + word + "!");
            } else System.out.println("You entered the wrong word");
        } catch (IOException e) {
            System.err.println("Reading from keyboard failed");
        } finally {
            try {
                reader.close();
                br.close();
            }catch (IOException e){
                System.err.println("Closing streams failed");
            }
        }
    }

    private static boolean checkChars(String word){

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
}
