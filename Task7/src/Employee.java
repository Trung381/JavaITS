public abstract class Employee {
    protected String name;
    protected int age;

    // Constructor
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Phương thức trừu tượng
    public abstract double calculateSalary();

    public void displayInfo() {
        System.out.println("Tên: " + name);
        System.out.println("Tuổi: " + age);
    }
}
