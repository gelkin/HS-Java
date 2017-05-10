package space.harbour.hw8;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MyGSONTest {
    static Gson gson = new Gson();

    @Test
    public void primitiveTest() {
        int i = 10;
        Assert.assertEquals(MyGSON.toJSON(i), gson.toJson(i));

        byte b = 10;
        Assert.assertEquals(MyGSON.toJSON(b), gson.toJson(b));

        boolean bool = true;
        Assert.assertEquals(MyGSON.toJSON(bool), gson.toJson(bool));
    }

    @Test
    public void stringTest() {
        String s = "Sample string";
        Assert.assertEquals(MyGSON.toJSON(s), gson.toJson(s));
    }

    @Test
    public void arrayTest() {
        int[] arrInt = {1, 2, 3};
        Assert.assertEquals(MyGSON.toJSON(arrInt), gson.toJson(arrInt));

        String[] arrString = {"a", "bd", "hello"};
        Assert.assertEquals(MyGSON.toJSON(arrString), gson.toJson(arrString));
    }

    @Test
    public void iterableTest() {
        List<Iterable> iterList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        Set<Integer> intSet = new HashSet<>();
        for (int i = 0; i < 5; ++i) {
            intList.add(i);
            intSet.add(i);
        }

        Set<String> strSet = new HashSet<>();
        strSet.add("Sample string 1");
        strSet.add("Sample string 2");

        // add iterables to the list
        iterList.add(intList);
        iterList.add(strSet);
        iterList.add(intSet);

        Assert.assertEquals(MyGSON.toJSON(iterList), gson.toJson(iterList));
    }

    @Test
    public void complexClassTest() {
        BagOfPrimitives x = new BagOfPrimitives(10);
        Assert.assertEquals(MyGSON.toJSON(x), gson.toJson(x));
    }
}
