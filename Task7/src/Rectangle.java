public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public void displayArea() {
        System.out.println("Hình chữ nhật có chiều dài: " + length + " và chiều rộng: " + width);
        super.displayArea();
    }
}
