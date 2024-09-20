public abstract class Shape {
    // Phương thức trừu tượng để mấy thằng sau override
    public abstract double calculateArea();

    public void displayArea() {
        System.out.println("Diện tích: " + calculateArea());
    }
}
