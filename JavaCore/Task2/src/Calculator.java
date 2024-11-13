import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số thứ nhất: ");
        double num1 = scanner.nextDouble();

        System.out.print("Nhập số thứ hai: ");
        double num2 = scanner.nextDouble();

        System.out.print("Chọn phép toán (+, -, *, /): ");
        char operator = scanner.next().charAt(0); // Lấy ký tự đầu tiên, bỏ qua khoảng trắng

        double result = 0;
        boolean validOperation = true;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Lỗi: Không thể chia cho 0.");
                    validOperation = false;
                }
                break;
            default:
                System.out.println("Phép toán không hợp lệ.");
                validOperation = false;
                break;
        }

        if (validOperation) {
            System.out.println("Kết quả: " + num1 + " " + operator + " " + num2 + " = " + result);
        }

        scanner.close();
    }
}
