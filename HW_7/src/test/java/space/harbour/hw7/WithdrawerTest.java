package space.harbour.hw7;

import org.junit.Assert;
import org.junit.Test;
import space.harbour.hw7.atm.Withdrawer;

public class WithdrawerTest {

    @Test
    public void constructorTest() {
        int banknote = 10;
        int number = 4;
        Withdrawer withdrawer = new Withdrawer(banknote, number);
        Assert.assertEquals(withdrawer.getBanknoteValue(), banknote);
        Assert.assertEquals(withdrawer.getNumber(), number);
    }

}
