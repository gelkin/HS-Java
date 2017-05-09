package space.harbour.hw7.atm;

public class Bank {
    private ATM atm;

    public Bank() {
        atm = new ATM();
    }

    public ATM getATM() {
        return atm.clone();
    }
}
