package space.harbour.hw5.test;

import space.harbour.hw5.annotations.After;
import space.harbour.hw5.annotations.Before;
import space.harbour.hw5.annotations.Test;
import space.harbour.hw5.classes.MyArrayList;

import java.util.ArrayList;


public class MyArrayListTest {
    private Integer[] sampleArr = {1, 2, 3};
    private Integer elem = 4;
    private int capacity = 10;
    private ArrayList<Integer> sampleList;

    @Before
    public void before() {
        System.out.println("Before method runs");
        sampleList = new ArrayList<>();
        sampleList.add(4);
        sampleList.add(5);
        sampleList.add(6);
    }

    @After
    public void after() {
        System.out.println("After method runs");
    }

    @Test
    public void arrayContructorTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        MyAssert.assertEquals(myList.size(), sampleArr.length);
    }

    @Test
    public void emptyContructorTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        MyAssert.assertEquals(myList.size(), 0);
    }

    @Test
    public void isEmptyTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        MyAssert.assertTrue(myList.isEmpty());
    }

    @Test
    public void getTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        for (int i = 0; i < sampleArr.length; ++i) {
            MyAssert.assertEquals(myList.get(i), sampleArr[i]);
        }
    }

    @Test
    public void addSizeIncreaseTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.add(elem);
        MyAssert.assertEquals(myList.size(), sampleArr.length + 1);
    }

    @Test
    public void addElementTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(this.sampleArr);
        myList.add(elem);
        MyAssert.assertEquals(myList.get(sampleArr.length), elem);
    }

    @Test
    public void addAllSizeIncreaseTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.addAll(sampleList);
        MyAssert.assertEquals(myList.size(), sampleArr.length + sampleList.size());
    }

    @Test
    public void addAllElementTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.addAll(sampleList);
        for (int i = 0; i < sampleList.size(); ++i) {
            MyAssert.assertEquals(myList.get(sampleArr.length + i), sampleList.get(i));
        }
    }

    @Test
    public void clearTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.clear();
        MyAssert.assertTrue(myList.isEmpty());
    }

    @Test
    public void toStringTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        String trueString = "1 2 3";
        MyAssert.assertEquals(myList.toString(), trueString);
    }
}
