package pl.w_kowalczyk.mytraining.ui.application.fragments.analise.view;

public interface AnaliseFragmentView {
    void initSummary(String count, String first, String last, String average, String averageFive);
    void initGraph(int[] countSeries);

}
