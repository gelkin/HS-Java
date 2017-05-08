package space.harbour.hw5.test;

/**
 * Created by gelkin on 8/05/17.
 */
public class MyAssertionError extends AssertionError {
    String msg = "";

    MyAssertionError() {
    }

    MyAssertionError(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
