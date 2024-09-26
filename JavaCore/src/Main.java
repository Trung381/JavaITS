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

# Cấu hình cổng server
server.port=8081


# Cấu hình datasource (cơ sở dữ liệu)
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=pass123


# Hibernate JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


# Cấu hình logging
logging.level.org.springframework=INFO