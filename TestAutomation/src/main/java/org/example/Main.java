package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        //1
        printThreeWords();
        String str = "Test";
        reverseString(str);

        int[] array = {1,0,3};
        invertArray(array);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 2);

        findDuplicates(numbers);
        removeDuplicates(numbers);
    }

    //1
    public static void printThreeWords() {
        System.out.print("Orange\nBanana\nApple\n");
    }

    //2
    public static void reverseString(String str) {
        if (str == null) {
            System.out.println("Input string is null");
        }

        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }

        System.out.println(reversed);
    }

    public static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else array[i] = 0;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void findDuplicates(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .filter(number -> Collections.frequency(numbers, number) > 1)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);

    }

    public static void removeDuplicates(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
