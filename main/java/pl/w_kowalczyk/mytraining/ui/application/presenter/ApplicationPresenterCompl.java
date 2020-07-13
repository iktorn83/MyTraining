package pl.w_kowalczyk.mytraining.ui.application.presenter;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import pl.w_kowalczyk.mytraining.ui.application.view.ApplicationView;

public class ApplicationPresenterCompl implements ApplicationPresenter {
    private ApplicationView view;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public ApplicationPresenterCompl(ApplicationView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseUser = firebaseAuth.getCurrentUser();
        this.storage = FirebaseStorage.getInstance();
        this.storageReference = storage.getReference();
    }

    @Override
    public void onLogoutClicked() {
        firebaseAuth.signOut();
    }

    @Override
    public String onUserLogged() {
        String username = firebaseAuth.getCurrentUser().getDisplayName();
        String email = firebaseAuth.getCurrentUser().getEmail();
        if (username==null)
        {
            username="Nie ustawiono nazwy";
        }
        return username+"\n"+email;
    }

    @Override
    public Boolean isAvatarUploaded() {
        Uri avatarUrl = getAvatarSrcFromFirebase();
        return avatarUrl != null;
    }

    @Override
    public Uri getAvatarSrcFromFirebase() {
        return firebaseAuth.getCurrentUser().getPhotoUrl();
    }

    @Override
    public void changeAvatar() {
        storageReference.getDownloadUrl().addOnCompleteListener(task -> {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(task.getResult())
                    .build();
            firebaseUser.updateProfile(profileUpdates);
        });


    }

    @Override
    public boolean uploadImage(Uri src) {
        if (src != null) {

            this.storageReference = storageReference.child("images/" + firebaseUser.getUid());
            storageReference.putFile(src).addOnCompleteListener(task -> changeAvatar());
            return true;
        } else {
            return false;
        }
    }


}



