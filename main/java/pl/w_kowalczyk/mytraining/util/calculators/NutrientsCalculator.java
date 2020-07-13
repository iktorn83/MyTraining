package pl.w_kowalczyk.mytraining.util.calculators;

public class NutrientsCalculator {

    public static int calculateProtein(int cpm, int flag) {
        int protein = 0;
        if (flag == 1) protein = (int) (cpm * 0.15);
        if (flag == 2) protein = (int) (cpm * 0.17);
        if (flag == 3) protein = (int) (cpm * 0.25);
        return protein;
    }

    public static int calculateCarbohydrates(int cpm, int flag) {
        int carbohydrates = 0;
        if (flag == 1) carbohydrates = (int) (cpm * 0.55);
        if (flag == 2) carbohydrates = (int) (cpm * 0.58);
        if (flag == 3) carbohydrates = (int) (cpm * 0.50);
        return carbohydrates;
    }

    public static int calculateFat(int cpm, int flag) {
        int fat = 0;
        if (flag == 1) fat = (int) (cpm * 0.30);
        if (flag == 2) fat = (int) (cpm * 0.25);
        if (flag == 3) fat = (int) (cpm * 0.15);
        return fat;
    }
}
