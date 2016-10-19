package super_ego.info.masterkit.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.GoalSectionPOJO;
import super_ego.info.masterkit.model.GoalsPOJO;

import static android.content.Context.MODE_PRIVATE;


public class FrgGoalsTarget extends FrgGoalsParent {

    ArrayAdapter<String> adapter;
    ListView listGoalsLove;
    List<String> list;

    public FrgGoalsTarget() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_goals_target, container, false);
        createAdapterAndListView(v,R.id.list_goal_target);
        return v;
    }

    @Override
    protected List getGoalsServer() {
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("data",MODE_PRIVATE);
        if (mPrefs.contains("goals")) {
            list = new ArrayList<>();
            Gson gson = new Gson();
            String json = mPrefs.getString("goals", "");
            GoalResultPOJO obj= gson.fromJson(json, GoalResultPOJO.class);
            GoalSectionPOJO goalSectionPOJO=obj.getData();
            for(GoalsPOJO i:goalSectionPOJO.getDestiny()){

                    list.add(i.getTitle());

            }

        }
        return list;
    }
}

