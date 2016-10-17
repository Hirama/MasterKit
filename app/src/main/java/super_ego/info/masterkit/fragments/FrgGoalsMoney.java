package super_ego.info.masterkit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import super_ego.info.masterkit.R;


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
        createAdapterAndListView(v,R.id.list_goal_money);

        return v;
    }
/*
    private void createAdapterAndListView(View v) {
        listGoalsMoney = (ListView) v.findViewById(R.id.list_goal_money);
        adapter = new ArrayAdapter<String>(this.getContext(), R.layout.my_list_item, getGoalsServer());
        listGoalsMoney.setAdapter(adapter);

    }*/
    @Override
    protected List getGoalsServer() {
        list = new ArrayList<>();
        list.add("testmoney1");
        list.add("testmoney2");
        list.add("testmoney3");
        return list;
    }
/*
    public void setListGoalsServer(String[] massGoalsFromServer) {
        list.clear();
        Collections.addAll(this.list, massGoalsFromServer);

        adapter.notifyDataSetChanged();
    }
*/

}
