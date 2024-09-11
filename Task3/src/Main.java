import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BubbleSort bubbleSort = new BubbleSort();
        CharacterCount characterCount = new CharacterCount();

        System.out.println("Chọn bài tập muốn thực hiện:");
        System.out.println("1. Sắp xếp mảng");
        System.out.println("2. Đếm số lần xuất hiện của ký tự trong chuỗi");
        System.out.print("Your lựa chọn: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Nhập kích thước mảng:");
                int size = scanner.nextInt();
                int[] array = new int[size];

                System.out.println("Nhập các phần tử của mảng:");
                for (int i = 0; i < size; i++) {
                    array[i] = scanner.nextInt();
                }

                bubbleSort.sortArray(array);
                System.out.println("Mảng sau khi sắp xếp:");
                bubbleSort.printArray(array);
                break;

            case 2:
                scanner.nextLine(); // đọc bỏ dòng trống sau khi nhập số để nhập cho 1 chuỗi giống cin.ignore() của c++
                System.out.println("Nhập chuỗi:");
                String input = scanner.nextLine();

                System.out.println("Nhập ký tự cần đếm:");
                char character = scanner.next().charAt(0);

                int count = characterCount.countCharacter(input, character);
                System.out.println("Số lần xuất hiện của ký tự '" + character + "' là: " + count);
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");
                break;
        }

        scanner.close();
    }
}
