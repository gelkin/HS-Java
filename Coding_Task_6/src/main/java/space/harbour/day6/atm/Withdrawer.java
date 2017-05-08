package space.harbour.day6.atm;

import java.util.Iterator;

public class Withdrawer implements Iterable<Withdrawer> {
    private Withdrawer next;
    private final int banknoteValue;
    private int number;

    public Withdrawer(int banknote, int number) {
        this.banknoteValue = banknote;
        this.number = number;
    }

    public int withdraw(int requestedAmount) {
        if (requestedAmount == 0) {
            return 0;
        }

        int neededNumberOfBanknotesToWithdraw = requestedAmount / banknoteValue;
        int numberOfBanknotesToWithdraw = Math.min(neededNumberOfBanknotesToWithdraw, number);

        number -= numberOfBanknotesToWithdraw;
        int withdrawnAmount = numberOfBanknotesToWithdraw * banknoteValue;

        System.out.println("Withdrawn " + numberOfBanknotesToWithdraw + " banknotes of value " + banknoteValue);

        if (next == null) {
            return withdrawnAmount;
        }
        return withdrawnAmount + next.withdraw(requestedAmount - withdrawnAmount);
    }

    public void setNext(Withdrawer next) {
        this.next = next;
    }

    public int getBanknoteValue() {
        return banknoteValue;
    }

    public int getNumber() {
        return number;
    }

    public Iterator<Withdrawer> iterator() {
        return new MyIterator(this);
    }

    public class MyIterator implements Iterator<Withdrawer> {
        Withdrawer w;

        public MyIterator(Withdrawer w) {
            this.w = w;
        }

        public boolean hasNext() {
            return w != null;
        }

        public Withdrawer next() {
            Withdrawer curW = w;
            w = w.next;
            return curW;
        }

    }
}
