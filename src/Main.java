import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BMICalculator bmiCalculator = new BMICalculator();
        TemperatureConverter tempConverter = new TemperatureConverter();

        System.out.println("Chọn chức năng:");
        System.out.println("1. Tính toán BMI");
        System.out.println("2. Chuyển đổi nhiệt độ");
        System.out.print("Lựa chọn của bạn (1 hoặc 2): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Nhập chiều cao (m): ");
                double height = scanner.nextDouble();

                System.out.print("Nhập cân nặng (kg): ");
                double weight = scanner.nextDouble();

                double bmi = bmiCalculator.calculateBMI(weight, height);
                String category = bmiCalculator.getBMICategory(bmi);

                System.out.printf("Chỉ số BMI của bạn là: %.2f%n", bmi);
                System.out.println("Bạn thuộc loại: " + category);
                break;

            case 2:
                System.out.println("Chọn đơn vị cần chuyển đổi:");
                System.out.println("1. Celsius sang Fahrenheit");
                System.out.println("2. Fahrenheit sang Celsius");
                System.out.print("Lựa chọn của bạn (1 hoặc 2): ");
                int tempChoice = scanner.nextInt();

                System.out.print("Nhập giá trị nhiệt độ: ");
                double temperature = scanner.nextDouble();

                double convertedTemperature;
                switch (tempChoice) {
                    case 1:
                        convertedTemperature = tempConverter.celsiusToFahrenheit(temperature);
                        System.out.printf("Nhiệt độ %.2f°C bằng %.2f°F%n", temperature, convertedTemperature);
                        break;
                    case 2:
                        convertedTemperature = tempConverter.fahrenheitToCelsius(temperature);
                        System.out.printf("Nhiệt độ %.2f°F bằng %.2f°C%n", temperature, convertedTemperature);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                        break;
                }
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");
                break;
        }

        scanner.close();
    }
}
