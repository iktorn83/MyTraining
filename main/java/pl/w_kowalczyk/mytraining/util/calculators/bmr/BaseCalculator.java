package pl.w_kowalczyk.mytraining.util.calculators.bmr;

import pl.w_kowalczyk.mytraining.ui.application.model.UserModel;

abstract class BaseCalculator {
    int weight;
     int height;
     int age;
     int muscleMass;
     String gender;

    BaseCalculator(UserModel userModel) {
        this.weight = (int) userModel.getWeight();
        this.height = (int) userModel.getHeight();
        this.age = userModel.getAge();
        this.muscleMass = (int) userModel.getMuscleMass();
        this.gender = userModel.getGender();
    }

    public abstract int calculate();
}

