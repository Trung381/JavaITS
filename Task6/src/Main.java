import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mainChoice;

        do {
            // Menu chính
            System.out.println("\nChọn chương trình:");
            System.out.println("1. Quản lý tài khoản ngân hàng");
            System.out.println("2. Thực hiện phép tính phân số");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn: ");
            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1 -> {
                    // Quản lý tài khoản ngân hàng
                    System.out.print("Nhập số tài khoản: ");
                    scanner.nextLine();  // Để tránh bỏ qua nhập số tài khoản do nextInt()
                    String accountNumber = scanner.nextLine();
                    System.out.print("Nhập số dư ban đầu: ");
                    double initialBalance = scanner.nextDouble();
                    BankAccount account = new BankAccount(accountNumber, initialBalance);

                    int choice;
                    do {
                        // Menu quản lý tài khoản ngân hàng
                        System.out.println("\nQuản lý tài khoản ngân hàng:");
                        System.out.println("1. Gửi tiền");
                        System.out.println("2. Rút tiền");
                        System.out.println("3. Kiểm tra số dư");
                        System.out.println("4. Thoát");
                        System.out.print("Lựa chọn: ");
                        choice = scanner.nextInt();

                        switch (choice) {
                            case 1 -> {
                                System.out.print("Nhập số tiền cần gửi: ");
                                double depositAmount = scanner.nextDouble();
                                account.deposit(depositAmount);
                            }
                            case 2 -> {
                                System.out.print("Nhập số tiền cần rút: ");
                                double withdrawAmount = scanner.nextDouble();
                                account.withdraw(withdrawAmount);
                            }
                            case 3 -> System.out.println("Số dư hiện tại: " + account.checkBalance());
                            case 4 -> System.out.println("Thoát quản lý tài khoản.");
                            default -> System.out.println("Lựa chọn không hợp lệ.");
                        }
                    } while (choice != 4);
                }
                case 2 -> {
                    // Thực hiện phép tính phân số
                    System.out.print("Nhập tử số của phân số 1: ");
                    int numerator1 = scanner.nextInt();
                    System.out.print("Nhập mẫu số của phân số 1: ");
                    int denominator1 = scanner.nextInt();
                    Fraction fraction1 = new Fraction(numerator1, denominator1);

                    System.out.print("Nhập tử số của phân số 2: ");
                    int numerator2 = scanner.nextInt();
                    System.out.print("Nhập mẫu số của phân số 2: ");
                    int denominator2 = scanner.nextInt();
                    Fraction fraction2 = new Fraction(numerator2, denominator2);

                    Fraction sum = fraction1.add(fraction2);
                    Fraction difference = fraction1.subtract(fraction2);
                    Fraction product = fraction1.multiply(fraction2);
                    Fraction quotient = fraction1.divide(fraction2);

                    // Hiển thị kết quả
                    System.out.println("Phân số 1: " + fraction1);
                    System.out.println("Phân số 2: " + fraction2);
                    System.out.println("Tổng: " + sum);
                    System.out.println("Hiệu: " + difference);
                    System.out.println("Tích: " + product);
                    System.out.println("Thương: " + quotient);
                }
                case 3 -> System.out.println("Thoát chương trình.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (mainChoice != 3);

        scanner.close();
    }
}