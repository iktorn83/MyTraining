package pl.w_kowalczyk.mytraining.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pl.w_kowalczyk.mytraining.util.calculators.CaloriesCalculator;

public class CaloriesCalculatorValidationTest {
    private List<Integer> expectedForNonFats = new ArrayList<>();
    private List<Integer> resultForNonFats = new ArrayList<>();

    private List<Integer> expectedForFats = new ArrayList<>();
    private List<Integer> resultForFats = new ArrayList<>();




    @Test
    public void testNonFatsCalculation() {
        expectedForNonFats.add(50);
        expectedForNonFats.add(75);
        expectedForNonFats.add(100);
        expectedForNonFats.add(125);
        expectedForNonFats.add(150);
        expectedForNonFats.add(250);

        resultForNonFats.add(CaloriesCalculator.kilocaloriesToGrams(200,false));
        resultForNonFats.add(CaloriesCalculator.kilocaloriesToGrams(300,false));
        resultForNonFats.add(CaloriesCalculator.kilocaloriesToGrams(400,false));
        resultForNonFats.add(CaloriesCalculator.kilocaloriesToGrams(500,false));
        resultForNonFats.add(CaloriesCalculator.kilocaloriesToGrams(600,false));
        resultForNonFats.add(CaloriesCalculator.kilocaloriesToGrams(1000,false));



        Assert.assertEquals(expectedForNonFats, resultForNonFats);
        System.out.println("EXPECTED:"+expectedForNonFats);
        System.out.println("RESULT:"+resultForNonFats+"\n");
    }


    @Test
    public void testFatsCalculation() {
        expectedForFats.add(22);
        expectedForFats.add(33);
        expectedForFats.add(44);
        expectedForFats.add(55);
        expectedForFats.add(66);
        expectedForFats.add(111);


        resultForFats.add(CaloriesCalculator.kilocaloriesToGrams(200,true));
        resultForFats.add(CaloriesCalculator.kilocaloriesToGrams(300,true));
        resultForFats.add(CaloriesCalculator.kilocaloriesToGrams(400,true));
        resultForFats.add(CaloriesCalculator.kilocaloriesToGrams(500,true));
        resultForFats.add(CaloriesCalculator.kilocaloriesToGrams(600,true));
        resultForFats.add(CaloriesCalculator.kilocaloriesToGrams(1000,true));

        Assert.assertEquals(expectedForFats, resultForFats);
        System.out.println("EXPECTED:"+expectedForFats);
        System.out.println("RESULT:"+resultForFats+"\n");
    }

}

