package super_ego.info.masterkit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.R;

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
        list = new ArrayList<>();
        list.add("testlove1");
        list.add("testlove2");
        list.add("testlove3");
        return list;
    }
/*
    public void setListGoalsServer(String[] massGoalsFromServer) {
        list.clear();
        Collections.addAll(this.list, massGoalsFromServer);

        adapter.notifyDataSetChanged();
    }*/
}
