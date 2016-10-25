package super_ego.info.masterkit.fragments.trainer_goal_fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import super_ego.info.masterkit.MainActivity;
import super_ego.info.masterkit.R;
import super_ego.info.masterkit.model.AudioGoal;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.GoalSectionPOJO;
import super_ego.info.masterkit.model.GoalsPOJO;
import super_ego.info.masterkit.util.RestUrl;

import static android.content.Context.MODE_PRIVATE;


public class TrainerGoalsMainFragment extends Fragment {

    ImageButton imageButton;
    TextView goalText;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean intialStage = true;
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

    boolean flag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trainer_goals_main, container, false);
        imageButton = (ImageButton) view.findViewById(R.id.imgBtnPlay);
        //((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String token="";
        SharedPreferences mPrefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (mPrefs.contains("user")) {
            token = mPrefs.getString("user", "");

        }
        String goalName = getArguments().getString("goalId");
        Integer goalId=0;
        SharedPreferences goalDetailCache = this.getActivity().getSharedPreferences("goal", MODE_PRIVATE);
        if (goalDetailCache.contains("goals")) {

            Gson gson = new Gson();
            String json = goalDetailCache.getString("goals", "");
            GoalResultPOJO obj = gson.fromJson(json, GoalResultPOJO.class);
            GoalSectionPOJO goalSectionPOJO = obj.getData();
            for (GoalsPOJO i : goalSectionPOJO.getMoney()) {
                if(i.getTitle().equals(goalName)){
                    goalId=i.getId();
                }
            }
        }
        GetAudioGoals getAudioGoals = new GetAudioGoals(token,String.valueOf(goalId));
        AudioGoal audioGoal=null;
        try {
            audioGoal=getAudioGoals.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final String audioLink=audioGoal.getData().getMp3();
        String replaceBreaks=audioGoal.getData().getText();
        replaceBreaks=replaceBreaks.replaceAll("\n","");
        replaceBreaks = replaceBreaks.trim().replaceAll(" +", " ");
        goalText = (TextView) view.findViewById(R.id.textView2);
        goalText.setText(Html.fromHtml(replaceBreaks));

        imageButton.setOnClickListener(new View.OnClickListener()

                                       {
                                           public void onClick(View v) {
                                               if (flag) {
                                                   imageButton.setImageResource(R.drawable.playbutton);
                                                   if (mediaPlayer.isPlaying())
                                                       mediaPlayer.pause();
                                                   flag=true;
                                               }
                                               else {
                                                   imageButton.setImageResource(R.drawable.pausebutton);
                                                   if (intialStage)
                                                       new Player()
                                                               .execute("https://super-ego.info"+audioLink);
                                                   else {
                                                       if (!mediaPlayer.isPlaying())
                                                           mediaPlayer.start();
                                                   }
                                                   flag = !flag;
                                               }
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
    class Player extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progress;

        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            Boolean prepared;
            try {

                mediaPlayer.setDataSource(params[0]);

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        intialStage = true;
                        flag=false;
                        imageButton.setBackgroundResource(R.drawable.playbutton);
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.prepare();
                prepared = true;
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                Log.d("IllegarArgument", e.getMessage());
                prepared = false;
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Log.d("Prepared", "//" + result);
            mediaPlayer.start();

            intialStage = false;
        }


    }
    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public class GetAudioGoals extends AsyncTask<Void, Void, AudioGoal> {


        private final String value;
        private String goal_id;
        public GetAudioGoals(String token,String goalAudioId) {
            this.value = token;
            this.goal_id=goalAudioId;
        }

        @Override
        protected AudioGoal doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            final String baseurl = RestUrl.BASE_URL + "v1/training/get-training?id=2&uri=purpose&goal_id=" +goal_id+ "&access-token=" + value;
            HttpURLConnection httpURLConnection;
            BufferedReader bufferedReader = null;
            StringBuilder stringBuilder = null;
            String line = null;
            URL url = null;
            String response = null;
            try {
                url = new URL(baseurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + '\n');
                }
                // Response from server after login process will be stored in response variable.
                response = stringBuilder.toString();

                // You can perform UI operations here

                Gson gson = new Gson();
                return gson.fromJson(response, AudioGoal.class);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final AudioGoal token) {
            if (token != null) {
                super.onPostExecute(token);

            }

            //  }

        }

    }

}
