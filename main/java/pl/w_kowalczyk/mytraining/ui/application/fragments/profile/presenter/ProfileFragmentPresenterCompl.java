package pl.w_kowalczyk.mytraining.ui.application.fragments.profile.presenter;

        import com.google.firebase.auth.FirebaseAuth;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import pl.w_kowalczyk.mytraining.ui.application.fragments.profile.view.ProfileFragmentView;
        import pl.w_kowalczyk.mytraining.ui.application.model.UserModel;

public class ProfileFragmentPresenterCompl implements ProfileFragmentPresenter {
    private ProfileFragmentView view;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public ProfileFragmentPresenterCompl(ProfileFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onUpdateClicked(String height, String weight, String bmr, String gender, String age, String musclemass,String calories) {
        bmr = bmr.substring(0, 3);
        gender = gender.substring(0, 1);
        String userId = getUserId();

        if (height.isEmpty()) {
            view.showMessageInvalidHeight();
            return;
        }
        if (weight.isEmpty()) {
            view.showMessageInvalidWeight();
            return;
        }
        if (age.isEmpty()) {
            view.showMessageInvalidAge();
            return;
        }
        if (calories.isEmpty()) {
            view.showMessageInvalidCalories();
            return;
        }
        if (musclemass.isEmpty()) {
            musclemass = "0";
        }
        UserModel userModel = new UserModel(userId, gender, height, weight, age, musclemass, bmr, calories);
        saveToDatabase(userModel);

    }

    private String getUserId() {
        return firebaseAuth.getUid();
    }

    private void saveToDatabase(UserModel userModel) {
        database.child("notes")
                .child(getUserId())
                .setValue(userModel)
                .addOnSuccessListener(aVoid -> view.showMessage("Pomyślnie zaktualizowano dane"))
                .addOnFailureListener(e -> view.showMessage("Nie udało się zaktualizować danych"));
    }
}
