public class FullTimeEmployee extends Employee {
    private double fixedSalary;

    // Constructor
    public FullTimeEmployee(String name, int age, double fixedSalary) {
        super(name, age);
        this.fixedSalary = fixedSalary;
    }

    // Ghi đè tính lương
    @Override
    public double calculateSalary() {
        return fixedSalary;
    }
    // Ghi đè cái hiển thị thông tin + lương
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Lương: " + calculateSalary());
    }
}
