package pl.w_kowalczyk.mytraining.ui.application.fragments.profile.view;

public interface ProfileFragmentView {
    void showMessageInvalidAge();
    void showMessageInvalidWeight();
    void showMessageInvalidHeight();
    void showMessage(String message);
    void showMessageInvalidCalories();

}
