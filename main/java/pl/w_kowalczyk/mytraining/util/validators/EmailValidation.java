package pl.w_kowalczyk.mytraining.util.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private Matcher matcher;

    private Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public boolean validateEmail(String paramString) {
        this.matcher = this.pattern.matcher(paramString);
        return this.matcher.matches();
    }
}
