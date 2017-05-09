package space.harbour.hw7.atm;

import java.util.Iterator;

public class Withdrawer implements Iterable<Withdrawer> {
    private Withdrawer next;
    private final int banknoteValue;
    private int number;

    public Withdrawer(int banknote, int number) {
        this.banknoteValue = banknote;
        this.number = number;
    }

    public ATMResult withdraw(int requestedAmount) {
        if (requestedAmount == 0) {
            return ATMResult.OK;
        }

        int neededNumberOfBanknotesToWithdraw = requestedAmount / banknoteValue;
        int numberOfBanknotesToWithdraw = Math.min(neededNumberOfBanknotesToWithdraw, number);
        int withdrawNow = numberOfBanknotesToWithdraw * banknoteValue;
        int leftAmountToRequest = requestedAmount - withdrawNow;

        if (next == null) {
            if (leftAmountToRequest > 0) {
                return ATMResult.FAIL_NOT_ENOUGH_MONEY;
            } else {
                onSuccessfulWithdrawal(numberOfBanknotesToWithdraw);
                return ATMResult.OK;
            }
        }

        ATMResult nextWithdrawalResult = next.withdraw(leftAmountToRequest);
        if (nextWithdrawalResult == ATMResult.OK) {
            onSuccessfulWithdrawal(numberOfBanknotesToWithdraw);
        }
        return nextWithdrawalResult;
    }

    private void onSuccessfulWithdrawal(int numberOfBanknotesToWithdraw) {
        number -= numberOfBanknotesToWithdraw;
        System.out.println("Withdrawn " + numberOfBanknotesToWithdraw + " banknotes of value " + banknoteValue);
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

    public Withdrawer getNext() {
        return next;
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
