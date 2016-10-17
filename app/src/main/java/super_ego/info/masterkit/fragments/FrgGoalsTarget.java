package super_ego.info.masterkit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.R;


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
        list = new ArrayList<>();
        list.add("testtarget1");
        list.add("testtarget2");
        list.add("testtarget3");
        return list;
    }
}

