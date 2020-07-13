package pl.w_kowalczyk.mytraining.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pl.w_kowalczyk.mytraining.util.calculators.CpmCalculator;

public class CPMCalculatorValidationTest {
    private List<Integer> expectedFor2000Calories = new ArrayList<>();
    private List<Integer> resultFor2000Calories = new ArrayList<>();

    private List<Integer> expectedFor2500Calories = new ArrayList<>();
    private List<Integer> resultFor2500Calories = new ArrayList<>();

    private List<Integer> expectedFor3000Calories = new ArrayList<>();
    private List<Integer> resultFor3000Calories = new ArrayList<>();


    @Test
    public void testBMR2000() {
        expectedFor2000Calories.add(2640);
        expectedFor2000Calories.add(2860);
        expectedFor2000Calories.add(3080);
        expectedFor2000Calories.add(3300);
        expectedFor2000Calories.add(3520);
        expectedFor2000Calories.add(3740);
        expectedFor2000Calories.add(3960);
        expectedFor2000Calories.add(4180);

        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.2", 2000));
        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.3", 2000));
        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.4", 2000));
        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.5", 2000));
        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.6", 2000));
        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.7", 2000));
        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.8", 2000));
        resultFor2000Calories.add(CpmCalculator.calculateCpm("1.9", 2000));

        Assert.assertEquals(expectedFor2000Calories, resultFor2000Calories);
        System.out.println("EXPECTED:"+expectedFor2000Calories);
        System.out.println("RESULT:"+resultFor2000Calories+"\n");
    }

    @Test
    public void testBMR2500() {
        expectedFor2500Calories.add(3300);
        expectedFor2500Calories.add(3575);
        expectedFor2500Calories.add(3850);
        expectedFor2500Calories.add(4125);
        expectedFor2500Calories.add(4400);
        expectedFor2500Calories.add(4675);
        expectedFor2500Calories.add(4950);
        expectedFor2500Calories.add(5225);

        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.2", 2500));
        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.3", 2500));
        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.4", 2500));
        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.5", 2500));
        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.6", 2500));
        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.7", 2500));
        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.8", 2500));
        resultFor2500Calories.add(CpmCalculator.calculateCpm("1.9", 2500));

        Assert.assertEquals(expectedFor2500Calories, resultFor2500Calories);
        System.out.println("EXPECTED:"+expectedFor2500Calories);
        System.out.println("RESULT:"+resultFor2500Calories+"\n");
    }

    @Test
    public void testBMR3000() {
        expectedFor3000Calories.add(3960);
        expectedFor3000Calories.add(4290);
        expectedFor3000Calories.add(4620);
        expectedFor3000Calories.add(4950);
        expectedFor3000Calories.add(5280);
        expectedFor3000Calories.add(5610);
        expectedFor3000Calories.add(5940);
        expectedFor3000Calories.add(6270);

        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.2", 3000));
        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.3", 3000));
        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.4", 3000));
        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.5", 3000));
        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.6", 3000));
        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.7", 3000));
        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.8", 3000));
        resultFor3000Calories.add(CpmCalculator.calculateCpm("1.9", 3000));

        Assert.assertEquals(expectedFor3000Calories, resultFor3000Calories);
        System.out.println("EXPECTED:"+expectedFor3000Calories);
        System.out.println("RESULT:"+resultFor3000Calories+"\n");
    }

}

