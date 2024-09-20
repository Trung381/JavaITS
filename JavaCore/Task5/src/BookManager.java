import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BookManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên sách: ");
        String bookTitle = scanner.nextLine();

        System.out.print("Nhập tên tác giả: ");
        String author = scanner.nextLine();

        System.out.print("Nhập năm xuất bản: ");
        int yearPublished = scanner.nextInt();

        try (FileWriter writer = new FileWriter("Task5/src/books.txt", true)) {
            writer.write("Tên sách: " + bookTitle + "\n");
            writer.write("Tác giả: " + author + "\n");
            writer.write("Năm xuất bản: " + yearPublished + "\n");
            writer.write("|-----------------------------------|\n");
            System.out.println("Thông tin sách đã được lưu vào file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }

        scanner.close();
    }
}
