package super_ego.info.masterkit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.R;


public class FrgGoalsHelth extends FrgGoalsParent {

    public FrgGoalsHelth() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frg_goals_helth, container, false);
        createAdapterAndListView(v,R.id.list_goal_helth);
        return v;
    }
    @Override
    protected List getGoalsServer() {
        list = new ArrayList<>();
        list.add("testhelth1");
        list.add("testhelth2");
        list.add("testhelth3");
        return list;
    }
}
