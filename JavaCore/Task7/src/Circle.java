public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void displayArea() {
        System.out.println("Hình tròn có bán kính: " + radius);
        super.displayArea();
    }
}
