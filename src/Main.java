import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên đi bạn: ");
        String name = scanner.nextLine();
        System.out.print("À chào: " + name);

        scanner.close();
    }
}
