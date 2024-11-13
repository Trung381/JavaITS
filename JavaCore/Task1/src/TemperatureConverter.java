import java.util.Scanner;

public class TemperatureConverter {
    // Định nghĩa hằng số cho phép chuyển đổi nhiệt độ
    private static final double FAHRENHEIT_CONVERSION_FACTOR = 9.0 / 5.0;
    private static final double FAHRENHEIT_CONVERSION_OFFSET = 32.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập giá trị nhiệt độ: ");
        double temperature = scanner.nextDouble();

        System.out.print("Nhập đơn vị hiện tại (C cho Celsius, F cho Fahrenheit): ");
        char unit = scanner.next().charAt(0);

        if (unit == 'C' || unit == 'c') {
            double fahrenheit = temperature * FAHRENHEIT_CONVERSION_FACTOR + FAHRENHEIT_CONVERSION_OFFSET;
            System.out.printf("%.2f độ Celsius = %.2f độ Fahrenheit\n", temperature, fahrenheit);
        } else if (unit == 'F' || unit == 'f') {
            double celsius = (temperature - FAHRENHEIT_CONVERSION_OFFSET) / FAHRENHEIT_CONVERSION_FACTOR;
            System.out.printf("%.2f độ Fahrenheit = %.2f độ Celsius\n", temperature, celsius);
        } else {
            System.out.println("Đơn vị không hợp lệ. Vui lòng nhập 'C' hoặc 'F'.");
        }

        scanner.close();
    }
}
