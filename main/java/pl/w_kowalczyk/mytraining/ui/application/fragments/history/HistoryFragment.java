package pl.w_kowalczyk.mytraining.ui.application.fragments.history;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.history.presenter.HistoryFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.history.presenter.HistoryFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.history.view.HistoryFragmentView;
import pl.w_kowalczyk.mytraining.ui.application.model.HistoryModel;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;
import pl.w_kowalczyk.mytraining.util.other.ListAdapter;

public class HistoryFragment extends BaseFragment implements HistoryFragmentView {
    private HistoryFragmentPresenter historyFragmentPresenter;
    private RecyclerView recyclerView;
    private View popupHeartrate;
    private GraphView graph;
    private Viewport viewport;
    private LineGraphSeries<DataPoint> series;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        findViews(view);
        initPresenter();

        return view;
    }

    private void findViews(View view) {

        recyclerView = view.findViewById(R.id.history_recycler);


    }

    private void initPresenter() {
        this.historyFragmentPresenter = new HistoryFragmentPresenterCompl(this);
    }


    @Override
    public void setHistoryModels(List<HistoryModel> historyModels) {
        setListAdapter(historyModels);
    }


    private void setListAdapter(List<HistoryModel> historyModels) {
        ListAdapter listAdapter = new ListAdapter(historyModels, (historyModel, position)
                -> { showHeartrateGraph(position); });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    private void showHeartrateGraph(int position) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        popupHeartrate = inflater.inflate(R.layout.popup_hearthrate, null);
        graph = popupHeartrate.findViewById(R.id.graph_exercise_statistic);
        series = historyFragmentPresenter.getSeries(position);
        viewport = graph.getViewport();
        viewport.setScalable(true);
        viewport.setScrollable(true);
        viewport.setYAxisBoundsManual(true);
        viewport.scrollToEnd();
        graph.addSeries(series);
        setDefaultScale();
        new AlertDialog.Builder(getContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Wykres tętna")
                .setNegativeButton("Wyjdź", null)
                .setView(popupHeartrate)
                .show();
    }
    private void setDefaultScale(){
        graph.getViewport().setMaxX(20);
        graph.getViewport().setMaxY(130);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMinY(50);
    }
}