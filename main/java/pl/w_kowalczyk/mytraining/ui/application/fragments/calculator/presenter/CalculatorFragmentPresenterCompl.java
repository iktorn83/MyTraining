package pl.w_kowalczyk.mytraining.ui.application.fragments.calculator.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.w_kowalczyk.mytraining.ui.application.fragments.calculator.view.CalculatorFragmentView;
import pl.w_kowalczyk.mytraining.ui.application.model.CpmModel;
import pl.w_kowalczyk.mytraining.ui.application.model.UserModel;
import pl.w_kowalczyk.mytraining.util.calculators.CpmCalculator;
import pl.w_kowalczyk.mytraining.util.calculators.bmr.HarrisBenedictCalculator;
import pl.w_kowalczyk.mytraining.util.calculators.bmr.KatchMcArdleCalculator;
import pl.w_kowalczyk.mytraining.util.calculators.bmr.MifflinStJeorCalculator;

public class CalculatorFragmentPresenterCompl implements CalculatorFragmentPresenter {
    private CalculatorFragmentView view;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private UserModel userModel;
    private String userId;
    private KatchMcArdleCalculator katchMcArdleCalculator;
    private MifflinStJeorCalculator mifflinStJeorCalculator;
    private HarrisBenedictCalculator harrisBenedictCalculator;

    public CalculatorFragmentPresenterCompl(CalculatorFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userId = firebaseAuth.getCurrentUser().getUid();
    }

    private String getUserId() {
        return this.userId;
    }

    public void calculateClicked() {

        database = FirebaseDatabase.getInstance().getReference("/notes/" + getUserId());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!dataSnapshot.exists()) {
                    view.showUserDownloadedOk("Najpierw uzupełnij profil!");
                    return;
                }

                String age = String.valueOf(dataSnapshot.child("age").getValue());
                String bmrPointer = String.valueOf(dataSnapshot.child("bmrPointer").getValue());
                String gender = String.valueOf(dataSnapshot.child("gender").getValue());
                String height = String.valueOf(dataSnapshot.child("height").getValue());
                String weight = String.valueOf(dataSnapshot.child("weight").getValue());
                String muscleMass = String.valueOf(dataSnapshot.child("muscleMass").getValue());
                String calories = String.valueOf(dataSnapshot.child("calories").getValue());

                userModel = new UserModel(getUserId(), gender, height, weight, age, muscleMass, bmrPointer, calories);
                initCalcualtors(userModel);


                view.showHarrisBenedictMethod(String.valueOf(harrisBenedictCalculator.calculate()));
                view.showMifflinStJeorMethod(String.valueOf(mifflinStJeorCalculator.calculate()));
                if (muscleMass.equals("0")) {
                    view.showKatchMcArdle(String.valueOf(katchMcArdleCalculator.calculate()), -1);
                } else {
                    view.showKatchMcArdle(String.valueOf(katchMcArdleCalculator.calculate()), 1);
                }

                view.showCalories(calories);
                int cpm = CpmCalculator.calculateCpm(bmrPointer, mifflinStJeorCalculator.calculate());
                updateCpm(cpm);
                view.showCpm(String.valueOf(cpm));
                int difference = userModel.getCalories() - cpm;
                view.showDifference(String.valueOf(difference));
                if (difference < 0) {
                    view.showSuggestion("Zwiększ kalorie spożywane!");
                } else {
                    int howMuchTime = difference / 10;
                    view.showSuggestion("Zalecane " + howMuchTime + "minut intensywnego treningu!");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void initCalcualtors(UserModel userModel){
        katchMcArdleCalculator = new KatchMcArdleCalculator(userModel);
        mifflinStJeorCalculator = new MifflinStJeorCalculator(userModel);
        harrisBenedictCalculator = new HarrisBenedictCalculator(userModel);
    }

    private void updateCpm(int cpm) {
        CpmModel cpmModel = new CpmModel(cpm);
        database.child("cpm")
                .setValue(cpmModel)
                .addOnSuccessListener(aVoid -> view.showMessage("Pomyślnie zaktualizowano CPM"))
                .addOnFailureListener(e -> view.showMessage("Nie udało się zaktualizować CPM"));
    }

}
