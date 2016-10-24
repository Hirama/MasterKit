package super_ego.info.masterkit.fragments.trainer_fragmnets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import super_ego.info.masterkit.R;

/**
 * Created by Andrey on 24.10.2016.
 */

public class StepSituationFragment extends Fragment {

    public StepSituationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_situation, container, false);
        getActivity().setTitle("Тренажёр");

        return rootView;
    }
}
