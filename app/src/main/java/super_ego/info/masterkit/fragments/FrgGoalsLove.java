package super_ego.info.masterkit.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_goals_love, container, false);
        createAdapterAndListView(v,R.id.list_goal_love);
        return v;
    }
/*
    @Override
    protected void createAdapterAndListView(View v,int listGoal) {
        listGoalsLove = (ListView) v.findViewById(R.id.list_goal_love);
        adapter = new ArrayAdapter<String>(this.getContext(), R.layout.my_list_item, getGoalsServer());
        listGoalsLove.setAdapter(adapter);

    }

*/
    @Override
    protected List getGoalsServer() {
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("data",MODE_PRIVATE);
        if (mPrefs.contains("goals")) {
            list = new ArrayList<>();
            Gson gson = new Gson();
            String json = mPrefs.getString("goals", "");
            GoalResultPOJO obj= gson.fromJson(json, GoalResultPOJO.class);
            GoalSectionPOJO goalSectionPOJO=obj.getData();
            for(GoalsPOJO i:goalSectionPOJO.getRelationship()){
                   list.add(i.getTitle());

            }

        }
        return list;
    }
/*
    public void setListGoalsServer(String[] massGoalsFromServer) {
        list.clear();
        Collections.addAll(this.list, massGoalsFromServer);

        adapter.notifyDataSetChanged();
    }*/
}
