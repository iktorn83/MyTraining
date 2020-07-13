package pl.w_kowalczyk.mytraining.util.calculators.bmr;

import pl.w_kowalczyk.mytraining.ui.application.model.UserModel;

public class HarrisBenedictCalculator extends BaseCalculator {
    public HarrisBenedictCalculator(UserModel userModel) {
        super(userModel);
    }

    public int calculate() {
        if (gender.equals("M")) {
            return (int) (66 + (13.7 * weight) + (5 * height) - (6.76 * age));
        } else {
            return (int) (655 + (9.6 * weight) + (1.8 * height) - (4.7 * age));
        }
    }
}
