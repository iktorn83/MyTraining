package pl.w_kowalczyk.mytraining.ui.application.presenter;



import android.net.Uri;

public interface ApplicationPresenter {
    void onLogoutClicked();
    String onUserLogged();
    Boolean isAvatarUploaded();
    Uri getAvatarSrcFromFirebase();
    void changeAvatar();
    boolean uploadImage(Uri src);
}
