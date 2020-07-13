package pl.w_kowalczyk.mytraining.ui.application.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.profile.presenter.ProfileFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.profile.presenter.ProfileFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.profile.view.ProfileFragmentView;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;

public class ProfileFragment extends BaseFragment implements ProfileFragmentView {
    private ProfileFragmentPresenter profileFragmentPresenter;

    private Button btn_profile_update;
    private EditText profile_height;
    private Spinner profile_bmr;
    private Spinner profile_gender;
    private EditText profile_weight;
    private EditText profile_age;
    private EditText profile_musclemass;
    private EditText profile_calories;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViews(view);
        initPresenter();
        setListeners();
        return view;
    }

    private void findViews(View view) {
        this.btn_profile_update = view.findViewById(R.id.btn_profile_update);
        this.profile_height = view.findViewById(R.id.profile_height);
        this.profile_bmr = view.findViewById(R.id.profile_bmr);
        this.profile_gender = view.findViewById(R.id.profile_gender);
        this.profile_weight = view.findViewById(R.id.profile_weight);
        this.profile_age = view.findViewById(R.id.profile_age);
        this.profile_musclemass = view.findViewById(R.id.profile_musclemass);
        this.profile_calories = view.findViewById(R.id.profile_calories);
    }

    private void initPresenter() {
        this.profileFragmentPresenter = new ProfileFragmentPresenterCompl(this);
    }

    private void setListeners() {
        this.btn_profile_update.setOnClickListener(param1View -> {

            String height, weight, bmr, gender, age, musclemass, calories;

            height = profile_height.getText().toString();
            weight = profile_weight.getText().toString();
            bmr = profile_bmr.getSelectedItem().toString();
            gender = profile_gender.getSelectedItem().toString();
            age = profile_age.getText().toString();
            musclemass = profile_musclemass.getText().toString();
            calories = profile_calories.getText().toString();
            profileFragmentPresenter.onUpdateClicked(height, weight, bmr, gender, age, musclemass,calories);
        });
    }

    @Override
    public void showMessageInvalidHeight() {
        Toast.makeText(getContext(), "Podaj wzrost poprawnie", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageInvalidWeight() {
        Toast.makeText(getContext(), "Podaj wagę poprawnie", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageInvalidAge() {
        Toast.makeText(getContext(), "Podaj wiek poprawnie", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showMessageInvalidCalories() {
        Toast.makeText(getContext(), "Podaj ilość kalorii poprawnie", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}