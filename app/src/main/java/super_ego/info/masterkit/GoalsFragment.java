package super_ego.info.masterkit;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.net.ssl.HttpsURLConnection;

import super_ego.info.masterkit.RESTapi.SetGoalToAPI;
import super_ego.info.masterkit.fragments.FrgGoalsHelth;
import super_ego.info.masterkit.fragments.FrgGoalsLove;
import super_ego.info.masterkit.fragments.FrgGoalsMoney;
import super_ego.info.masterkit.fragments.FrgGoalsParent;
import super_ego.info.masterkit.fragments.FrgGoalsTarget;
import super_ego.info.masterkit.fragments.GoalsFrgDialog;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.LearingPlanPOJO;
import super_ego.info.masterkit.util.RestUrl;

import static android.content.Context.MODE_PRIVATE;


public class GoalsFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private Fragment mCurrentFragment;
    private String token;
    Button btnAddGoal;
    FrgGoalsParent frgGoalsParent = new FrgGoalsParent();
    ////////!!!!!!!!!!!!!1
    FrgGoalsMoney frgGoalsMoney = new FrgGoalsMoney();
    FrgGoalsHelth frgGoalsHelth = new FrgGoalsHelth();
    FrgGoalsLove frgGoalsLove = new FrgGoalsLove();
    FrgGoalsTarget frgGoalsTarget = new FrgGoalsTarget();
////////////////////////!!!!!!!!!!!!1
    private int[] tabIcons = {
            R.drawable.goal_money,
            R.drawable.goal_love,
            R.drawable.goal_target,
            R.drawable.goal_helth
    };

    public GoalsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mPrefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (mPrefs.contains("user")) {
            token=mPrefs.getString("user","");

        }

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

                //MainActivity.GetGoals getGoals= new MainActivity.GetGoals(token);
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

        adapter.addFrag(frgGoalsMoney, "Money");
        adapter.addFrag(frgGoalsLove, "Love");
        adapter.addFrag(frgGoalsTarget, "Target");
        adapter.addFrag(frgGoalsHelth, "Helth");
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
        public List<Fragment> getmFragmentList(){
            return mFragmentList;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            // return null to display only the icon
            return null;
        }

    }

}




