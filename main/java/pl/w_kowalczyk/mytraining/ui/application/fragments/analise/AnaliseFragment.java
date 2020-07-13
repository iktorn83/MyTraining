package pl.w_kowalczyk.mytraining.ui.application.fragments.analise;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Calendar;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.analise.presenter.AnaliseFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.analise.presenter.AnaliseFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.analise.view.AnaliseFragmentView;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;

public class AnaliseFragment extends BaseFragment implements AnaliseFragmentView {
    private AnaliseFragmentPresenter analiseFragmentPresenter;
    private LinearLayout hiddenLayout;

    private GraphView graphCount;
    private TextView averageHeartrateAll;
    private TextView averageHeartrateLastFive;
    private TextView lastActivity;
    private TextView firstActivity;
    private TextView activityCount;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analise, container, false);
        findViews(view);
        initPresenter();



        initData();
        return view;
    }

    private void findViews(View view) {
        graphCount = view.findViewById(R.id.graph);
        averageHeartrateAll = view.findViewById(R.id.averageHeartrateAll);
        averageHeartrateLastFive = view.findViewById(R.id.averageHeartrateLastFive);
        lastActivity = view.findViewById(R.id.lastActivity);
        firstActivity = view.findViewById(R.id.firstActivity);
        activityCount = view.findViewById(R.id.activityCount);

        hiddenLayout = view.findViewById(R.id.hiddenLayout);
    }

    private void initPresenter() {
        this.analiseFragmentPresenter = new AnaliseFragmentPresenterCompl(this);
    }

    public void initGraph(int[] countSeries) {
        String[] days = new String[5];
        Calendar calendar = Calendar.getInstance();

        int month = (calendar.get(Calendar.MONTH) + 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < 5; i++) {
            days[i] =  month + "." + day--;
        }
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(1, countSeries[0]),
                new DataPoint(2, countSeries[1]),
                new DataPoint(3, countSeries[2]),
                new DataPoint(4, countSeries[3]),
                new DataPoint(5, countSeries[4])
        });

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        graphCount.addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphCount);
        staticLabelsFormatter.setHorizontalLabels(new String[]{days[0], days[1], days[2], days[3], days[4]});
        graphCount.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);




        graphCount.getGridLabelRenderer().setHorizontalAxisTitle("Data aktywności");
        graphCount.getGridLabelRenderer().setVerticalAxisTitle("Ilość aktywności");

        graphCount.getViewport().setXAxisBoundsManual(true);

        graphCount.getViewport().setMinY(0);
        graphCount.getViewport().setMaxX(5);
        graphCount.getGridLabelRenderer().setLabelsSpace(5);

    }


    private void initData(){
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            analiseFragmentPresenter.onSummaryClicked();
            analiseFragmentPresenter.updateGraph();
        }, 500);
    }
    public void initSummary(String count, String first, String last, String average, String averageFive) {
        hiddenLayout.setVisibility(View.VISIBLE);

        activityCount.setText(count);
        firstActivity.setText(first);
        lastActivity.setText(last);
        averageHeartrateAll.setText(average);
        averageHeartrateLastFive.setText(averageFive);


    }


}
