public class Problem1 {
    public static void main(String[] args) {
        final BankAccount bankAccount = new BankAccount("324783495773", 1000);
        Client client1 = new Client("Uktam Nimatov", bankAccount);
        Client client2 = new Client("Toshtemir Nimatov", bankAccount);

        new Thread(() -> {client1.getBankAccount().deposit(300);
                            client1.getBankAccount().withdraw(50);}).start();
        new Thread(() -> {client2.getBankAccount().deposit(203.75);
                            client2.getBankAccount().withdraw(100);}).start();

//        Thread thread1 = new Thread(() -> {
//            client1.getBankAccount().deposit(300);
//            client1.getBankAccount().withdraw(50);
//        });
//        thread1.start();
//
//        Thread thread2 = new Thread(() -> {
//            client2.getBankAccount().deposit(203.75);
//            client2.getBankAccount().withdraw(100);
//        });
//        thread2.start();



    }
}
