package space.harbour.hw3;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Integer[] rawArr = {0, 5, -3, 20};
        Integer[] secondRawArr = {7, 8, 9};

        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for (int i : secondRawArr) {
            arrList.add(i);
        }

        MyArrayList<Integer> myList = new MyArrayList<Integer>(rawArr);
        System.out.println("Array:");
        for (int i : myList) {
            System.out.print(i + " ");
        }
        System.out.println("\nMax element: " + Collections.max(myList));


        System.out.println("\nBefore adding:");
        for (int i : myList) {
            System.out.print(i + " ");
        }
        Collections.addAll(myList, 3, 7, 90);
        System.out.println("\nAfter adding [3, 7, 90]:");
        for (int i : myList) {
            System.out.print(i + " ");
        }
    }
}
