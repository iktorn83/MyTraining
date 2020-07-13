package pl.w_kowalczyk.mytraining.util.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {

    private Matcher matcher;

    private Pattern pattern = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})");

    public boolean validatePassword(String paramString) {
        this.matcher = this.pattern.matcher(paramString);
        return this.matcher.matches();
    }
}
