package pl.w_kowalczyk.mytraining.ui.application.fragments.calculator.view;

public interface CalculatorFragmentView {
    void showUserDownloadedOk(String text);
    void showHarrisBenedictMethod(String text);
    void showMifflinStJeorMethod(String text);
    void showKatchMcArdle(String text, int flag);
    void showCalories(String text);
    void showCpm(String text);
    void showSuggestion(String text);
    void showDifference(String text);
    void showMessage(String text);
}
