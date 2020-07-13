package pl.w_kowalczyk.mytraining.ui.application.fragments.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.activities.presenter.ActivitiesFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.activities.presenter.ActivitiesFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.activities.view.ActivitiesFragmentView;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;

public class ActivitiesFragment extends BaseFragment implements ActivitiesFragmentView, DatePickerDialog.OnDateSetListener {

    private ActivitiesFragmentPresenter activitiesFragmentPresenter;
    Button button_date;
    Button button_send;
    DatePickerDialog datePickerDialog;
    TextView history;
    EditText heartrate;
    EditText time;
    Spinner activitySpinner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        initPresenter();


        initDialog();
        findViews(view);

        setListeners();
        return view;
    }

    @Override
    public void initPresenter() {
        this.activitiesFragmentPresenter = new ActivitiesFragmentPresenterCompl(this);

    }

    public void initDialog() {
        datePickerDialog = new DatePickerDialog(
                getContext(),
                ActivitiesFragment.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void findViews(View view) {

        button_date = view.findViewById(R.id.button_date);
        button_send = view.findViewById(R.id.button_send);
        time = view.findViewById(R.id.time);
        heartrate = view.findViewById(R.id.heartrate);
        activitySpinner = view.findViewById(R.id.activitySpinner);
        history = view.findViewById(R.id.history);


    }


    public void setListeners() {
        button_date.setOnClickListener(v -> {
            datePickerDialog.show();
        });

        button_send.setOnClickListener(v -> {
            String activity = activitySpinner.getSelectedItem().toString();
            String date = button_date.getText().toString();
            String hr = heartrate.getText().toString();
            String duration = time.getText().toString();
            activitiesFragmentPresenter.onSaveClicked(activity, date, hr, duration);

        });
    }

    @Override
    public void updateHistory(String historyActivity) {
        history.setText(history.getText() + "\n" + historyActivity);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        button_date.setText(year + "." + (month + 1) + "." + dayOfMonth);

    }


    public void showMessageInvalidDate() {
        Toast.makeText(getContext(), "Niepoprawna data", Toast.LENGTH_SHORT).show();
    }
    public void showMessageInvalidHeartrate() {
        Toast.makeText(getContext(), "Brak tętna", Toast.LENGTH_SHORT).show();
    }
    public void showMessageInvalidTime() {
        Toast.makeText(getContext(), "Brak czasu trwania aktywności", Toast.LENGTH_SHORT).show();
    }


}