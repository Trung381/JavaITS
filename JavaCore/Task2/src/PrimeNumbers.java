import java.util.Scanner;

public class PrimeNumbers {

    // Phương thức kiểm tra số nguyên tố
    public static boolean checkPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số từ người dùng
        System.out.print("Nhập số để kiểm tra: ");
        int number = scanner.nextInt();

        // Kiểm tra và in kết quả
        if (checkPrime(number)) {
            System.out.println(number + " là số nguyên tố.");
        } else {
            System.out.println(number + " không phải là số nguyên tố.");
        }

        scanner.close();
    }
}
