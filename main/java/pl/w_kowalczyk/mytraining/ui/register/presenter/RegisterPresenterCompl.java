package pl.w_kowalczyk.mytraining.ui.register.presenter;

import com.google.firebase.auth.FirebaseAuth;

import pl.w_kowalczyk.mytraining.ui.register.view.RegisterView;
import pl.w_kowalczyk.mytraining.util.validators.EmailValidation;
import pl.w_kowalczyk.mytraining.util.validators.PasswordValidation;

public class RegisterPresenterCompl implements RegisterPresenter {

    private EmailValidation emailValidation = new EmailValidation();
    private PasswordValidation passwordValidation = new PasswordValidation();
    private FirebaseAuth firebaseAuth;

    private RegisterView view;

    public RegisterPresenterCompl(RegisterView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onRegisterClicked(String email, String password, String passwordRepeat) {
        boolean isEmailValid = emailValidation.validateEmail(email);
        if (!isEmailValid) {
            view.showMessageInvalidEmail();
            return;
        }

        boolean isPasswordValid = passwordValidation.validatePassword(password);
        if (!isPasswordValid) {
            view.showMessageInvalidPassword();
            return;
        }

        boolean arePasswordIdentical = arePasswordsIdentic(password, passwordRepeat);
        if (!arePasswordIdentical) {
            view.showMessagePasswordNotEquals();
            return;
        }

        registerUser(email, password);
    }

    private void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.showMainScreen();
                    } else {
                        view.showRegistrationError();
                    }
                })
                .addOnFailureListener(e -> view.showRegistrationError());
    }

    private boolean arePasswordsIdentic(String password, String passwordRepeat) {
        return password.equals(passwordRepeat);
    }
}
