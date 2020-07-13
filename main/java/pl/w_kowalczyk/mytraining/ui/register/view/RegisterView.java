package pl.w_kowalczyk.mytraining.ui.register.view;

public interface RegisterView {

    void showMainScreen();

    void showMessageInvalidPassword();

    void showMessagePasswordNotEquals();

    void showMessageInvalidEmail();

    void showRegistrationError();
}
