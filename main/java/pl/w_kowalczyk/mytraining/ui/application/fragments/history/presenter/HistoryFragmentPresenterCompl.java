package pl.w_kowalczyk.mytraining.ui.application.fragments.history.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import pl.w_kowalczyk.mytraining.ui.application.fragments.history.view.HistoryFragmentView;
import pl.w_kowalczyk.mytraining.ui.application.model.ActivityModel;
import pl.w_kowalczyk.mytraining.ui.application.model.HistoryModel;

public class HistoryFragmentPresenterCompl implements HistoryFragmentPresenter {
    private HistoryFragmentView view;
    private FirebaseAuth firebaseAuth;

    private String userID;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("/activity/");

    public HistoryFragmentPresenterCompl(HistoryFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userID = getCurrentUserID();
        getActivities();
    }

    private String getCurrentUserID() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    public LineGraphSeries<DataPoint> getSeries(int position) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        database.child(userID).child(String.valueOf(position)).child("heartrate_progress")
                .child("heartrate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count=1;
                    int value;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        value  = Integer.parseInt(snapshot.getValue().toString());
                        series.appendData(new DataPoint(count, value), false, 3600);
                        count++; }
                } else {
                    System.out.println("BRAK DANYCH");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return series;
    }

    public void getActivities() {
        database.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<HistoryModel> historyModels = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ActivityModel activityModel = snapshot.getValue(ActivityModel.class);
                    HistoryModel historyModel = new HistoryModel(
                            activityModel.getActivity(),
                            activityModel.getDate(),
                            activityModel.getHeartrate(),
                            activityModel.getTime()
                    );
                    historyModels.add(historyModel);
                }
                view.setHistoryModels(historyModels);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            view.showMessage("Błąd bazy danych. Spróbuj ponownie");
            }
        });
    }
}
