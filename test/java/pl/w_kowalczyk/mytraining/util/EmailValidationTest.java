package pl.w_kowalczyk.mytraining.util;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import pl.w_kowalczyk.mytraining.util.validators.EmailValidation;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EmailValidationTest {

    private String arg;
    private static EmailValidation emailFormatValidator;
    private Boolean expectedValidation;

    public EmailValidationTest(String str, Boolean expectedValidation) {
        this.arg = str;
        this.expectedValidation = expectedValidation;
    }

    @BeforeClass
    public static void initialize() {
        emailFormatValidator = new EmailValidation();
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { "testtestowy@gmail.com.2j",false },    // liczba w domenie
                { "testtestowy@test@test.com", false },         // dwa znaki @
                { "testtestowy!!!@test.com", false },          // znak specjalny !
                { "testtestowy@.com", false },                  // domena zaczyna sie od .
                { "testtestowy.com", false },             // brak znaku @
                { ".testtestowy.com@at.com", false },     // adres zaczyna się od .
                { "testtestowy..test@at.com", false }, // dwa znaki . obok siebie


                { "testtestowy@gmail.com",true },
                { "test+testowy@gmail.com", true },
                { "test.test-123@gmail-list.com", true },
                { "testtestowy123@test.com.gr", true } };

        return Arrays.asList(data);
    }

    @Test
    public void test() {
        Boolean res = emailFormatValidator.validateEmail(this.arg);
        String validv = (res) ? "valid" : "invalid";
        System.out.println("Email "+arg+ " is " + validv);
        assertEquals("Result", this.expectedValidation, res);

    }

}