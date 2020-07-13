package pl.w_kowalczyk.mytraining.ui.application.fragments.activities.view;

import android.view.View;

public interface ActivitiesFragmentView {

    void initPresenter();
    void findViews(View view);
    void showMessageInvalidTime();
    void showMessageInvalidDate();
    void showMessageInvalidHeartrate();
    void showMessage(String message);
    void updateHistory(String historyActivity);

}
