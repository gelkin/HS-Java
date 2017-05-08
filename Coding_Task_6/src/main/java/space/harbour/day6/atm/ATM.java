package space.harbour.day6.atm;

public class ATM {
    Withdrawer withdrawer;

    public ATM() {
        withdrawer = new Withdrawer(5, 10);
        Withdrawer withdrawer2 = new Withdrawer(2, 10);
        Withdrawer withdrawer1 = new Withdrawer(1, 10);

        withdrawer2.setNext(withdrawer1);
        withdrawer.setNext(withdrawer2);
    }

    public int withdraw(int requestedAmount) {
        return withdrawer.withdraw(requestedAmount);
    }

    public int getBalance() {
        int res = 0;
        for (Withdrawer w : withdrawer) {
            res += w.getBanknoteValue() * w.getNumber();
        }
        return res;
    }
}
