package super_ego.info.masterkit.fragments.trainer_fragmnets;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.adapter.DividerItemDecoration;
import super_ego.info.masterkit.adapter.ItemClickSupport;
import super_ego.info.masterkit.adapter.RecycleViewAdapterSituationFragment;
import super_ego.info.masterkit.model.TempModel;

/**
 * Created by Andrey on 19.10.2016.
 */

public class SituationFragment extends Fragment {
    RecyclerView listView;
    List<TempModel> records = new ArrayList<>();

    public SituationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_situation, container, false);
        listView = (RecyclerView) fragmentView.findViewById(R.id.situation_recycle_list);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        listView.setLayoutManager(mLayoutManager);
        listView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        //Populate some list

        TempModel tempModel = new TempModel();
        tempModel.setName("Ситуация");
        tempModel.setImageId(R.drawable.situation);
        records.add(tempModel);

        TempModel tempModel1 = new TempModel();

        tempModel1.setName("Качество");
        tempModel1.setImageId(R.drawable.quality);
        records.add(tempModel1);

        TempModel tempMode2 = new TempModel();

        tempMode2.setName("Страх");
        tempMode2.setImageId(R.drawable.fear);
        records.add(tempMode2);

        TempModel tempMode3 = new TempModel();
        tempMode3.setName("Обида");
        tempMode3.setImageId(R.drawable.offense);
        records.add(tempMode3);

        TempModel tempMode4 = new TempModel();
        tempMode4.setName("Образ");
        tempMode4.setImageId(R.drawable.image);
        records.add(tempMode4);

        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new RecycleViewAdapterSituationFragment(records);
        ItemClickSupport.addTo(listView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Toast.makeText(getContext(), "WOW SUCH WORK!", Toast.LENGTH_SHORT).show();
                        FragmentManager fragManager = getActivity().getSupportFragmentManager();
                        StepSituationFragment stepSituationFragment = new StepSituationFragment();
                        fragManager.beginTransaction()
                                .replace(R.id.frgmContMain, stepSituationFragment)
//                                .addToBackStack(null)
                                .commit();
                    }
                });
        listView.setAdapter(mAdapter);

        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
