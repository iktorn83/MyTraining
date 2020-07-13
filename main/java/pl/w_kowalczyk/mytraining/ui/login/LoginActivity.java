package pl.w_kowalczyk.mytraining.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.MainActivity;
import pl.w_kowalczyk.mytraining.ui.base.BaseActivity;
import pl.w_kowalczyk.mytraining.ui.login.presenter.LoginPresenter;
import pl.w_kowalczyk.mytraining.ui.login.presenter.LoginPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.login.view.LoginView;
import pl.w_kowalczyk.mytraining.ui.register.RegisterActivity;

import static pl.w_kowalczyk.mytraining.R.string.default_web_client_id;

public class LoginActivity extends BaseActivity implements LoginView {
    private static final int GOOGLE_LOGIN = 12;

    private LoginPresenter loginPresenter;

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount googleSignInAccount;

    private Button buttonClear;
    private Button buttonLogin;
    private Button googleSignInButton;
    private TextView buttonRegister;
    private EditText editPass;
    private EditText editUser;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setListeners();
        initPresenter();
        setPopUps();
        initGoogleServices();
    }

    private void findViews() {
        this.editUser = findViewById(R.id.et_login_username);
        this.editPass = findViewById(R.id.et_login_password);
        this.buttonLogin = findViewById(R.id.btn_profile_update);
        this.buttonClear = findViewById(R.id.btn_login_clear);
        this.buttonRegister = findViewById(R.id.btn_login_register);
        this.googleSignInButton = findViewById(R.id.sign_in_google_button);
    }

    private void goToRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void initPresenter() {
        this.loginPresenter = new LoginPresenterCompl(this);
    }

    private void setListeners() {
        this.buttonLogin.setOnClickListener(param1View -> {

            String username = editUser.getText().toString();
            String password = editPass.getText().toString();

            if (username.length() != 0 && password.length() != 0) {

                loginPresenter.onLoginClicked(
                        editUser.getText().toString(),
                        editPass.getText().toString());
            } else {
                showMessage("Wpisz wymagane dane!", Toast.LENGTH_SHORT);
            }
        });

        buttonClear.setOnClickListener(param1View -> clearInputs());
        googleSignInButton.setOnClickListener(v -> showGoogleIntent());
        buttonRegister.setOnClickListener(param1View -> goToRegisterActivity());
    }

    private void setPopUps() {
        if (editUser.getText().toString().length() == 0) {
            editUser.setError("Wpisz adres e-mail", null);
        }

        if (editPass.getText().toString().length() < 8) {
            editPass.setError("Wpisz haslo", null);
        }
    }

    private void initGoogleServices() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    @Override
    public void clearInputs() {
        this.editUser.setText("");
        this.editPass.setText("");
    }



    @Override
    public void showLoginError() {
        showMessage("Wystąpił błąd logowania", Toast.LENGTH_LONG);
    }

    @Override
    public void openMainScreen() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showGoogleIntent() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_LOGIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                googleSignInAccount = task.getResult(ApiException.class);
                loginPresenter.authenticateByGoogle(googleSignInAccount);
            } catch (Exception e) {
                showLoginError();
            }
        }
    }


}


