package pl.w_kowalczyk.mytraining.ui.application.fragments.activities.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.w_kowalczyk.mytraining.ui.application.fragments.activities.view.ActivitiesFragmentView;
import pl.w_kowalczyk.mytraining.ui.application.model.ActivityModel;

public class ActivitiesFragmentPresenterCompl implements ActivitiesFragmentPresenter {
    ActivitiesFragmentView view;
    FirebaseAuth firebaseAuth;
    private String userID;
    int activitiesCount = 0;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("/activity/");

    public ActivitiesFragmentPresenterCompl(ActivitiesFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userID = getCurrentUserID();
        initActivitiesCounter();
    }


    private String getCurrentUserID() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    @Override
    public void onSaveClicked(String activity, String date, String heartrate, String time) {
        getActivitiesCount();
        if (date.equals("YYYY.MM.DD")) {
            view.showMessageInvalidDate();
            return; }
        if (heartrate.isEmpty()) {
            view.showMessageInvalidHeartrate();
            return; }
        if (time.isEmpty()) {
            view.showMessageInvalidTime();
            return; }
        ActivityModel activityModel = new ActivityModel(activity, date, heartrate, time);
        saveToDatabase(activityModel, userID, String.valueOf(activitiesCount++));
    }


    private void saveToDatabase(ActivityModel activityModel, String userID, String count) {
        database.child(userID)
                .child(count)
                .setValue(activityModel)
                .addOnSuccessListener(aVoid ->
                {
                    view.showMessage("Pomyślnie zaktualizowano dane");
                    String historyActivity = (activityModel.getActivity() + " dnia " + activityModel.getDate() + " z tętnem " + activityModel.getHeartrate() + " przez " + activityModel.getTime() + "m");
                    view.updateHistory(historyActivity);
                })
                .addOnFailureListener(e -> view.showMessage("Nie udało się zaktualizować danych"));
    }

    private void initActivitiesCounter() {
        database.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ignored : dataSnapshot.getChildren()) {
                    activitiesCount++;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                view.showMessage("Błąd połączenia z bazą. Spróbuj ponownie.");
            }
        });
    }

    private int getActivitiesCount() {
        System.out.println("ILOSC: " + this.activitiesCount);
        return this.activitiesCount;
    }
}