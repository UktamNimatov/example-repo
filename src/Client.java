public class Client {
    private String fullName;
    private BankAccount bankAccount;

    public Client(String fullName, BankAccount bankAccount) {
        this.fullName = fullName;
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

}
