package pl.w_kowalczyk.mytraining.ui.application.fragments.user.presenter;

public interface UserFragmentPresenter {
    String getUserEmail();
    String getUserName();
    void updateUserEmail(String email);
    void updateUserDisplayName(String username);
    void deleteUser();

}
