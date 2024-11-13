import java.util.InputMismatchException;
import java.util.Scanner;

public class InputInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int number = 0;

        while (!validInput) {
            try {
                System.out.print("Nhập một số nguyên: ");
                number = scanner.nextInt();
                validInput = true; // Đánh dấu đầu vào hợp lệ
            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Bạn phải nhập một số nguyên.");
                scanner.nextLine(); // Xóa bỏ input không hợp lệ khỏi bộ đệm
            }
        }

        System.out.println("Số bạn đã nhập là: " + number);
        scanner.close();
    }
}
