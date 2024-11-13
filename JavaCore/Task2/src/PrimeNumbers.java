import java.util.Scanner;

public class PrimeNumbers {

    public static boolean checkPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        // Chỉ lặp qua các số lẻ từ 3 đến căn bậc hai của num vì có 2 là số nguyên tố chẵn duy nhất
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số để kiểm tra: ");
        int number = scanner.nextInt();

        if (checkPrime(number)) {
            System.out.println(number + " là số nguyên tố.");
        } else {
            System.out.println(number + " không phải là số nguyên tố.");
        }

        scanner.close();
    }
}
