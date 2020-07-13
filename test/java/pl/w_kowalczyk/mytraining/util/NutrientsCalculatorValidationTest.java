package pl.w_kowalczyk.mytraining.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pl.w_kowalczyk.mytraining.util.calculators.NutrientsCalculator;

public class NutrientsCalculatorValidationTest {
    private List<Integer> expectedListProteinBasic = new ArrayList<>();
    private List<Integer> resultListProteinBasic = new ArrayList<>();
    private List<Integer> expectedListCarbohydratesBasic = new ArrayList<>();
    private List<Integer> resultListCarbohydratesBasic = new ArrayList<>();
    private List<Integer> expectedListFatBasic = new ArrayList<>();
    private List<Integer> resultListFatBasic = new ArrayList<>();

    private List<Integer> expectedListProteinReduce = new ArrayList<>();
    private List<Integer> resultListProteinReduce = new ArrayList<>();
    private List<Integer> expectedListCarbohydratesReduce = new ArrayList<>();
    private List<Integer> resultListCarbohydratesReduce = new ArrayList<>();
    private List<Integer> expectedListFatReduce = new ArrayList<>();
    private List<Integer> resultListFatReduce = new ArrayList<>();

    private List<Integer> expectedListProteinGain = new ArrayList<>();
    private List<Integer> resultListProteinGain = new ArrayList<>();
    private List<Integer> expectedListCarbohydratesGain = new ArrayList<>();
    private List<Integer> resultListCarbohydratesGain = new ArrayList<>();
    private List<Integer> expectedListFatGain = new ArrayList<>();
    private List<Integer> resultListFatGain = new ArrayList<>();

    @Test
    public void testProteinBasic() {
        resultListProteinBasic.add(300);
        resultListProteinBasic.add(315);
        resultListProteinBasic.add(330);
        resultListProteinBasic.add(345);
        resultListProteinBasic.add(360);
        resultListProteinBasic.add(375);

        expectedListProteinBasic.add(NutrientsCalculator.calculateProtein(2000, 1));
        expectedListProteinBasic.add(NutrientsCalculator.calculateProtein(2100, 1));
        expectedListProteinBasic.add(NutrientsCalculator.calculateProtein(2200, 1));
        expectedListProteinBasic.add(NutrientsCalculator.calculateProtein(2300, 1));
        expectedListProteinBasic.add(NutrientsCalculator.calculateProtein(2400, 1));
        expectedListProteinBasic.add(NutrientsCalculator.calculateProtein(2500, 1));
        Assert.assertEquals(expectedListProteinBasic, resultListProteinBasic);
        System.out.println("EXPECTED:"+expectedListProteinBasic);
        System.out.println("RESULT:"+resultListProteinBasic+"\n");
    }

    @Test
    public void testCarbohydratesBasic() {
        resultListCarbohydratesBasic.add(1100);
        resultListCarbohydratesBasic.add(1155);
        resultListCarbohydratesBasic.add(1210);
        resultListCarbohydratesBasic.add(1265);
        resultListCarbohydratesBasic.add(1320);
        resultListCarbohydratesBasic.add(1375);

        expectedListCarbohydratesBasic.add(NutrientsCalculator.calculateCarbohydrates(2000, 1));
        expectedListCarbohydratesBasic.add(NutrientsCalculator.calculateCarbohydrates(2100, 1));
        expectedListCarbohydratesBasic.add(NutrientsCalculator.calculateCarbohydrates(2200, 1));
        expectedListCarbohydratesBasic.add(NutrientsCalculator.calculateCarbohydrates(2300, 1));
        expectedListCarbohydratesBasic.add(NutrientsCalculator.calculateCarbohydrates(2400, 1));
        expectedListCarbohydratesBasic.add(NutrientsCalculator.calculateCarbohydrates(2500, 1));
        Assert.assertEquals(expectedListCarbohydratesBasic, resultListCarbohydratesBasic);
        System.out.println("EXPECTED:"+expectedListCarbohydratesBasic);
        System.out.println("RESULT:"+resultListCarbohydratesBasic+"\n");
    }

