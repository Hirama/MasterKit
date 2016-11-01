package super_ego.info.masterkit.fragments.learning_fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.adapter.RecycleViewAdapterLearningStep;
import super_ego.info.masterkit.model.ListItemVideoStep;

/**
 * Created by Andrey on 21.10.2016.
 */

public class LearningStepFragment extends android.support.v4.app.Fragment {

    public RecyclerView.Adapter mAdapter;
    RecyclerView listView;
    private FragmentActivity myContext;
    private RecyclerView.LayoutManager mLayoutManager;

    public LearningStepFragment() {
        //empty
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_learning_step_with_video, container, false);
        getActivity().setTitle("Шаги");
        listView = (RecyclerView) rootView.findViewById(R.id.recycleView_with_video);

        List<ListItemVideoStep> records = new ArrayList<>();
        ListItemVideoStep firstItem = new ListItemVideoStep("ШАГ 23", "Текст текст", "Вопрос?", "LVu0YKegGdE");
        ListItemVideoStep secondItem = new ListItemVideoStep("ШАГ 24", "Текст текст", "Вопрос?", "XALLZHKnS_U");
        ListItemVideoStep thirdItem = new ListItemVideoStep("ШАГ 25", "Текст текст", "Вопрос?", "XThpDZzOSPg");

        records.add(firstItem);
        records.add(secondItem);
        records.add(thirdItem);


        mLayoutManager = new LinearLayoutManager(this.getActivity());
        listView.setLayoutManager(mLayoutManager);
        mAdapter = new RecycleViewAdapterLearningStep(records, this);

        listView.setAdapter(mAdapter);
        return rootView;
    }
}
