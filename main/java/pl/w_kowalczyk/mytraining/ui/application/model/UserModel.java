package pl.w_kowalczyk.mytraining.ui.application.model;

public class UserModel {

    private String userId;
    private String gender;
    private double height;
    private double weight;
    private int age;
    private double muscleMass;
    private double bmrPointer;
    private int calories;

    public UserModel(String userId, String gender, String height, String weight, String age, String muscleMass, String bmrPointer, String calories) {

        this.userId = userId;
        this.gender = gender;
        this.height = Double.parseDouble(height);
        this.weight = Double.parseDouble(weight);
        this.age = Integer.parseInt(age);
        this.muscleMass = Double.parseDouble(muscleMass);
        this.bmrPointer = Double.parseDouble(bmrPointer);
        this.calories = Integer.parseInt(calories);
    }




    public String getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public double getMuscleMass() {
        return muscleMass;
    }

    public double getBmrPointer() {
        return bmrPointer;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", muscleMass=" + muscleMass +
                ", bmrPointer=" + bmrPointer +
                ", calories=" + calories +
                '}';
    }
}
