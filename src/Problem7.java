import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Problem7 {
    public static void main(String[] args) {
        NewBankAccount account1 = new NewBankAccount("12345-678", 500.00);
        NewBankAccount account2 = new NewBankAccount("98765-432", 1000.00);

        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();
    }
}

class NewBankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock = new ReentrantLock();

    public NewBankAccount(String accountNumber, double balance) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public boolean withdraw(double amount) {
        if (lock.tryLock()) {
            try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            balance -= amount;
            System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
            return true;
            }finally {
                lock.unlock();
            }
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (lock.tryLock()) {
            try{
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            balance += amount;
            System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName(), amount);
            return true;
        }finally{
            lock.unlock();
        }
        }
        return false;
    }

    public boolean transfer(NewBankAccount destinationAccount, double amount) {
        if (withdraw(amount)) {
            destinationAccount.deposit(amount);
            return true;
        } else {
            System.out.printf("%s: Destination account busy. Refunding money\n", Thread.currentThread().getName());
            this.deposit(amount);
            return false;
        }
    }
}

     class Transfer implements Runnable{
        private NewBankAccount sourceAccount, destinationAccount;
        private double amount;

        public Transfer(NewBankAccount sourceAccount, NewBankAccount destinationAccount, double amount) {
            this.sourceAccount = sourceAccount;
            this.destinationAccount = destinationAccount;
            this.amount = amount;
        }

        @Override
        public void run() {
            while (!sourceAccount.transfer(destinationAccount, amount))
                continue;
            System.out.printf("%s completed\n", Thread.currentThread().getName());
        }
    }