    @Test
    public void testFatBasic() {
        resultListFatBasic.add(600);
        resultListFatBasic.add(630);
        resultListFatBasic.add(660);
        resultListFatBasic.add(690);
        resultListFatBasic.add(720);
        resultListFatBasic.add(750);

        expectedListFatBasic.add(NutrientsCalculator.calculateFat(2000, 1));
        expectedListFatBasic.add(NutrientsCalculator.calculateFat(2100, 1));
        expectedListFatBasic.add(NutrientsCalculator.calculateFat(2200, 1));
        expectedListFatBasic.add(NutrientsCalculator.calculateFat(2300, 1));
        expectedListFatBasic.add(NutrientsCalculator.calculateFat(2400, 1));
        expectedListFatBasic.add(NutrientsCalculator.calculateFat(2500, 1));
        Assert.assertEquals(expectedListFatBasic, resultListFatBasic);
        System.out.println("EXPECTED:"+expectedListFatBasic);
        System.out.println("RESULT:"+resultListFatBasic+"\n");
    }

    @Test
    public void testProteinReduce() {
        resultListProteinReduce.add(340);
        resultListProteinReduce.add(357);
        resultListProteinReduce.add(374);
        resultListProteinReduce.add(391);
        resultListProteinReduce.add(408);
        resultListProteinReduce.add(425);

        expectedListProteinReduce.add(NutrientsCalculator.calculateProtein(2000, 2));
        expectedListProteinReduce.add(NutrientsCalculator.calculateProtein(2100, 2));
        expectedListProteinReduce.add(NutrientsCalculator.calculateProtein(2200, 2));
        expectedListProteinReduce.add(NutrientsCalculator.calculateProtein(2300, 2));
        expectedListProteinReduce.add(NutrientsCalculator.calculateProtein(2400, 2));
        expectedListProteinReduce.add(NutrientsCalculator.calculateProtein(2500, 2));
        Assert.assertEquals(expectedListProteinReduce, resultListProteinReduce);
        System.out.println("EXPECTED:"+expectedListProteinReduce);
        System.out.println("RESULT:"+resultListProteinReduce+"\n");
    }

    @Test
    public void testCarbohydratesReduce() {
        resultListCarbohydratesReduce.add(1160);
        resultListCarbohydratesReduce.add(1218);
        resultListCarbohydratesReduce.add(1276);
        resultListCarbohydratesReduce.add(1334);
        resultListCarbohydratesReduce.add(1392);
        resultListCarbohydratesReduce.add(1450);

        expectedListCarbohydratesReduce.add(NutrientsCalculator.calculateCarbohydrates(2000, 2));
        expectedListCarbohydratesReduce.add(NutrientsCalculator.calculateCarbohydrates(2100, 2));
        expectedListCarbohydratesReduce.add(NutrientsCalculator.calculateCarbohydrates(2200, 2));
        expectedListCarbohydratesReduce.add(NutrientsCalculator.calculateCarbohydrates(2300, 2));
        expectedListCarbohydratesReduce.add(NutrientsCalculator.calculateCarbohydrates(2400, 2));
        expectedListCarbohydratesReduce.add(NutrientsCalculator.calculateCarbohydrates(2500, 2));
        Assert.assertEquals(expectedListCarbohydratesReduce, resultListCarbohydratesReduce);
        System.out.println("EXPECTED:"+expectedListCarbohydratesReduce);
        System.out.println("RESULT:"+resultListCarbohydratesReduce+"\n");
    }

