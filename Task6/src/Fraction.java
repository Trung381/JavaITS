public class Fraction {
    private int numerator;
    private int denominator;

    // Constructor
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Mẫu số không được bằng 0.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }

    public Fraction subtract(Fraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }


    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Không thể chia cho phân số có tử số bằng 0.");
        }
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }

    private void simplify() {
        int gcd = gcd(numerator, denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    // tim ucln de rut gon ps bằng euclid
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Phương thức hiển thị phân số
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);  // Nếu mẫu số là 1, chỉ hiển thị tử số
        }
        if(denominator <0){
            numerator = - numerator;
            denominator = - denominator;
        }
        return numerator + "/" + denominator;
    }

}
