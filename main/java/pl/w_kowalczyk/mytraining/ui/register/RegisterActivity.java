package pl.w_kowalczyk.mytraining.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.MainActivity;
import pl.w_kowalczyk.mytraining.ui.base.BaseActivity;
import pl.w_kowalczyk.mytraining.ui.register.presenter.RegisterPresenter;
import pl.w_kowalczyk.mytraining.ui.register.presenter.RegisterPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.register.view.RegisterView;

public class RegisterActivity extends BaseActivity implements RegisterView {
    private RegisterPresenter registerPresenter;

    private TextView textViewBack;
    private Button btnClear;
    private Button btnRegister;
    private EditText editEmail;
    private EditText editPass;
    private EditText editPassRepeat;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        showMessage("Uzupelnij dane do rejestracji", Toast.LENGTH_SHORT);
        findViews();
        setListeners();
        initPresenter();
    }

    private void findViews() {
        this.editPass = findViewById(R.id.et_register_password);
        this.editPassRepeat = findViewById(R.id.et_register_password_repeat);
        this.editEmail = findViewById(R.id.et_register_email);
        this.btnRegister = findViewById(R.id.btn_register_register);
        this.textViewBack = findViewById(R.id.textView_register_back);
        this.btnClear = findViewById(R.id.btn_register_clear);
    }

    private void initPresenter() {
        this.registerPresenter = new RegisterPresenterCompl(this);
    }

    private void setListeners() {
        this.btnRegister.setOnClickListener(param1View -> {
            String email = editEmail.getText().toString();
            String password = editPass.getText().toString();
            String passwordRepeat = editPassRepeat.getText().toString();

            registerPresenter.onRegisterClicked(email, password, passwordRepeat);
        });

        this.btnClear.setOnClickListener(param1View -> clearInputs());

        this.textViewBack.setOnClickListener(param1View -> onBackPressed());
    }

    private void clearInputs() {
        this.editPass.setText("");
        this.editPassRepeat.setText("");
        this.editEmail.setText("");
    }

    @Override
    public void showMainScreen() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showMessageInvalidPassword() {
        showMessage("Niepoprawne hasło. Wymagane 8 znaków, duża i mała litera, cyfra, znak sppecjalny", Toast.LENGTH_SHORT);
    }

    @Override
    public void showMessagePasswordNotEquals() {
        showMessage("Hasła się różnią", Toast.LENGTH_SHORT);
    }

    @Override
    public void showMessageInvalidEmail() {
        showMessage("Niepoprawny adres mailowy", Toast.LENGTH_SHORT);
    }

    @Override
    public void showRegistrationError() {
        showMessage("Błąd rejestracji. Spróbuj później", Toast.LENGTH_SHORT);
    }
}
