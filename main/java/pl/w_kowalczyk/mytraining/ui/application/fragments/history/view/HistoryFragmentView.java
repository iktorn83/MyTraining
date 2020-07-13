package pl.w_kowalczyk.mytraining.ui.application.fragments.history.view;

import java.util.List;

import pl.w_kowalczyk.mytraining.ui.application.model.HistoryModel;

public interface HistoryFragmentView {
    void setHistoryModels(List<HistoryModel> historyModels);

    void showMessage(String s);
}
