import java.util.Scanner;

public class Calculator {
    public void runCalculator() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số thứ nhất: ");
        double num1 = scanner.nextDouble();

        System.out.print("Nhập số thứ hai: ");
        double num2 = scanner.nextDouble();

        System.out.print("Chọn phép toán (+, -, *, /): ");
        char operator = scanner.next().charAt(0); // đề phòng cụ nào nhập "+  " thì chỉ lấy "+" thôi

        double result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                System.out.println("Kết quả: " + num1 + " + " + num2 + " = " + result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Kết quả: " + num1 + " - " + num2 + " = " + result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Kết quả: " + num1 + " * " + num2 + " = " + result);
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Kết quả: " + num1 + " / " + num2 + " = " + result);
                } else {
                    System.out.println("Lỗi: Không thể chia cho 0.");
                }
                break;
            default:
                System.out.println("Phép toán không hợp lệ.");
                break;
        }
    }
}
