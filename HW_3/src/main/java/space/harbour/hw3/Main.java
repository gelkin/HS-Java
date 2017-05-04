package space.harbour.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Integer[] rawArr = {0, 5, -3, 20, 10};
        Integer[] secondRawArr = {7, 8, 9};

        ArrayList<Integer> arrList = new ArrayList<Integer>();
        arrList.addAll(Arrays.asList(secondRawArr));

        MyArrayList<Integer> myList = new MyArrayList<Integer>(rawArr);
        System.out.println("Array:");
        System.out.println(myList.toString());

        System.out.println("\nMax element: " + Collections.max(myList));

        System.out.println("\nBefore adding:");
        System.out.println(myList.toString());

        Collections.addAll(myList, 3, 7, 90);
        System.out.println("\nAfter adding [3, 7, 90]:");
        System.out.println(myList.toString());
    }
}
