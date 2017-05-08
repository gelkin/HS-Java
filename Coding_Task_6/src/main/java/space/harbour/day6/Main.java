package space.harbour.day6;


import space.harbour.day6.atm.ATM;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        System.out.println("Current balance: " + atm.getBalance());
        int requestedAmount = 23;
        int res = atm.withdraw(requestedAmount);
        System.out.println("Requested to withdraw: " + requestedAmount);
        System.out.println("Actually withdrawn: " + res);

        System.out.println("Current balance: " + atm.getBalance());
    }
}

