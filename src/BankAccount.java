import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final ReentrantLock lock;
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        lock = new ReentrantLock();
    }

    public void withdraw(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                status = true;
                try {
                    if (balance < amount) {
                        System.out.println("You cannot withdraw this amount as there is no such amount of money in the balance");
                        return;
                    }
                    balance -= amount;
                    System.out.println("Withdrawn amount: " + amount + ". Current balance: " + balance);
                } finally {
                    lock.unlock();
                }
            }else{
                System.out.println("Could not get the lock");
            }
        } catch (InterruptedException ignored) {
        }
        System.out.println("Transaction status is " + status);
    }

    public void deposit(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                status = true;
                if (amount <= 0) {
                    System.out.println("The deposit amount cannot be equal to or less than zero");
                    return;
                }
                balance += amount;
                System.out.println("Deposited amount: " + amount + ". Current balance: " + balance);
            }else{
                System.out.println("Could not get the lock");
            }
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
        System.out.println("Transaction status is " + status);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println(this.accountNumber);
	System.out.println();
}    
}
