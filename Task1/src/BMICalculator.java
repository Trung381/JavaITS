public class BMICalculator {
    public double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    public String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Gầy";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Bình thường";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Thừa cân";
        } else {
            return "Béo phì";
        }
    }
}
