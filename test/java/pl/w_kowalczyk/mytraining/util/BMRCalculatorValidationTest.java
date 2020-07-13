package pl.w_kowalczyk.mytraining.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pl.w_kowalczyk.mytraining.ui.application.model.UserModel;
import pl.w_kowalczyk.mytraining.util.calculators.bmr.HarrisBenedictCalculator;
import pl.w_kowalczyk.mytraining.util.calculators.bmr.KatchMcArdleCalculator;
import pl.w_kowalczyk.mytraining.util.calculators.bmr.MifflinStJeorCalculator;

public class BMRCalculatorValidationTest {
    private List<Integer> harrisBenedictExpedted = new ArrayList<>();
    private List<Integer> harrisBenedictResult = new ArrayList<>();
    private List<Integer> katchMcArdleExpedted = new ArrayList<>();
    private List<Integer> katchMcArdleResult = new ArrayList<>();
    private List<Integer> mifflinStJeorExpedted = new ArrayList<>();
    private List<Integer> mifflinStJeorResult = new ArrayList<>();
    private UserModel userModel1 = new UserModel(
            "1", "M", "190", "90", "20", "70", "1.2", "2200");
    private UserModel userModel2 = new UserModel(
            "2", "M", "180", "80", "19", "65", "1.3", "2500");
    private UserModel userModel3 = new UserModel(
            "3", "M", "170", "70", "18", "55", "1.4", "2800");
    private UserModel userModel4 = new UserModel(
            "4", "K", "190", "90", "20", "70", "1.2", "2200");
    private UserModel userModel5 = new UserModel(
            "5", "K", "180", "80", "19", "65", "1.3", "2500");
    private UserModel userModel6 = new UserModel(
            "6", "K", "170", "70", "18", "55", "1.4", "2800");

    @Test
    public void testHarrisBenedictCalculator() {
        harrisBenedictExpedted.add(2113);
        harrisBenedictExpedted.add(1933);
        harrisBenedictExpedted.add(1753);
        harrisBenedictExpedted.add(1767);
        harrisBenedictExpedted.add(1657);
        harrisBenedictExpedted.add(1548);
        harrisBenedictResult.add(new HarrisBenedictCalculator(userModel1).calculate());
        harrisBenedictResult.add(new HarrisBenedictCalculator(userModel2).calculate());
        harrisBenedictResult.add(new HarrisBenedictCalculator(userModel3).calculate());
        harrisBenedictResult.add(new HarrisBenedictCalculator(userModel4).calculate());
        harrisBenedictResult.add(new HarrisBenedictCalculator(userModel5).calculate());
        harrisBenedictResult.add(new HarrisBenedictCalculator(userModel6).calculate());
        Assert.assertEquals(harrisBenedictExpedted, harrisBenedictResult);
    }

    @Test
    public void testKatchMcArdleCalculator() {
        katchMcArdleExpedted.add(1882);
        katchMcArdleExpedted.add(1774);
        katchMcArdleExpedted.add(1558);
        katchMcArdleExpedted.add(1882);
        katchMcArdleExpedted.add(1774);
        katchMcArdleExpedted.add(1558);

        katchMcArdleResult.add(Integer.valueOf(new KatchMcArdleCalculator(userModel1).calculate()));
        katchMcArdleResult.add(Integer.valueOf(new KatchMcArdleCalculator(userModel2).calculate()));
        katchMcArdleResult.add(Integer.valueOf(new KatchMcArdleCalculator(userModel3).calculate()));
        katchMcArdleResult.add(Integer.valueOf(new KatchMcArdleCalculator(userModel4).calculate()));
        katchMcArdleResult.add(Integer.valueOf(new KatchMcArdleCalculator(userModel5).calculate()));
        katchMcArdleResult.add(Integer.valueOf(new KatchMcArdleCalculator(userModel6).calculate()));

        Assert.assertEquals(katchMcArdleExpedted, katchMcArdleResult);
        System.out.println("EXPECTED:" + katchMcArdleExpedted);
        System.out.println("RESULT:" + katchMcArdleResult + "\n");
    }

    @Test
    public void testMifflinStJeorCalculator() {
        mifflinStJeorExpedted.add(1993);
        mifflinStJeorExpedted.add(1835);
        mifflinStJeorExpedted.add(1678);
        mifflinStJeorExpedted.add(1827);
        mifflinStJeorExpedted.add(1669);
        mifflinStJeorExpedted.add(1512);

        mifflinStJeorResult.add(new MifflinStJeorCalculator(userModel1).calculate());
        mifflinStJeorResult.add(new MifflinStJeorCalculator(userModel2).calculate());
        mifflinStJeorResult.add(new MifflinStJeorCalculator(userModel3).calculate());
        mifflinStJeorResult.add(new MifflinStJeorCalculator(userModel4).calculate());
        mifflinStJeorResult.add(new MifflinStJeorCalculator(userModel5).calculate());
        mifflinStJeorResult.add(new MifflinStJeorCalculator(userModel6).calculate());

        Assert.assertEquals(mifflinStJeorExpedted, mifflinStJeorResult);
        System.out.println("EXPECTED:" + mifflinStJeorExpedted);
        System.out.println("RESULT:" + mifflinStJeorResult + "\n");
    }

}

