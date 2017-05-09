package space.harbour.hw7;

import org.junit.Assert;
import org.junit.Test;
import space.harbour.hw7.atm.ATM;
import space.harbour.hw7.atm.Bank;

public class BankTest {

    @Test
    public void getATMTest() {
        Bank bank = new Bank();
        ATM atm1 = bank.getATM();
        ATM atm2 = bank.getATM();

        Assert.assertEquals(atm1, atm2);
    }
}
