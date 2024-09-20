import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mainChoice;

        do {
//            BankAccount bc = new BankAccount("1", 1000);
//            System.out.print(bc);
//            nớ sẽ in ra BankAccount@f6f4d33 nên cần override cái toString để in
            System.out.println("\nChọn chương trình:");
            System.out.println("1. Quản lý tài khoản ngân hàng");
            System.out.println("2. Thực hiện phép tính phân số");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn: ");
            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1 -> {
                    System.out.print("Nhập số tài khoản: ");
                    scanner.nextLine();  // Để tránh bỏ qua nhập số tài khoản do nextInt(), kiểu như cin.ignore() trong c++
                    String accountNumber = scanner.nextLine();
                    System.out.print("Nhập số dư ban đầu: ");
                    double initialBalance = scanner.nextDouble();
                    BankAccount account = new BankAccount(accountNumber, initialBalance);

                    int choice;
                    do {
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

                    /*
                    Khi gọi `System.out.println()` hoặc `System.out.print()` với một đối tượng, Java sẽ tự động gọi phương thức `toString()` của đối tượng đó để lấy chuỗi đại diện của nó.
                    - Phương thức `System.out.print()` yêu cầu một chuỗi để in ra. Nếu cung cấp một đối tượng không phải là chuỗi, Java sẽ tìm cách chuyển đối tượng đó thành chuỗi.
                    - Mặc định, mọi lớp trong Java đều kế thừa lớp `Object`, và lớp `Object` cung cấp một phương thức `toString()` mà mọi lớp đều có thể sử dụng hoặc ghi đè (override).
                    - Nếu bạn không ghi đè phương thức `toString()`, Java sẽ sử dụng phương thức `toString()` của lớp `Object`, vốn chỉ trả về chuỗi dạng "tên lớp@hashcode".
                    - Khi ghi đè `toString()`, Java sẽ gọi phiên bản của phương thức này từ lớp bạn đã định nghĩa.
                    Vì vậy, khi in một đối tượng bằng `System.out.print()`, nó sẽ tự động gọi phương thức `toString()` để hiển thị đối tượng dưới dạng chuỗi.
                    */
//                    System.out.println("Phân số 1: " + fraction1.toString()); không cần nhu này á
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