package pl.w_kowalczyk.mytraining.ui.application.fragments.analise.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import pl.w_kowalczyk.mytraining.ui.application.fragments.analise.view.AnaliseFragmentView;
import pl.w_kowalczyk.mytraining.ui.application.model.ActivityModel;

public class AnaliseFragmentPresenterCompl implements AnaliseFragmentPresenter {
    private AnaliseFragmentView view;
    private FirebaseAuth firebaseAuth;
    private String userID;
    private String firstActivity = null;
    private String lastActivity;
    private int averageHeartrate = 0;
    private int averageFiveLastHeartrate = 0;
    private int[] lastFivecount;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("/activity/");

    private int activitiesCount = 0;

    public AnaliseFragmentPresenterCompl(AnaliseFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userID = getCurrentUserID();
        initActivitiesCounter();
        getActivities();
        getLastFiveActivities();
        getLastFiveCount();
    }

    private String getCurrentUserID() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    public void getActivities() {
        database.child(userID).orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ActivityModel activityModel = snapshot.getValue(ActivityModel.class);
                    if (firstActivity == null) {
                        firstActivity = activityModel.getActivity() + " " + activityModel.getDate();
                    }
                    lastActivity = activityModel.getActivity() + " " + activityModel.getDate();
                    averageHeartrate += Integer.parseInt(activityModel.getHeartrate());


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getLastFiveActivities() {
        database.child(userID).orderByKey().limitToLast(5).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ActivityModel activityModel = snapshot.getValue(ActivityModel.class);


                    averageFiveLastHeartrate += Integer.parseInt(activityModel.getHeartrate());


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onSummaryClicked() {
        view.initSummary(getActivitiesCount(), getFirstActivity(), getLastActivity(), getAverageHeartrate(), getAverageLastFiveHeartrate());
    }

    private String[] initDays() {
        String[] days = new String[5];
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = (calendar.get(Calendar.MONTH) + 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        for (int i = 0; i < 5; i++) {
            days[i] = year + "." + month + "." + day--;
        }


        return days;
    }

    private void getLastFiveCount() {
        String[] days = initDays();

        int[] count = {0, 0, 0, 0, 0};


        database.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ActivityModel activityModel = snapshot.getValue(ActivityModel.class);


                    if (activityModel.getDate().equals(days[0])) {

                        count[4]++;
                    }
                    if (activityModel.getDate().equals(days[1])) {

                        count[3]++;
                    }
                    if (activityModel.getDate().equals(days[2])) {

                        count[2]++;
                    }
                    if (activityModel.getDate().equals(days[3])) {

                        count[1]++;
                    }
                    if (activityModel.getDate().equals(days[4])) {

                        count[0]++;
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lastFivecount = count;
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

            }
        });

    }

    private String getActivitiesCount() {
        return String.valueOf(this.activitiesCount);
    }

    private String getFirstActivity() {
        return this.firstActivity;
    }

    private String getLastActivity() {
        return this.lastActivity;
    }

    private String getAverageHeartrate() {
        if (averageHeartrate == 0 || activitiesCount == 0)
        {
            return "Brak danych do wyświetlenia";
        }
            return String.valueOf(this.averageHeartrate / this.activitiesCount);
    }

    private String getAverageLastFiveHeartrate() {
        return String.valueOf(this.averageFiveLastHeartrate / 5);
    }

    public void updateGraph() {
        view.initGraph(lastFivecount);
    }
}
