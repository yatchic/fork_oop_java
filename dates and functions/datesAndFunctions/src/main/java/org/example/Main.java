package org.example;

public class Main {

    public static int countEvens(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int count = countEvens(arr);
        System.out.println("count = " + count);
    }
}
