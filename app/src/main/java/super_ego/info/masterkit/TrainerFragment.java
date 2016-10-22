package super_ego.info.masterkit;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import super_ego.info.masterkit.fragments.trainer_fragmnets.PagerAdapterTrainer;

/**
 * Created by Andrey on 18.10.2016.
 */

public class TrainerFragment extends Fragment {

    private Toolbar supportActionBar;

    public TrainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View liView = inflater.inflate(R.layout.fragment_trainer, container, false);
        getActivity().setTitle("Тренажёр");

        TabLayout tabLayout = (TabLayout) liView.findViewById(R.id.tab_layout_fragment_trainer);
        tabLayout.addTab(tabLayout.newTab().setText("Ситуация"));
        tabLayout.addTab(tabLayout.newTab().setText("Универсальные"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) liView.findViewById(R.id.pager);
        final PagerAdapterTrainer adapter = new PagerAdapterTrainer
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return liView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }

    public Toolbar getSupportActionBar() {
        return supportActionBar;
    }
}
