class BankAccount {
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Gửi tiền thành công! Số dư hiện tại: " + balance);
        } else {
            System.out.println("Số tiền gửi phải lớn hơn 0.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Rút tiền thành công! Số dư hiện tại: " + balance);
        } else if (amount > balance) {
            System.out.println("Số tiền rút vượt quá số dư tài khoản.");
        } else {
            System.out.println("Số tiền rút phải lớn hơn 0.");
        }
    }

    public double checkBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}



