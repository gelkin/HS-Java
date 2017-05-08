package space.harbour.hw5.test;

public class MyAssert {

    public static void assertTrue(boolean expression) {
        if (!expression) {
            throw new MyAssertionError();
        }
    }

    public static void assertFalse(boolean expression) {
        assertTrue(!expression);
    }

    public static void assertEquals(Object expected, Object actual) {
        boolean isOk = true;

        if (expected == null) {
            if (actual != null) {
                isOk = false;
            }
        } else {
            if (actual == null || !expected.equals(actual)) {
                isOk = false;
            }
        }

        assertTrue(isOk);
    }

}
