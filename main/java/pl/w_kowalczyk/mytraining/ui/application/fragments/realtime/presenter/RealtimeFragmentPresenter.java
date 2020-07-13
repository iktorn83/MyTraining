package pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.presenter;

import java.util.TreeMap;

public interface RealtimeFragmentPresenter {

    void onSendClicked(String activity,TreeMap heartrate, String time);
}
