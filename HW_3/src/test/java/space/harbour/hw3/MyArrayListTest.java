package space.harbour.hw3;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;


public class MyArrayListTest {
    private Integer[] sampleArr = {1, 2, 3};
    private Integer elem = 4;
    private int capacity = 10;
    private ArrayList<Integer> sampleList;

    @Before
    public void before() {
        sampleList = new ArrayList<>();
        sampleList.add(4);
        sampleList.add(5);
        sampleList.add(6);
    }

    @Test
    public void arrayContructorTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        Assert.assertEquals(myList.size(), sampleArr.length);
    }

    @Test
    public void emptyContructorTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        Assert.assertEquals(myList.size(), 0);
    }

    @Test
    public void isEmptyTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        Assert.assertTrue(myList.isEmpty());
    }

    @Test
    public void getTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        for (int i = 0; i < sampleArr.length; ++i) {
            Assert.assertEquals(myList.get(i), sampleArr[i]);
        }
    }

    @Test
    public void addSizeIncreaseTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.add(elem);
        Assert.assertEquals(myList.size(), sampleArr.length + 1);
    }

    @Test
    public void addElementTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(this.sampleArr);
        myList.add(elem);
        Assert.assertEquals(myList.get(sampleArr.length), elem);
    }

    @Test
    public void addAllSizeIncreaseTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.addAll(sampleList);
        Assert.assertEquals(myList.size(), sampleArr.length + sampleList.size());
    }

    @Test
    public void addAllElementTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.addAll(sampleList);
        for (int i = 0; i < sampleList.size(); ++i) {
            Assert.assertEquals(myList.get(sampleArr.length + i), sampleList.get(i));
        }
    }

    @Test
    public void clearTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        myList.clear();
        Assert.assertTrue(myList.isEmpty());
    }

    @Test
    public void toStringTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        String trueString = "1 2 3";
        Assert.assertEquals(myList.toString(), trueString);
    }
}
