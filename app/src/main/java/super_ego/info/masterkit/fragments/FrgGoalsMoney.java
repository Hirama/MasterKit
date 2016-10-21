package super_ego.info.masterkit.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.GoalSectionPOJO;
import super_ego.info.masterkit.model.GoalsPOJO;

import static android.content.Context.MODE_PRIVATE;


public class FrgGoalsMoney extends FrgGoalsParent {


    public FrgGoalsMoney() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_goals_money, container, false);
        getGoalsServer();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        setUpRecyclerView();
        mRecyclerView.addOnItemTouchListener(new RecyclerClickListener(getContext()) {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView,
                                    int position) {
                Log.d("+++++++++++++++++", "sss" + position);
                TrainerGoalsMainFragment trainerGoalsMainFragment = new TrainerGoalsMainFragment();
                FragmentTransaction fTrans;
                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.frgmCont, trainerGoalsMainFragment);
                fTrans.addToBackStack("trainerGoals");
                fTrans.commit();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frgmCont, trainerGoalsMainFragment).commit();

            }
        });
        return v;
    }

    @Override
    protected List getGoalsServer() {
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (mPrefs.contains("goals")) {
            list = new ArrayList<>();
            Gson gson = new Gson();
            String json = mPrefs.getString("goals", "");
            Log.d("**********",json);
            GoalResultPOJO obj = gson.fromJson(json, GoalResultPOJO.class);
            GoalSectionPOJO goalSectionPOJO = obj.getData();
            for (GoalsPOJO i : goalSectionPOJO.getMoney()) {

                list.add(i.getTitle());

            }

        }
        return list;
    }

    public void addNewGoals() {
        ((GoalsFragmAdapter) mRecyclerView.getAdapter()).addNewGoal(new String[]{});
        setUpRecyclerView();
    }


}
