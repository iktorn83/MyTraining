package pl.w_kowalczyk.mytraining.ui.application.fragments.user;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.user.presenter.UserFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.user.presenter.UserFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.user.view.UserFragmentView;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;
import pl.w_kowalczyk.mytraining.ui.login.LoginActivity;

public class UserFragment extends BaseFragment implements UserFragmentView {
    private UserFragmentPresenter userFragmentPresenter;
    private TextView user_name;
    private TextView user_email;
    private Button btn_username;
    private Button btn_email;
    private EditText user_new_name;
    private EditText user_new_email;
    private CheckBox user_delete_checkbox;
    private Button btn_delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        findViews(view);
        initPresenter();
        setListeners();
        showUserData();
        return view;
    }

    private void findViews(View view) {
        user_name = view.findViewById(R.id.user_name);
        user_email = view.findViewById(R.id.user_email);
        btn_username = view.findViewById(R.id.btn_username);
        btn_email = view.findViewById(R.id.btn_email);
        user_new_name = view.findViewById(R.id.user_new_name);
        user_new_email = view.findViewById(R.id.user_new_email);
        user_delete_checkbox = view.findViewById(R.id.user_delete_checkbox);
        btn_delete = view.findViewById(R.id.btn_delete);
    }

    private void initPresenter() {
        this.userFragmentPresenter = new UserFragmentPresenterCompl(this);
    }

    private void setListeners() {
        btn_username.setOnClickListener(v -> {
            userFragmentPresenter.updateUserDisplayName(user_new_name.getText().toString());
            user_name.setText(user_new_name.getText().toString());
        });

        btn_email.setOnClickListener(v -> {
            userFragmentPresenter.updateUserEmail(user_new_email.getText().toString());
            user_email.setText(user_new_email.getText().toString());
        });

        btn_delete.setOnClickListener(v -> {
            if (user_delete_checkbox.isChecked()
            ) {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Usuwanie konta")
                        .setMessage("Na pewno chcesz usunąć konto?")
                        .setPositiveButton("Tak", (dialog, which) ->
                        {
                            userFragmentPresenter.deleteUser();
                            openLoginScreen();
                        })
                        .setNegativeButton("Nie", null)
                        .show();


            }
        });

    }

    private void openLoginScreen(){
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
    public void showUserData() {
        user_name.setText(userFragmentPresenter.getUserName());
        user_email.setText(userFragmentPresenter.getUserEmail());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}