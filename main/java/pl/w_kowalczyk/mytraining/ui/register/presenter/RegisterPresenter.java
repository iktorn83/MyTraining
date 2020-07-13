package pl.w_kowalczyk.mytraining.ui.register.presenter;

public interface RegisterPresenter {

    void onRegisterClicked(
            String email,
            String password,
            String passwordRepeat);
}
