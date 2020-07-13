package pl.w_kowalczyk.mytraining.ui.login.presenter;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginPresenter {
    void onLoginClicked(String email, String password);
    void authenticateByGoogle(GoogleSignInAccount googleAccount);
}
