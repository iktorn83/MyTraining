package pl.w_kowalczyk.mytraining.util.calculators;

public class CaloriesCalculator {
    public static int kilocaloriesToGrams(int kcals, boolean fat) {
        if (!fat) {
            return kcals / 4;
        } else {
            return kcals / 9;
        }
    }
}
