import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private static ArrayList<String> library = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void addBook() {
        System.out.print("Nhập tên sách: ");
        String title = scanner.nextLine();
        library.add(title);
        System.out.println("Đã thêm sách thành công.");
    }

    public static void updateBook() {
        System.out.print("Nhập tên sách cần cập nhật: ");
        String oldTitle = scanner.nextLine();
        if (library.contains(oldTitle)) {
            System.out.print("Nhập tên sách mới: ");
            String newTitle = scanner.nextLine();
            library.set(library.indexOf(oldTitle), newTitle);
            System.out.println("Cập nhật sách thành công.");
        } else {
            System.out.println("Không tìm thấy sách.");
        }
    }

    public static void deleteBook() {
        System.out.print("Nhập tên sách cần xóa: ");
        String title = scanner.nextLine();
        if (library.remove(title)) {
            System.out.println("Đã xóa sách thành công.");
        } else {
            System.out.println("Không tìm thấy sách.");
        }
    }

    public static void searchBook() {
        System.out.print("Nhập tên sách cần tìm: ");
        String title = scanner.nextLine();
        if (library.contains(title)) {
            System.out.println("Tìm thấy sách: " + title);
        } else {
            System.out.println("Không tìm thấy sách.");
        }
    }

    public static void viewAllBooks() {
        if (library.isEmpty()) {
            System.out.println("Thư viện chưa có sách nào.");
        } else {
            System.out.println("Danh sách các sách trong thư viện:");
            for (int i = 0; i < library.size(); i++) {
                System.out.println((i + 1) + ". " + library.get(i));
            }
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nQuản lý thư viện:");
            System.out.println("1. Thêm sách");
            System.out.println("2. Cập nhật sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Xem tất cả sách");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // giống cin.ignore() trong c++, loại bỏ ký tự xuống dòng thừa

            switch (choice) {
                case 1 -> addBook();
                case 2 -> updateBook();
                case 3 -> deleteBook();
                case 4 -> searchBook();
                case 5 -> viewAllBooks();
                case 6 -> System.out.println("Đã thoát.");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 6);
    }
}
