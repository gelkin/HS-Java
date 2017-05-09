package space.harbour.hw7.atm;

public class ATM {
    private Withdrawer withdrawer;

    public ATM() {
        withdrawer = new Withdrawer(5, 10);
        Withdrawer withdrawer2 = new Withdrawer(2, 10);
        Withdrawer withdrawer1 = new Withdrawer(1, 10);

        withdrawer2.setNext(withdrawer1);
        withdrawer.setNext(withdrawer2);
    }

    public ATMResult withdraw(int requestedAmount) {
        return withdrawer.withdraw(requestedAmount);
    }

    public int getBalance() {
        int res = 0;
        for (Withdrawer w : withdrawer) {
            res += w.getBanknoteValue() * w.getNumber();
        }
        return res;
    }

    @Override
    public ATM clone() {
        Withdrawer first = null;
        Withdrawer current = null;
        for (Withdrawer w : withdrawer) {
            if (first == null) {
                first = new Withdrawer(w.getBanknoteValue(), w.getNumber());
                current = first;
            } else {
                current.setNext(new Withdrawer(w.getBanknoteValue(), w.getNumber()));
                current = current.getNext();
            }
        }

        ATM res = new ATM();
        res.withdrawer = first;
        return res;
    }

    @Override
    public boolean equals(Object o) {
        ATM other = (ATM) o;
        Withdrawer otherWithdrawer = other.withdrawer;
        for (Withdrawer w : withdrawer) {
            if (otherWithdrawer.getBanknoteValue() != w.getBanknoteValue() ||
                otherWithdrawer.getNumber() != w.getNumber()) {
                return false;
            }
            otherWithdrawer = otherWithdrawer.getNext();
        }
        return true;
    }
}
