package pl.w_kowalczyk.mytraining.ui.application.fragments.realtime;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.TreeMap;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.presenter.RealtimeFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.presenter.RealtimeFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.view.RealtimeFragmentView;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;
import pl.w_kowalczyk.mytraining.util.calculators.TimeConverter;
import pl.w_kowalczyk.mytraining.util.devices.Device;
import pl.w_kowalczyk.mytraining.util.devices.HeartrateGenerator;

public class RealtimeFragment extends BaseFragment implements RealtimeFragmentView {

    private RealtimeFragmentPresenter realtimeFragmentPresenter;
    Activity activity;

    private LineGraphSeries<DataPoint> series;

    private GraphView realtimeGraph;
    private Viewport viewport;
    private Spinner realtimeActivity;
    private Spinner realtimeDevice;
    private TextView realtimeTime;
    private Button realtimeStart;
    private Button realtimeStop;
    private Button realtimeSend;
    private Boolean interrupt = false;
    private TreeMap treeMap = new TreeMap();
    private Device device;
    private Boolean firstStart = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realtime, container, false);
        initPresenter();
        activity = getActivity();
        findViews(view);
        initGraph();
        setDefaultScale();
        setListeners();
        setupButtons();
        return view;
    }

    void setupButtons() {
        realtimeSend.setEnabled(false);
        realtimeStop.setEnabled(false);
    }

    @Override
    public void initPresenter() {
        this.realtimeFragmentPresenter = new RealtimeFragmentPresenterCompl(this);
    }


    @Override
    public void findViews(View view) {
        realtimeStart = view.findViewById(R.id.realtimeStart);
        realtimeStop = view.findViewById(R.id.realtimeStop);
        realtimeTime = view.findViewById(R.id.realtimeTime);
        realtimeActivity = view.findViewById(R.id.realtimeActivity);
        realtimeDevice = view.findViewById(R.id.realtimeDevice);
        realtimeGraph = view.findViewById(R.id.realtimeGraph);
        realtimeSend = view.findViewById(R.id.realtimeSend);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void setListeners() {
        realtimeStart.setOnClickListener(v ->
        { if (realtimeDevice.getSelectedItem().toString().equals("generator danych")) {
                if (firstStart) {
                    device = new HeartrateGenerator();
                    firstStart = false; }
                realtimeStart.setEnabled(false);
                realtimeStop.setEnabled(true);
                realtimeSend.setEnabled(false); }
            interrupt = false;
            new Thread(() -> {
                for (int i = 0; i < 3600; i++) {
                    if (interrupt) break;
                    activity.runOnUiThread(() -> addEntry(device));
                    try { Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Błąd w wątku interfejsu użytkownika"); } }
            }).start();
        });
        realtimeStop.setOnClickListener(v -> {
            interrupt = true;
            realtimeStart.setEnabled(true);
            realtimeStop.setEnabled(false);
            realtimeSend.setEnabled(true);
        });
        realtimeSend.setOnClickListener(v -> {
            realtimeFragmentPresenter.onSendClicked(
                    realtimeActivity.getSelectedItem().toString(),
                    treeMap,
                    realtimeTime.getText().toString());
        });
    }



    private void setDefaultScale(){
        realtimeGraph.getViewport().setMaxX(20);
        realtimeGraph.getViewport().setMaxY(130);
        realtimeGraph.getViewport().setMinX(0);
        realtimeGraph.getViewport().setMinY(50);
    }

    private void initGraph() {
        series = new LineGraphSeries<>();
        series.setDrawDataPoints(true);
        realtimeGraph.addSeries(series);
        viewport = realtimeGraph.getViewport();
        viewport.setScalable(true);
        viewport.setScrollable(true);
        viewport.setYAxisBoundsManual(true);
    }
    private void addEntry(Device device) {
        int key = device.getTime();
        int value = device.getHeartrate();
        updateTime(key);
        updateHashSet(key, value);
        series.appendData(new DataPoint(key, value), true, 3600);
        realtimeGraph.setPressed(true);
        realtimeGraph.invalidate();
        viewport.scrollToEnd();
    }
    private void updateHashSet(int x, int y) {
        treeMap.put(String.valueOf(x), String.valueOf(y));
    }
    private void updateTime(int time) {
        int hours = TimeConverter.getHour(time);
        int minutes = TimeConverter.getMinute(time);
        int seconds = TimeConverter.getSecond(time);
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        realtimeTime.setText(timeString);
    }




}