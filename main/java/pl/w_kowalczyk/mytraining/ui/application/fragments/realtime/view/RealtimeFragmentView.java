package pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.view;

import android.view.View;

public interface RealtimeFragmentView {

    void initPresenter();
    void findViews(View view);
    void showMessage(String message);

}
