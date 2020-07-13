package pl.w_kowalczyk.mytraining.ui.application.fragments.history.presenter;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public interface HistoryFragmentPresenter {

    void getActivities();
    LineGraphSeries<DataPoint> getSeries(int position);
}
