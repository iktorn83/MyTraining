package pl.w_kowalczyk.mytraining.ui.application.fragments.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.calculator.presenter.CalculatorFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.calculator.presenter.CalculatorFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.calculator.view.CalculatorFragmentView;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;

public class CalculatorFragment extends BaseFragment implements CalculatorFragmentView {
    private CalculatorFragmentPresenter calculatorFragmentPresenter;

    private TextView firstMethod;
    private TextView secondMethod;
    private TextView thirdMethod;
    private TextView calories;
    private TextView cpm;
    private TextView difference;
    private TextView suggestion;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        findViews(view);
        initPresenter();
        setListeners();
        calculatorFragmentPresenter.calculateClicked();
        return view;
    }

    private void findViews(View view) {
        this.firstMethod = view.findViewById(R.id.firstMethod);
        this.secondMethod = view.findViewById(R.id.secondMethod);
        this.thirdMethod = view.findViewById(R.id.thirdMethod);
        this.calories = view.findViewById(R.id.calories);
        this.suggestion = view.findViewById(R.id.suggestion);
        this.difference = view.findViewById(R.id.difference);
        this.cpm = view.findViewById(R.id.cpm);
    }

    private void initPresenter() {
        this.calculatorFragmentPresenter = new CalculatorFragmentPresenterCompl(this);
    }

    private void setListeners() {

    }

    public void showUserDownloadedOk(String text) {
        Toast.makeText(this.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHarrisBenedictMethod(String text) {
        firstMethod.setText(text + "kcal");
    }

    @Override
    public void showMifflinStJeorMethod(String text) {
        secondMethod.setText(text + "kcal");
    }

    @Override
    public void showKatchMcArdle(String text, int flag) {
        if (flag == -1) {
            thirdMethod.setText(text);
        } else {
            thirdMethod.setText(text + "kcal");
        }
    }

    @Override
    public void showCalories(String text) {
        calories.setText(text);
    }

    @Override
    public void showCpm(String text) {
        cpm.setText(text);
    }

    @Override
    public void showDifference(String text) {
        difference.setText(text);
    }

    @Override
    public void showSuggestion(String text) {
        suggestion.setText(text);
    }

    @Override
    public void showMessage(String text) {
        if (getContext() != null) {
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        }

    }
}
