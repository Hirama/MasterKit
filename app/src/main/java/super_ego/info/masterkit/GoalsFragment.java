package super_ego.info.masterkit;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


import super_ego.info.masterkit.fragments.FrgGoalsHelth;
import super_ego.info.masterkit.fragments.FrgGoalsLove;
import super_ego.info.masterkit.fragments.FrgGoalsMoney;
import super_ego.info.masterkit.fragments.FrgGoalsParent;
import super_ego.info.masterkit.fragments.FrgGoalsTarget;
import super_ego.info.masterkit.fragments.GoalsFrgDialog;


public class GoalsFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    Button btnAddGoal;
    FrgGoalsParent frgGoalsParent = new FrgGoalsParent();
    ////////!!!!!!!!!!!!!1
    FrgGoalsMoney frgGoalsMoney = new FrgGoalsMoney();
////////////////////////!!!!!!!!!!!!1
    private int[] tabIcons = {
            R.drawable.goal_money,
            R.drawable.goal_love,
            R.drawable.goal_target,
            R.drawable.goal_helth
    };

    public GoalsFragment() {
    }
/*
    public static GoalsFragment newInstance(String param1, String param2) {
        GoalsFragment fragment = new GoalsFragment();
        Bundle args = new Bundle();
      //  arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_goals, container, false);
        viewPager = (CustomViewPager) v.findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        btnAddGoal = (Button) v.findViewById(R.id.btnAddGoal);
       // pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] found = new String[]{"1", "2", "3"};
                new GoalsFrgDialog().show(getActivity().getSupportFragmentManager(),
                        "login");
                frgGoalsMoney.setListGoalsServer(found);
              //   ((FrgGoalsParent) getActivity().getSupportFragmentManager().getFragment()).setListGoalsServer(found);

            }
        };
        btnAddGoal.setOnClickListener(oclBtn);

        return v;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }


    private void setupViewPager(ViewPager viewPager) {
        GoalsFragment.ViewPagerAdapter adapter = new GoalsFragment.ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFrag(frgGoalsMoney, "Money ");
        adapter.addFrag(new FrgGoalsLove(), "Love");
        adapter.addFrag(new FrgGoalsTarget(), "Target");
        adapter.addFrag(new FrgGoalsHelth(), "Helth");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return null;
        }
    }
}
