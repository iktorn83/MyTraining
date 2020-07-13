package pl.w_kowalczyk.mytraining.util.calculators;

public class CpmCalculator {

    public static int calculateCpm(String bmrPointer, int bmr)
    {
        double pointer = Double.parseDouble(bmrPointer);
        return (int) ((pointer * bmr) + (0.1 * bmr * pointer));
    }
}


