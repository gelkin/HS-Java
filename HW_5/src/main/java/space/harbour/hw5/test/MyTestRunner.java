package space.harbour.hw5.test;

import java.util.ArrayList;
import java.util.List;

public class MyTestRunner {
    public static void main(String[] args) {
        List<Class<?>> classList = new ArrayList<>();
        classList.add(MyArrayListTest.class);
        classList.add(MyIteratorTest.class);
        MyTestCore.runClasses(classList);
    }
}
