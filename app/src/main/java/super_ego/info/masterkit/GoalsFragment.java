package super_ego.info.masterkit;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    private Fragment mCurrentFragment;
    //    private String token;
    Button btnAddGoal;
    FrgGoalsParent frgGoalsParent = new FrgGoalsParent();
    ////////!!!!!!!!!!!!!1
    FrgGoalsMoney frgGoalsMoney = new FrgGoalsMoney();
    FrgGoalsHelth frgGoalsHelth = new FrgGoalsHelth();
    FrgGoalsLove frgGoalsLove = new FrgGoalsLove();
    FrgGoalsTarget frgGoalsTarget = new FrgGoalsTarget();
    ////////////////////////!!!!!!!!!!!!1
    private int[] tabIcons = {
            R.drawable.goal_money_select,
            R.drawable.goal_love,
            R.drawable.goal_target,
            R.drawable.goal_helth
    };

    public GoalsFragment() {
    }

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

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new GoalsFrgDialog().show(getActivity().getSupportFragmentManager(),
                        "login");
//                SharedPreferences mPrefs = getActivity().getSharedPreferences("goal", MODE_PRIVATE);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();
//                editor.apply();
//                if (mPrefs.contains("user")) {
//                    String token=mPrefs.getString("user","");
//                    MainActivity outer = new MainActivity();
//                    outer.new GetGoals(token)
//                            .execute();
//                }
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

        List<Fragment> pages = new ArrayList<>();
        GoalsFragment.ViewPagerAdapter adapter = new GoalsFragment.ViewPagerAdapter(getActivity().getSupportFragmentManager(), pages);
        pages.add(frgGoalsMoney);
        adapter.notifyDataSetChanged();
        frgGoalsMoney.setMainFragment(this);
        pages.add(frgGoalsLove);
        adapter.notifyDataSetChanged();
        frgGoalsLove.setMainFragment(this);
        pages.add(frgGoalsTarget);
        adapter.notifyDataSetChanged();
        frgGoalsTarget.setMainFragment(this);
        pages.add(frgGoalsHelth);
        adapter.notifyDataSetChanged();
        frgGoalsHelth.setMainFragment(this);
        viewPager.setAdapter(adapter);
    }
    public void changeIconTabs(String s) {
        switch (s) {
            case "helth":
                tabIcons[0] = R.drawable.goal_money;
                tabIcons[1] = R.drawable.goal_love;
                tabIcons[2] = R.drawable.goal_target;
                tabIcons[3] = R.drawable.goal_helth_select;
                setupTabIcons();
                break;
            case "target":
                tabIcons[0] = R.drawable.goal_money;
                tabIcons[1] = R.drawable.goal_love;
                tabIcons[2] = R.drawable.goal_target_select;
                tabIcons[3] = R.drawable.goal_helth;

                setupTabIcons();
                break;
            case "love":
                tabIcons[0] = R.drawable.goal_money;
                tabIcons[1] = R.drawable.goal_love_select;
                tabIcons[2] = R.drawable.goal_target;
                tabIcons[3] = R.drawable.goal_helth;

                setupTabIcons();
                break;
            case "money":
                tabIcons[0] = R.drawable.goal_money_select;
                tabIcons[1] = R.drawable.goal_love;
                tabIcons[2] = R.drawable.goal_target;
                tabIcons[3] = R.drawable.goal_helth;

                setupTabIcons();
                break;

        }
    }
    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragmentList;


        public ViewPagerAdapter(FragmentManager manager, List<Fragment> pages) {
            super(manager);
            this.mFragmentList = pages;
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        @Override
        public int getItemPosition(Object object) {
            int index = mFragmentList.indexOf(object);

            if (index == -1)
                return POSITION_NONE;
            else
                return index;

        }


    }

}




