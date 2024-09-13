import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập chiều cao (mét): ");
        double height = scanner.nextDouble();

        System.out.print("Nhập cân nặng (kg): ");
        double weight = scanner.nextDouble();

        double bmi = weight / (height * height);

        System.out.printf("Chỉ số BMI của bạn là: %.2f\n", bmi);

        if (bmi < 18.5) {
            System.out.println("Bạn thuộc loại: Gầy");
        } else if (bmi >= 18.5 && bmi < 24.9) {
            System.out.println("Bạn thuộc loại: Bình thường");
        } else if (bmi >= 25 && bmi < 29.9) {
            System.out.println("Bạn thuộc loại: Thừa cân");
        } else {
            System.out.println("Bạn thuộc loại: Béo phì");
        }

        scanner.close();
    }
}
