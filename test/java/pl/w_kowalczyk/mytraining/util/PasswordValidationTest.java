package pl.w_kowalczyk.mytraining.util;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import pl.w_kowalczyk.mytraining.util.validators.PasswordValidation;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordValidationTest {

    private String arg;
    private static PasswordValidation passwordValidator;
    private Boolean expectedValidation;

    public PasswordValidationTest(String str, Boolean expectedValidation) {
        this.arg = str;
        this.expectedValidation = expectedValidation;
    }

    @BeforeClass
    public static void initialize() {
        passwordValidator = new PasswordValidation();
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {"n!k@s",false },                         // mniej niż 8 znaków
                { "gregorymarjames-law", false },         // brak cyfry i wielkiej litery
                { " abcdFg45*", false },                  // znak specjalny ~
                { "n!koabcD#AX", false },                 // brak cyfry
                { "ABCASWF2!", false   },                 // brak małej litery

                // valid passwords

                {"n!k@sn1Kos",true },
                { "T@estT0doG##dd", true },
                { "t!e1stabcD#!", true } };

        return Arrays.asList(data);
    }

    @Test
    public void test() {
        Boolean res = passwordValidator.validatePassword(this.arg);
        String validv = (res) ? "valid" : "invalid";
        System.out.println("Password '"+arg+ "' is " + validv);
        assertEquals("Result", this.expectedValidation, res);

    }

}