package pl.w_kowalczyk.mytraining.util.calculators.bmr;

import pl.w_kowalczyk.mytraining.ui.application.model.UserModel;

public class MifflinStJeorCalculator extends BaseCalculator {
    public MifflinStJeorCalculator(UserModel userModel) {
        super(userModel);
    }

    public int calculate() {
        if (gender.equals("M")) {
            return (int) ((9.99 * weight) + (6.25 * height) - (4.92 * age) + 5);
        } else {
            return (int) ((9.99 * weight) + (6.25 * height) - (4.92 * age) - 161);
        }
    }
}

