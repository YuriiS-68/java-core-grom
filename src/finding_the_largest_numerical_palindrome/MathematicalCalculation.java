package finding_the_largest_numerical_palindrome;

import java.text.SimpleDateFormat;
import java.util.*;

public class MathematicalCalculation {
    //найти 5ти значные простые числа
    //определить является ли число палиндромом
    //занести результат перемножения всех пятизначных чисел в список, если он является числом палиндромом
    //определить самое большое число палиндром из списка

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println(findingTheLargest((List<Long>) findingPrimeNumbers(100, 999)));

        long finish = System.currentTimeMillis() - start;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(finish);
        SimpleDateFormat format = new SimpleDateFormat("mm : ss");
        System.out.println(format.format(cal.getTime()));
    }

    public static long findingTheLargest(List<Long> array){

        long max = array.get(0);

        for (Long number : array) {
            if (number > max){
                max = number;
            }
        }
        return max;
    }

    public static ArrayList<Long> findingPrimeNumbers(long start, long end){

        //int divisor = 0;

        ArrayList<Long> primeNumbers = new ArrayList<>();

        for (long i = 2; i <= end; i++) {
            boolean divisor = true;
            for (long j = 2; j <= i; j++) {
                if (i % j == 0 && i != j){
                    divisor = false;
                    break;
                }
            }
            if (divisor){
                primeNumbers.add(i);
            }
        }
        //System.out.println(primeNumbers);

        ArrayList<Long> primePalindromeNumbers = new ArrayList<>();
        for (long i = 0; i < primeNumbers.size(); i++) {
            for (long j = i; j < primeNumbers.size(); j++) {
                long multiplicationResult = primeNumbers.get((int) i) * primeNumbers.get((int) j);
                if (checkPalindrome(multiplicationResult)){
                    primePalindromeNumbers.add(multiplicationResult);
                }
            }
        }
        return primePalindromeNumbers;
    }

    private static boolean checkPalindrome(Long number){

        String origin = number.toString();

        int i = origin.length() - 1;
        int j = 0;

        while (i > j){
            if (origin.charAt(i) != origin.charAt(j)){
                return false;
            }
            i--;
            j++;
        }
        return true;
    }

}
