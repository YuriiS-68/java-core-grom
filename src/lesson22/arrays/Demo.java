package lesson22.arrays;

import lesson22.arrays.ArrayUtils;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        int[] array = {10, 4, 6, 9, 5, 4};

        System.out.println(ArrayUtils.nCount(array, 4));
        System.out.println(Arrays.toString(ArrayUtils.sortAscending(array)));
    }
}
