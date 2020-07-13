package pl.w_kowalczyk.mytraining.ui.application.fragments.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.history.HistoryFragment;
import pl.w_kowalczyk.mytraining.ui.application.fragments.realtime.RealtimeFragment;
import pl.w_kowalczyk.mytraining.util.other.TabAdapter;

public class TabActivitiesFragment extends Fragment {
    TabAdapter adapter;
    ViewPager viewPager;
    TabLayout tabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tab_layout, container, false);

        findViews(view);
        setupVievPager();

        return view;

    }

    private void findViews(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabs = (TabLayout) view.findViewById(R.id.result_tabs);
    }

    private void setupVievPager() {
        setupViewPager(viewPager);


        tabs.setupWithViewPager(viewPager);
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {

        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new ActivitiesFragment(), "Archiwalne");
        adapter.addFragment(new RealtimeFragment(), "Obecne");
        adapter.addFragment(new HistoryFragment(), "Historia");
        viewPager.setAdapter(adapter);


    }


}
