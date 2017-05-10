package space.harbour.hw8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class BagOfPrimitives {
    private int value1 = 1;
    private boolean boolT = true;
    private boolean boolF = false;
    private String value2 = "abc";
    private int[] intArr = {1, 2, 3};
    private String[] stringArr = {"str1", "str2"};
    private List<List<Integer>> someLists;
    private List[] arrayOfLists;
    private BagOfPrimitives bro;

    private transient int value3 = 3;

    BagOfPrimitives() { }

    BagOfPrimitives(int junkArgument) {
        someLists = new ArrayList<>(10);
        someLists.add(new ArrayList<>(5));
        someLists.add(new ArrayList<>(20));
        someLists.add(new LinkedList<>());
        someLists.get(0).add(1);
        someLists.get(0).add(2);

        someLists.get(1).add(7);
        someLists.get(1).add(6);
        someLists.get(1).add(5);

        someLists.get(2).add(10);
        someLists.get(2).add(20);

        arrayOfLists = new List[3];
        arrayOfLists[0] = someLists.get(0);
        arrayOfLists[1] = someLists.get(1);
        arrayOfLists[2] = someLists.get(2);
        bro = new BagOfPrimitives();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BagOfPrimitives that = (BagOfPrimitives) o;

        if (value1 != that.value1) return false;
        if (value3 != that.value3) return false;
        return value2 != null ? value2.equals(that.value2) : that.value2 == null;
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                '}';
    }
}
