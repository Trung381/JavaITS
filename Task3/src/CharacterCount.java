import java.util.Scanner;

public class CharacterCount {

    public static int countCharacter(String input, char character) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập chuỗi: ");
        String input = scanner.nextLine();

        System.out.print("Nhập ký tự cần đếm: ");
        char character = scanner.next().charAt(0);

        int count = countCharacter(input, character);

        System.out.println("Số lần xuất hiện của ký tự '" + character + "' trong chuỗi là: " + count);

        scanner.close();
    }
}
