package space.harbour.hw5.test;

import space.harbour.hw5.annotations.Test;
import space.harbour.hw5.classes.MyArrayList.MyIterator;
import space.harbour.hw5.classes.MyArrayList;

public class MyIteratorTest {
    private Integer[] sampleArr = {1, 2, 3};
    private int capacity = 10;

    @Test
    public void hasNextOnEmptyTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        MyIterator myIter = (MyIterator) myList.iterator();

        MyAssert.assertFalse(myIter.hasNext());
    }

    @Test
    public void nextOnEmptyTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        MyIterator myIter = (MyIterator) myList.iterator();

        MyAssert.assertEquals(myIter.next(), null);
    }

    @Test
    public void hasNextOneStepTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        MyIterator myIter = (MyIterator) myList.iterator();

        MyAssert.assertTrue(myIter.hasNext());
    }

    @Test
    public void hasNextLastStepTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        MyIterator myIter = (MyIterator) myList.iterator();

        for (int i = 0; i < myList.size(); ++i) {
            myIter.next();
        }
        MyAssert.assertFalse(myIter.hasNext());
    }

    @Test
    public void nextTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        MyIterator myIter = (MyIterator) myList.iterator();

        for (int i = 0; i < myList.size(); ++i) {
            MyAssert.assertEquals(myIter.next(), sampleArr[i]);
        }
    }
}
