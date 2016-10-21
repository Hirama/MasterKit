package super_ego.info.masterkit.fragments.trainer_goal_fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import super_ego.info.masterkit.MainActivity;
import super_ego.info.masterkit.R;


public class TrainerGoalsMainFragment extends Fragment {

    ImageButton imageButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public TrainerGoalsMainFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainerGoalsMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainerGoalsMainFragment newInstance(String param1, String param2) {
        TrainerGoalsMainFragment fragment = new TrainerGoalsMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    boolean flag = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trainer_goals_main, container, false);
        imageButton = (ImageButton) view.findViewById(R.id.imgBtnPlay);
        //((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButton.setOnClickListener(new View.OnClickListener()

                                       {
                                           public void onClick(View v) {
                                               if (flag)
                                                   imageButton.setImageResource(R.drawable.playbutton);
                                               else
                                                   imageButton.setImageResource(R.drawable.pausebutton);
                                               flag = !flag;
                                           }
                                       }

        );
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


}