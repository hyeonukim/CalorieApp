package mydemos.example.calapp.model;

public class calc {
    private double calorie;

    //gets male calorie suggested intake to lose weight
    public double getMaleLoseCalorie(double height, double weight, int age){
        this.calorie = 66.47 + (13.76 * weight) + (5 * height) - (6.76 * age) - 500;
        if (calorie <= 0){
            calorie = 0;
        }
        return calorie;
    }
    //gets male calorie suggested intake to maintain weight
    public double getMaleMaintainCalorie(double height, double weight, int age){
        this.calorie = 66.47 + (13.76 * weight) + (5 * height) - (6.76 * age);
        if (calorie <= 0){
            calorie = 0;
        }
        return calorie;
    }
    //gets male calorie suggested intake to gain weight
    public double getMaleGainCalorie(double height, double weight, int age){
        this.calorie = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.88 * age))) * 1.15;
        if (calorie <= 0){
            calorie = 0;
        }
        return calorie;
    }
    //gets female calorie suggested intake to lose weight
    public double getFemaleLoseCalorie(double height, double weight, int age){
        this.calorie = 655.1 + (9.56 * weight) + (1.85 * height) - (4.88 * age) - 500;
        if (calorie <= 0){
            calorie = 0;
        }
        return calorie;
    }
    //gets female calorie suggested intake to maintain weight
    public double getFemaleMaintainCalorie(double height, double weight, int age){
        this.calorie = 655.1 + (9.56 * weight) + (1.85 * height) - (4.88 * age);
        if (calorie <= 0){
            calorie = 0;
        }
        return calorie;
    }
    //gets female calorie suggested intake to gain weight
    public double getFemaleGainCalorie(double height, double weight, int age){
        this.calorie = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.88 * age))) * 1.15;
        if (calorie <= 0){
            calorie = 0;
        }
        return calorie;
    }
}