    @Test
    public void testFatReduce() {
        resultListFatReduce.add(500);
        resultListFatReduce.add(525);
        resultListFatReduce.add(550);
        resultListFatReduce.add(575);
        resultListFatReduce.add(600);
        resultListFatReduce.add(625);

        expectedListFatReduce.add(NutrientsCalculator.calculateFat(2000, 2));
        expectedListFatReduce.add(NutrientsCalculator.calculateFat(2100, 2));
        expectedListFatReduce.add(NutrientsCalculator.calculateFat(2200, 2));
        expectedListFatReduce.add(NutrientsCalculator.calculateFat(2300, 2));
        expectedListFatReduce.add(NutrientsCalculator.calculateFat(2400, 2));
        expectedListFatReduce.add(NutrientsCalculator.calculateFat(2500, 2));
        Assert.assertEquals(expectedListFatReduce, resultListFatReduce);
        System.out.println("EXPECTED:"+expectedListFatReduce);
        System.out.println("RESULT:"+resultListFatReduce+"\n");
    }

    @Test
    public void testProteinGain() {
        resultListProteinGain.add(500);
        resultListProteinGain.add(525);
        resultListProteinGain.add(550);
        resultListProteinGain.add(575);
        resultListProteinGain.add(600);
        resultListProteinGain.add(625);

        expectedListProteinGain.add(NutrientsCalculator.calculateProtein(2000, 3));
        expectedListProteinGain.add(NutrientsCalculator.calculateProtein(2100, 3));
        expectedListProteinGain.add(NutrientsCalculator.calculateProtein(2200, 3));
        expectedListProteinGain.add(NutrientsCalculator.calculateProtein(2300, 3));
        expectedListProteinGain.add(NutrientsCalculator.calculateProtein(2400, 3));
        expectedListProteinGain.add(NutrientsCalculator.calculateProtein(2500, 3));
        Assert.assertEquals(expectedListProteinGain, resultListProteinGain);
        System.out.println("EXPECTED:"+expectedListProteinGain);
        System.out.println("RESULT:"+resultListProteinGain+"\n");
    }

    @Test
    public void testCarbohydratesGain() {
        resultListCarbohydratesGain.add(1000);
        resultListCarbohydratesGain.add(1050);
        resultListCarbohydratesGain.add(1100);
        resultListCarbohydratesGain.add(1150);
        resultListCarbohydratesGain.add(1200);
        resultListCarbohydratesGain.add(1250);

        expectedListCarbohydratesGain.add(NutrientsCalculator.calculateCarbohydrates(2000, 3));
        expectedListCarbohydratesGain.add(NutrientsCalculator.calculateCarbohydrates(2100, 3));
        expectedListCarbohydratesGain.add(NutrientsCalculator.calculateCarbohydrates(2200, 3));
        expectedListCarbohydratesGain.add(NutrientsCalculator.calculateCarbohydrates(2300, 3));
        expectedListCarbohydratesGain.add(NutrientsCalculator.calculateCarbohydrates(2400, 3));
        expectedListCarbohydratesGain.add(NutrientsCalculator.calculateCarbohydrates(2500, 3));
        Assert.assertEquals(expectedListCarbohydratesGain, resultListCarbohydratesGain);
        System.out.println("EXPECTED:"+expectedListCarbohydratesGain);
        System.out.println("RESULT:"+resultListCarbohydratesGain+"\n");
    }

    @Test
    public void testFatGain() {
        resultListFatGain.add(300);
        resultListFatGain.add(315);
        resultListFatGain.add(330);
        resultListFatGain.add(345);
        resultListFatGain.add(360);
        resultListFatGain.add(375);

        expectedListFatGain.add(NutrientsCalculator.calculateFat(2000, 3));
        expectedListFatGain.add(NutrientsCalculator.calculateFat(2100, 3));
        expectedListFatGain.add(NutrientsCalculator.calculateFat(2200, 3));
        expectedListFatGain.add(NutrientsCalculator.calculateFat(2300, 3));
        expectedListFatGain.add(NutrientsCalculator.calculateFat(2400, 3));
        expectedListFatGain.add(NutrientsCalculator.calculateFat(2500, 3));
        Assert.assertEquals(expectedListFatGain, resultListFatGain);
        System.out.println("EXPECTED:"+expectedListFatGain);
        System.out.println("RESULT:"+resultListFatGain+"\n");
    }
}

