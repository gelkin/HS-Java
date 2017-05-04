package space.harbour.hw3;

import org.junit.Assert;
import org.junit.Test;
import space.harbour.hw3.MyArrayList.MyIterator;

public class MyIteratorTest {
    private Integer[] sampleArr = {1, 2, 3};
    private int capacity = 10;

    @Test
    public void hasNextOnEmptyTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        MyIterator myIter = (MyIterator) myList.iterator();

        Assert.assertFalse(myIter.hasNext());
    }

    @Test
    public void nextOnEmptyTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(capacity);
        MyIterator myIter = (MyIterator) myList.iterator();

        Assert.assertEquals(myIter.next(), null);
    }

    @Test
    public void hasNextOneStepTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        MyIterator myIter = (MyIterator) myList.iterator();

        Assert.assertTrue(myIter.hasNext());
    }

    @Test
    public void hasNextLastStepTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        MyIterator myIter = (MyIterator) myList.iterator();

        for (int i = 0; i < myList.size(); ++i) {
            myIter.next();
        }
        Assert.assertFalse(myIter.hasNext());
    }

    @Test
    public void nextTest() {
        MyArrayList<Integer> myList = new MyArrayList<>(sampleArr);
        MyIterator myIter = (MyIterator) myList.iterator();

        for (int i = 0; i < myList.size(); ++i) {
            Assert.assertEquals(myIter.next(), sampleArr[i]);
        }
    }
}
