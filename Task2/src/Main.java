import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrimeNumbers prime = new PrimeNumbers();
        MultiplicationTable multiplicationTable = new MultiplicationTable();

        System.out.println("Chọn một chương trình:");
        System.out.println("1. Kiểm tra số nguyên tố");
        System.out.println("2. In bảng cửu chương");
        System.out.println("3. Máy tính đơn giản");
        System.out.print("Nhập lựa chọn của bạn: ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                double number;
                do {
                    System.out.print("Nhập một số nguyên dương để kiểm tra: ");
                    number = input.nextDouble();
                    if (number != (int)number || number <= 0) {
                        System.out.println("Số nhập vào phải là số nguyên dương. Vui lòng nhập lại.");
                    }
                } while (number != (int)number || number <= 0);

                int intNumber = (int) number;
                if (prime.checkPrime(intNumber)) {
                    System.out.println("Đúng số nguyên tố rồi đó :v");
                } else {
                    System.out.println("KO phải số nguyên tố :v");
                }
                break;

            case 2:
                multiplicationTable.printMultiplicationTable();
                break;

            case 3:
                Calculator calculator = new Calculator();
                calculator.runCalculator();
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");
                break;
        }

        input.close();
    }
}
