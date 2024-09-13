import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập giá trị nhiệt độ: ");
        double temperature = scanner.nextDouble();

        System.out.print("Nhập đơn vị hiện tại (C cho Celsius, F cho Fahrenheit): ");
        char unit = scanner.next().charAt(0);

        if (unit == 'C' || unit == 'c') {
            double fahrenheit = temperature * 9/5 + 32;
            System.out.printf("%.2f độ Celsius = %.2f độ Fahrenheit\n", temperature, fahrenheit);
        } else if (unit == 'F' || unit == 'f') {
            double celsius = (temperature - 32) * 5/9;
            System.out.printf("%.2f độ Fahrenheit = %.2f độ Celsius\n", temperature, celsius);
        } else {
            System.out.println("Đơn vị không hợp lệ. Vui lòng nhập 'C' hoặc 'F'.");
        }

        scanner.close();
    }
}
