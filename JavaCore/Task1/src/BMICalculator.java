import java.util.Scanner;

public class BMICalculator {
    private static final double UNDERWEIGHT_THRESHOLD = 18.5;
    private static final double NORMAL_WEIGHT_THRESHOLD = 25.0;
    private static final double OVERWEIGHT_THRESHOLD = 30.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập chiều cao (mét): ");
        double height = scanner.nextDouble();

        System.out.print("Nhập cân nặng (kg): ");
        double weight = scanner.nextDouble();

        double bmi = weight / (height * height);

        System.out.printf("Chỉ số BMI của bạn là: %.2f\n", bmi);

        if (bmi < UNDERWEIGHT_THRESHOLD) {
            System.out.println("Bạn thuộc loại: Gầy");
        } else if (bmi < NORMAL_WEIGHT_THRESHOLD) {
            System.out.println("Bạn thuộc loại: Bình thường");
        } else if (bmi < OVERWEIGHT_THRESHOLD) {
            System.out.println("Bạn thuộc loại: Thừa cân");
        } else {
            System.out.println("Bạn thuộc loại: Béo phì");
        }

        scanner.close();
    }
}
