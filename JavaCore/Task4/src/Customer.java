import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    private static HashMap<String, String> customers = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void addCustomer() {
        System.out.print("Nhập mã khách hàng: ");
        String customerID = scanner.nextLine();
        System.out.print("Nhập tên khách hàng: ");
        String customerName = scanner.nextLine();
        customers.put(customerID, customerName); // put để thêm vào hashmap
        System.out.println("Đã thêm khách hàng thành công.");
    }

    public static void updateCustomer() {
        System.out.print("Nhập mã khách hàng cần cập nhật: ");
        String customerID = scanner.nextLine();
        if (customers.containsKey(customerID)) { // containsKey check hashmap có phần tử nào chứ key z không
            System.out.print("Nhập tên mới cho khách hàng: ");
            String newName = scanner.nextLine();
            customers.put(customerID, newName);
            System.out.println("Cập nhật thông tin khách hàng thành công.");
        } else {
            System.out.println("Không tìm thấy khách hàng.");
        }
    }

    public static void deleteCustomer() {
        System.out.print("Nhập mã khách hàng cần xóa: ");
        String customerID = scanner.nextLine();
        if (customers.remove(customerID) != null) { // remove cũng là kiểu bool
            System.out.println("Đã xóa khách hàng thành công.");
        } else {
            System.out.println("Không tìm thấy khách hàng.");
        }
    }

    public static void searchCustomer() {
        System.out.print("Nhập mã khách hàng cần tìm: ");
        String customerID = scanner.nextLine();
        if (customers.containsKey(customerID)) {
            System.out.println("Khách hàng: " + customers.get(customerID));
        } else {
            System.out.println("Không tìm thấy khách hàng.");
        }
    }

    public static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Chưa có khách hàng nào trong danh sách.");
        } else {
            System.out.println("Danh sách khách hàng:");
            for (Map.Entry<String, String> entry : customers.entrySet()) { // sử dụng entrySet duyệt for
                System.out.println("Mã khách hàng: " + entry.getKey() + ", Tên khách hàng: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Quản lý khách hàng:");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Cập nhật khách hàng");
            System.out.println("3. Xóa khách hàng");
            System.out.println("4. Tìm kiếm khách hàng");
            System.out.println("5. Xem tất cả khách hàng");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> updateCustomer();
                case 3 -> deleteCustomer();
                case 4 -> searchCustomer();
                case 5 -> viewAllCustomers();
                case 6 -> System.out.println("Đã thoát.");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 6);
    }
}
