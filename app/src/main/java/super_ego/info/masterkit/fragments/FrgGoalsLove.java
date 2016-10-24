package super_ego.info.masterkit.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import super_ego.info.masterkit.GoalsFragment;
import super_ego.info.masterkit.R;
import super_ego.info.masterkit.adapter.TrainerGoalsActivity;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.GoalSectionPOJO;
import super_ego.info.masterkit.model.GoalsPOJO;
import super_ego.info.masterkit.model.UserPOJO;

import static android.content.Context.MODE_PRIVATE;

public class FrgGoalsLove extends FrgGoalsParent {


    private GoalsFragment goalsFragment;
    public final String relationship="relationship";
    public FrgGoalsLove() {
    }
    public String getMoney() {
        return relationship;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_goals_love, container, false);
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
                Log.d("то что тебе нужно бобур", "sss" + position);
                Log.d("название цели", "sss" + list.get(position));

                Intent intent = new Intent(getActivity(), TrainerGoalsActivity.class);
                intent.putExtra("Target", position);
                startActivity(intent);
//                TrainerGoalsMainFragment trainerGoalsMainFragment = new TrainerGoalsMainFragment();
//                android.support.v4.app.FragmentTransaction fTrans;
//                fTrans = getFragmentManager().beginTransaction();
//                fTrans.replace(R.id.frgmContMain, trainerGoalsMainFragment);
//                fTrans.addToBackStack("trainerGoals");
//                fTrans.commit();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frgmCont, trainerGoalsMainFragment).commit();

            }
        });
        return v;
    }
    public void setMainFragment(GoalsFragment goalsFragment){
        this.goalsFragment = goalsFragment;
    }


    @Override
    protected List getGoalsServer() {
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("goal", MODE_PRIVATE);
        if (mPrefs.contains("goals")) {
            list = new ArrayList<>();
            Gson gson = new Gson();
            String json = mPrefs.getString("goals", "");
            GoalResultPOJO obj = gson.fromJson(json, GoalResultPOJO.class);
            GoalSectionPOJO goalSectionPOJO = obj.getData();
            for (GoalsPOJO i : goalSectionPOJO.getRelationship()) {
                list.add(i.getTitle());

            }

        }

        return list;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            Log.d("******","Love");
            this.goalsFragment.changeIconTabs("love");
        }
    }
    public void addNewGoals(String goal) {
        if(getUserVisibleHint()) {
            ((GoalsFragmAdapter) mRecyclerView.getAdapter()).addNewGoal(goal);
            setUpRecyclerView();
        }else{
            Log.d("******","it is not visible");
        }
    }

}
