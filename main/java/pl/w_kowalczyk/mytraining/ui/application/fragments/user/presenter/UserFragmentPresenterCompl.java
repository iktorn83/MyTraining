package pl.w_kowalczyk.mytraining.ui.application.fragments.user.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


import pl.w_kowalczyk.mytraining.ui.application.fragments.user.view.UserFragmentView;


public class UserFragmentPresenterCompl implements UserFragmentPresenter {
    private UserFragmentView view;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public UserFragmentPresenterCompl(UserFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public String getUserName() {
        return firebaseUser.getDisplayName();
    }

    public String getUserEmail() {
        return firebaseUser.getEmail();
    }


    public void updateUserDisplayName(String name) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        firebaseUser.updateProfile(profileUpdates);

    }

    public void updateUserEmail(String email) {

        firebaseUser.updateEmail(email);
    }

    public void deleteUser() {

        firebaseUser.delete();
    }
}
