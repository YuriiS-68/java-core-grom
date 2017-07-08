package lesson5;

/**
 * Created by Skorodielov on 06.07.2017.
 */
public class Exersicel {
    public static void main(String[] args) {
        int[] array = {-10, 0, 110, 555, 1000, -45, 0, 60};
        System.out.println(maxElement(array));
        System.out.println(nCount(array, 60));
    }

    public static int maxElement(int [] array){
        int max = array[0];

        for(int el : array){
            if (el > max)
                max = el;
        }
        return max;
    }

    public static int nCount(int[] array, int n){
        int count = 0;

        for(int el : array){
            if (el == n)
                count++;
        }
        return count;
    }
}
