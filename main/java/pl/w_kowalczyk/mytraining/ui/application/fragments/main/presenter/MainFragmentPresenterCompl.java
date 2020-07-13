package pl.w_kowalczyk.mytraining.ui.application.fragments.main.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.w_kowalczyk.mytraining.ui.application.fragments.main.view.MainFragmentView;
import pl.w_kowalczyk.mytraining.util.calculators.CaloriesCalculator;
import pl.w_kowalczyk.mytraining.util.calculators.NutrientsCalculator;

public class MainFragmentPresenterCompl implements MainFragmentPresenter {
    MainFragmentView view;
    FirebaseAuth firebaseAuth;
    private DatabaseReference database;

    public MainFragmentPresenterCompl(MainFragmentView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("/notes/" + getCurrentUserId() + "/cpm/");
    }


    public String getCurrentUserEmail() {
        return firebaseAuth.getCurrentUser().getEmail();
    }

    public String getCurrentUserId() {
        if (firebaseAuth.getCurrentUser().getUid() != null) {
            return firebaseAuth.getCurrentUser().getUid();
        }
        return "Błąd";
    }

    public void downloadCpmData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int basicCPM = Integer
                            .parseInt(String.valueOf(dataSnapshot.child("basic_cpm").getValue()));
                    view.setBasicCpm(
                            NutrientsCalculator.calculateProtein(basicCPM, 1),
                            NutrientsCalculator.calculateCarbohydrates(basicCPM, 1),
                            NutrientsCalculator.calculateFat(basicCPM, 1),
                            basicCPM,
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateProtein(basicCPM, 1), false),
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateCarbohydrates(basicCPM, 1), false),
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateCarbohydrates(basicCPM, 1), true));

                    int reduceMassCPM = Integer.parseInt(
                            String.valueOf(dataSnapshot.child("reduce_mass_cpm").getValue()));
                    view.setReduceCpm(
                            NutrientsCalculator.calculateProtein(reduceMassCPM, 2),
                            NutrientsCalculator.calculateCarbohydrates(reduceMassCPM, 2),
                            NutrientsCalculator.calculateFat(reduceMassCPM, 2),
                            reduceMassCPM,
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateProtein(reduceMassCPM, 2), false),
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateCarbohydrates(reduceMassCPM, 2), false),
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateCarbohydrates(reduceMassCPM, 2), true));

                    int gainMassCPM = Integer
                            .parseInt(String.valueOf(dataSnapshot.child("gain_mass_cpm").getValue()));
                    view.setGainCpm(
                            NutrientsCalculator.calculateProtein(gainMassCPM, 3),
                            NutrientsCalculator.calculateCarbohydrates(gainMassCPM, 3),
                            NutrientsCalculator.calculateFat(gainMassCPM, 3),
                            gainMassCPM,
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateProtein(gainMassCPM, 3), false),
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateCarbohydrates(gainMassCPM, 3), false),
                            CaloriesCalculator.kilocaloriesToGrams(NutrientsCalculator
                                    .calculateCarbohydrates(gainMassCPM, 3), true));
                }
                else {
                    view.showMessage("Niemożliwe do wyświetlenia, uzupełnij profil i uruchom kalkulator!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.showMessage("Niemożliwe do wyświetlenia, błąd połączenia!");
            }
        });
    }


}
