package pl.w_kowalczyk.mytraining.util.calculators.bmr;

import pl.w_kowalczyk.mytraining.ui.application.model.UserModel;

public class KatchMcArdleCalculator extends BaseCalculator {
    public KatchMcArdleCalculator(UserModel userModel) {
        super(userModel);
    }

    public int calculate() {
        if (muscleMass == 0) {
            return 0;
        } else {
            return (int) (370 + (21.6 * muscleMass));
        }
    }
}
