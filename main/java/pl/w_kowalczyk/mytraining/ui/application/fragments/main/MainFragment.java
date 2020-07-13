package pl.w_kowalczyk.mytraining.ui.application.fragments.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.main.presenter.MainFragmentPresenter;
import pl.w_kowalczyk.mytraining.ui.application.fragments.main.presenter.MainFragmentPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.fragments.main.view.MainFragmentView;
import pl.w_kowalczyk.mytraining.ui.base.BaseFragment;

public class MainFragment extends BaseFragment implements MainFragmentView {

    private MainFragmentPresenter mainFragmentPresenter;


    PieChartView pieChartViewBasic;
    PieChartView pieChartViewReduce;
    PieChartView pieChartViewGain;

    TextView basicCPM, proteinsBasic, carbohydratesBasic, fatsBasic;
    TextView reduceCPM, proteinsReduce, carbohydratesReduce, fatsReduce;
    TextView gainCPM, proteinsGain, carbohydratesGain, fatsGain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initPresenter();

        findViews(view);
        updateData();

        return view;
    }

    @Override
    public void initPresenter() {
        this.mainFragmentPresenter = new MainFragmentPresenterCompl(this);
    }


    @Override
    public void findViews(View view) {

        pieChartViewBasic = view.findViewById(R.id.pieChartViewBasic);
        basicCPM = view.findViewById(R.id.basicCPM);
        proteinsBasic = view.findViewById(R.id.proteinsBasic);
        carbohydratesBasic = view.findViewById(R.id.carbohydratesBasic);
        fatsBasic = view.findViewById(R.id.fatsBasic);

        pieChartViewReduce = view.findViewById(R.id.pieChartViewReduce);
        reduceCPM = view.findViewById(R.id.reduceCPM);
        proteinsReduce = view.findViewById(R.id.proteinsReduce);
        carbohydratesReduce = view.findViewById(R.id.carbohydratesReduce);
        fatsReduce = view.findViewById(R.id.fatsReduce);

        pieChartViewGain = view.findViewById(R.id.pieChartViewGain);
        gainCPM = view.findViewById(R.id.gainCPM);
        proteinsGain = view.findViewById(R.id.proteinsGain);
        carbohydratesGain = view.findViewById(R.id.carbohydratesGain);
        fatsGain = view.findViewById(R.id.fatsGain);
    }

    private void initPieChartBasic(int protein, int carbohydrates, int fat, int proteinGrams,
                                   int carbohydratesGrams, int fatGrams) {
        List pieData = new ArrayList<>();
        proteinsBasic.setText(protein + "kcal");
        carbohydratesBasic.setText(carbohydrates + "kcal");
        fatsBasic.setText(fat + "kcal");
        pieData.add(new SliceValue(protein, Color.rgb(244, 81, 30))
                .setLabel("Białka " + proteinGrams + "g"));
        pieData.add(new SliceValue(carbohydrates, Color.rgb(124, 179, 66))
                .setLabel("Węglowodany " + carbohydratesGrams + "g"));
        pieData.add(new SliceValue(fat, Color.rgb(251, 192, 45))
                .setLabel("Tłuszcze " + fatGrams + "g"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setValueLabelBackgroundAuto(true);
        pieChartViewBasic.animate().rotation(360).setDuration(1000);
        pieChartViewBasic.setPieChartData(pieChartData);
    }

    private void initPieChartReduce(int protein, int carbohydrates, int fat, int proteinGrams, int carbohydratesGrams, int fatGrams) {
        List pieData = new ArrayList<>();
        proteinsReduce.setText(protein + "kcal");
        carbohydratesReduce.setText(carbohydrates + "kcal");
        fatsReduce.setText(fat + "kcal");

        pieData.add(new SliceValue(protein, Color.rgb(183, 28, 28)).setLabel("Białka " + proteinGrams + "g"));
        pieData.add(new SliceValue(carbohydrates, Color.rgb(51, 105, 30)).setLabel("Węglowodany " + carbohydratesGrams + "g"));
        pieData.add(new SliceValue(fat, Color.rgb(255, 111, 0)).setLabel("Tłuszcze " + fatGrams + "g"));


        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setValueLabelBackgroundAuto(true);
        pieChartViewReduce.animate().rotation(360).setDuration(1000);
        pieChartViewReduce.setPieChartData(pieChartData);

    }

    private void initPieChartGain(int protein, int carbohydrates, int fat, int proteinGrams, int carbohydratesGrams, int fatGrams) {
        List pieData = new ArrayList<>();
        proteinsGain.setText(protein + "kcal");
        carbohydratesGain.setText(carbohydrates + "kcal");
        fatsGain.setText(fat + "kcal");
        pieData.add(new SliceValue(protein, Color.rgb(255, 112, 67)).setLabel("Białka " + proteinGrams + "g"));
        pieData.add(new SliceValue(carbohydrates, Color.rgb(156, 204, 101)).setLabel("Węglowodany " + carbohydratesGrams + "g"));
        pieData.add(new SliceValue(fat, Color.rgb(255, 238, 88)).setLabel("Tłuszcze " + fatGrams + "g"));


        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setValueLabelBackgroundAuto(true);
        pieChartViewGain.animate().rotation(360).setDuration(1000);
        pieChartViewGain.setPieChartData(pieChartData);

    }


    public void setBasicCpm(int protein, int carbohydrates, int fat, int count, int proteinGrams,
                            int carbohydratesGrams, int fatGrams) {
        basicCPM.setText(String.valueOf(count) + "kcal");
        initPieChartBasic(protein, carbohydrates, fat, proteinGrams, carbohydratesGrams, fatGrams);
    }
    public void setReduceCpm(int protein, int carbohydrates, int fat, int count, int proteinGrams,
                             int carbohydratesGrams, int fatGrams) {
        reduceCPM.setText(String.valueOf(count) + "kcal");
        initPieChartReduce(protein, carbohydrates, fat, proteinGrams, carbohydratesGrams, fatGrams);
    }
    public void setGainCpm(int protein, int carbohydrates, int fat, int count, int proteinGrams,
                           int carbohydratesGrams, int fatGrams) {
        gainCPM.setText(String.valueOf(count) + "kcal");
        initPieChartGain(protein, carbohydrates, fat, proteinGrams, carbohydratesGrams, fatGrams);
    }

    void updateData() {
        mainFragmentPresenter.downloadCpmData();
    }

    public void showMessage(String text) {
        if (this.getContext() != null) {
            Toast.makeText(this.getContext(), text, Toast.LENGTH_LONG).show();
        }

    }
}