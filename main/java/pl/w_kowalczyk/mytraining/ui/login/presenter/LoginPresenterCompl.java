package pl.w_kowalczyk.mytraining.ui.login.presenter;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import pl.w_kowalczyk.mytraining.ui.login.view.LoginView;


public class LoginPresenterCompl implements LoginPresenter {

    private LoginView view;
    private FirebaseAuth firebaseAuth;
    private AuthCredential authCredential;

    public LoginPresenterCompl(LoginView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }


    public void onLoginClicked(String email, String password) {


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {


                    if (task.isSuccessful()) {
                        Log.d("INFO: ", "isLoginInFireBase: complete");
                        view.openMainScreen();
                    } else {
                        Log.d("INFO: ", "isLoginInFireBase: false");
                        view.showLoginError();
                    }
                });
    }

    @Override
    public void authenticateByGoogle(GoogleSignInAccount googleAccount) {


        authCredential = GoogleAuthProvider.getCredential(googleAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(task -> {


                    if (task.isSuccessful()) {
                        Log.d("INFO: ", "GoogleLogin: complete");
                        view.openMainScreen();
                    } else {
                        Log.d("INFO: ", "GoogleLogin: failed");
                        view.showLoginError();
                    }
                });
    }
}
