package super_ego.info.masterkit.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.GoalSectionPOJO;
import super_ego.info.masterkit.model.GoalsPOJO;
import super_ego.info.masterkit.model.UserPOJO;

import static android.content.Context.MODE_PRIVATE;

public class FrgGoalsLove extends FrgGoalsParent {


    public FrgGoalsLove() {
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
        return v;
    }

    @Override
    protected List getGoalsServer() {
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("data", MODE_PRIVATE);
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

    public void addNewGoals() {
        ((GoalsFragmAdapter) mRecyclerView.getAdapter()).addNewGoal(new String[]{});
        setUpRecyclerView();
    }

}
