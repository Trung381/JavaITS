import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choice;
        do{
            System.out.println("Chọn chương trình:");
            System.out.println("1. Quản lý nhân viên");
            System.out.println("2. Quản lý shape");
            System.out.println("3. Ê xịt");
            System.out.print("Your lựa chọn: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch(choice){
                case 1 -> {
                    ArrayList<Employee> employees = new ArrayList<>();

                    employees.add(new FullTimeEmployee("Vũ Văn Trung", 20, 15000000));

                    employees.add(new PartTimeEmployee("Ai đó 1", 22, 50000, 120));  // 120 giờ làm việc

                    for (Employee employee : employees) {
                        employee.displayInfo();
                        System.out.println("-------------------------");
                    }
                }
                case 2 -> {
                    ArrayList<Shape> shapes = new ArrayList<>();

                    shapes.add(new Circle(5));
                    shapes.add(new Rectangle(4, 6));

                    for (Shape shape : shapes) {
                        shape.displayArea();
                        System.out.println("-------------------------");
                    }
                }
                case 3 -> System.out.println("Ê xịt");
                default -> System.out.println("Chọn lại 1 trong 3 cái kia thôi cụ");
            }
        } while(choice != 3);
    }
}
