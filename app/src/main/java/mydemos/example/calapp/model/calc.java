package mydemos.example.calapp.model;

public class calc {
    private double calorie;

    public double getMaleCalorie(double height, double weight, int age){
        this.calorie = 66.47 + (13.76 * weight) + (5 * height) - (6.76 * age);
        return calorie;
    }
    public double getFemaleCalorie(double height, double weight, int age){
        this.calorie = 655.1 + (9.56 * weight) + (1.85 * height) - (4.88 * age);
        return calorie;
    }
}
