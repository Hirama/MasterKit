package super_ego.info.masterkit.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.GoalsFragment;
import super_ego.info.masterkit.R;
import super_ego.info.masterkit.adapter.TrainerGoalsActivity;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.GoalSectionPOJO;
import super_ego.info.masterkit.model.GoalsPOJO;

import static android.content.Context.MODE_PRIVATE;


public class FrgGoalsMoney extends FrgGoalsParent {

    private GoalsFragment goalsFragment;
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

                Intent intent = new Intent(getActivity(), TrainerGoalsActivity.class);
                intent.putExtra("Target", list.get(position));
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
            for (GoalsPOJO i : goalSectionPOJO.getMoney()) {

                list.add(i.getTitle());
            }
        }
        //}
        return list;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
          this.goalsFragment.changeIconTabs("money");
        }
    }

    public void addNewGoals(String goal) {
        if (getUserVisibleHint()) {
            ((GoalsFragmAdapter) mRecyclerView.getAdapter()).addNewGoal(goal);
            setUpRecyclerView();
        } else {

        }
    }

}
