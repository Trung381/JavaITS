import java.util.InputMismatchException;
import java.util.Scanner;

public class InputInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhập một số nguyên: ");
            int number = scanner.nextInt();
            System.out.println("Số bạn đã nhập là: " + number);
        } catch (InputMismatchException e) {
            System.out.println("Lỗi: Bạn phải nhập một số nguyên.");
        } finally {
            scanner.close();
        }
    }
}
