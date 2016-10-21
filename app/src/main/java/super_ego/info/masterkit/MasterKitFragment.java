package super_ego.info.masterkit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import super_ego.info.masterkit.adapter.RecycleViewAdapterMasterKit;
import super_ego.info.masterkit.model.VideoModel;

/**
 * Created by Andrey on 18.10.2016.
 */

public class MasterKitFragment extends Fragment {
    RecyclerView videoList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MasterKitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View liView = inflater.inflate(R.layout.fragment_master_kit, container, false);
        videoList = (RecyclerView) liView.findViewById(R.id.video_recycle_view);

        getActivity().setTitle("Master Kit Live");

        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        videoList.setLayoutManager(mLayoutManager);

        List<VideoModel> records = new ArrayList<>();
        VideoModel videoModel = new VideoModel();
        videoModel.setDate("23 ноября 2016");
        videoModel.setName("Методика в Жизни");
        videoModel.setImage(R.mipmap.small_video);
        records.add(videoModel);
        VideoModel videoModel1 = new VideoModel();
        videoModel1.setDate("13 ноября 2016");
        videoModel1.setName("Универсальные методики");
        videoModel1.setImage(R.mipmap.small_video);
        records.add(videoModel1);
        mAdapter = new RecycleViewAdapterMasterKit(records);
        videoList.setAdapter(mAdapter);
        return liView;
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
