public class MultiplicationTable {
    public static void main(String[] args) {
        System.out.print("Bảng cửu chương 1 -> 10 :v");
        for (int num1 = 1; num1 <= 10; num1++) {
            System.out.print("\n");
            for (int num2 = 1; num2 <= 10; num2++) {
//                System.out.printf("%d * %d = %d \t\t", num2, num1, num1 * num2);
                System.out.printf("%2d * %2d = %4d\t", num2, num1, num1 * num2);
            }
        }

        System.out.println("\n\nBảng chia 1 -> 10 :v");
        // Bảng chia
        for (int num1 = 1; num1 <= 10; num1++) {
            System.out.print("\n");
            for (int num2 = 1; num2 <= 10; num2++) {
                double result = (double) num1 / num2; // Đảm bảo phép chia trả về kết quả chính xác
                System.out.printf("%2d / %2d = %4.2f\t", num1, num2, result);
            }
        }
    }
}
