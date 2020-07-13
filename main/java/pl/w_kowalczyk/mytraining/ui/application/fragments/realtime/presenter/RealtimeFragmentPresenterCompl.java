package pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.presenter;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;

import pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.view.RealtimeFragmentView;
import pl.w_kowalczyk.mytraining.ui.application.model.ActivityModel;
import pl.w_kowalczyk.mytraining.ui.application.model.HeartrateModel;

public class RealtimeFragmentPresenterCompl implements RealtimeFragmentPresenter {
    RealtimeFragmentView view;
    FirebaseAuth firebaseAuth;
    private String userID;
    int count = 0;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("/activity/");


    public RealtimeFragmentPresenterCompl(RealtimeFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userID = getCurrentUserID();
        setCount();

    }

    private String getCurrentUserID() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    @Override
    public void onSendClicked(String activity, TreeMap heartrate, String time) {
        getCount();
        String date = getCurrentDate();
        String averageHeartrate = String.valueOf(getAverageHeartrate(heartrate));
        HeartrateModel heartrateModel = new HeartrateModel(heartrate);
        ActivityModel activityModel = new ActivityModel(activity, date, averageHeartrate, time);

        saveToDatabase(activityModel, userID, String.valueOf(count++), heartrateModel);
    }

    private void saveToDatabase(ActivityModel activityModel, String userID, String count, HeartrateModel heartrateModel) {
        database.child(userID)
                .child(count)
                .setValue(activityModel)
                .addOnSuccessListener(aVoid ->
                {
                    view.showMessage("Pomyślnie zaktualizowano aktywność");
                    saveProgress(userID, count, heartrateModel);
                })
                .addOnFailureListener(e -> view.showMessage("Nie udało się zaktualizować aktywności"));


    }

    private void saveProgress(String userID, String count, HeartrateModel heartrateModel) {
        database.child(userID)
                .child(count)
                .child("heartrate_progress")
                .setValue(heartrateModel)
                .addOnSuccessListener(aVoid ->
                {
                    view.showMessage("Pomyślnie zaktualizowano przebieg");


                })
                .addOnFailureListener(e -> view.showMessage("Nie udało się zaktualizować przebiegu"));
    }

    private int setCount() {


        database.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ignored : dataSnapshot.getChildren()) {

                    count++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return count;
    }

    private int getCount() {
        System.out.println("ILOSC: " + this.count);
        return this.count;
    }

    private int getAverageHeartrate(TreeMap treeMap) {
        Set<String> set1 = treeMap.keySet();
        int sum = 0, count = 0;

        for (String key : set1) {
            count++;
            sum +=  Integer.valueOf(String.valueOf(treeMap.get(key)));

        }
        return sum / count;
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
