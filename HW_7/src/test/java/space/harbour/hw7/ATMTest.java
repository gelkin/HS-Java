package space.harbour.hw7;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import space.harbour.hw7.atm.ATM;
import space.harbour.hw7.atm.ATMResult;

import java.util.ArrayList;
import java.util.List;


public class ATMTest {
    private static final int BASIC_ATM_AMOUNT = 80;

    @Test
    public void getBalanceTest() {
        ATM atm = new ATM();
        Assert.assertEquals(atm.getBalance(), BASIC_ATM_AMOUNT);
    }

    @Test
    public void withdrawATMResultTest() {
        ATM atm = new ATM();
        int toWithdraw = 18;
        Assert.assertEquals(atm.withdraw(toWithdraw), ATMResult.OK);
    }

    @Test
    public void withdrawAmountTest() {
        int[] amounts = {0, 4, 18, 30, 80};
        for (int toWithdraw : amounts) {
            ATM atm = new ATM();
            Assert.assertEquals(atm.withdraw(toWithdraw), ATMResult.OK);
            Assert.assertEquals(atm.getBalance(), BASIC_ATM_AMOUNT - toWithdraw);
        }
    }

    @Test
    public void withdrawFailTest() {
        ATM atm = new ATM();
        int toWithdraw = BASIC_ATM_AMOUNT + 100;
        Assert.assertEquals(atm.withdraw(toWithdraw), ATMResult.FAIL_NOT_ENOUGH_MONEY);
    }

    @Test
    public void equalsSimpleTest() {
        ATM atm = new ATM();
        Assert.assertEquals(atm, atm);
    }

    @Test
    public void equalsOkTest() {
        ATM atmFirst = new ATM();
        ATM atmSecond = new ATM();
        Assert.assertEquals(atmFirst, atmSecond);
    }

    @Test
    public void equalsFailTest() {
        ATM atmFirst = new ATM();
        ATM atmSecond = new ATM();
        atmSecond.withdraw(10);

        Assert.assertNotEquals(atmFirst, atmSecond);
    }

    @Test
    public void cloneTest() {
        ATM atm = new ATM();
        atm.withdraw(18);
        ATM clonedATM = atm.clone();
        Assert.assertEquals(atm, clonedATM);
    }
}
