package pl.w_kowalczyk.mytraining.ui.application.fragments.main.view;

import android.view.View;

public interface MainFragmentView {

    void initPresenter();
    void findViews(View view);
    void setBasicCpm(int protein, int carbohydrates,int fat, int count, int proteinGrams, int carbohydratesGrams, int fatGrams);
    void setReduceCpm(int protein, int carbohydrates,int fat, int count, int proteinGrams, int carbohydratesGrams, int fatGrams);
    void setGainCpm(int protein, int carbohydrates,int fat, int count, int proteinGrams, int carbohydratesGrams, int fatGrams);
    void showMessage(String text);
}
