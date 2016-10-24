package super_ego.info.masterkit.fragments.trainer_fragmnets;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import super_ego.info.masterkit.R;

/**
 * Created by Andrey on 24.10.2016.
 */

public class StepUniversalFragment extends Fragment {
    ImageButton imageButton;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean intialStage = true;

    public StepUniversalFragment() {
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_step_universal, container, false);
//        getActivity().setTitle("Тренажёр");
//
//        return rootView;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_universal, container, false);
        imageButton = (ImageButton) view.findViewById(R.id.imgBtnPlay);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String token = "";
        return view;
    }
}
