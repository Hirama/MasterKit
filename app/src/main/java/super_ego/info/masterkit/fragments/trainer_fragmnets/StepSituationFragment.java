package super_ego.info.masterkit.fragments.trainer_fragmnets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import super_ego.info.masterkit.MainActivity;
import super_ego.info.masterkit.R;

import super_ego.info.masterkit.model.TokenPOJO;
import super_ego.info.masterkit.util.RestUrl;

import static android.R.attr.id;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Andrey on 24.10.2016.
 */

public class StepSituationFragment extends Fragment {
    private TextView situationName;
    private Button nextButton;
    private EditText userAnswer;
    private TextView situationDescription;
    ImageButton imageButtonPlayer;
   // private MediaPlayer mediaPlayer;
    boolean flag = false;
    private Integer counter=0;
    private boolean intialStage = true;
    private JSONObject setTrainingSituationData = null;
    private String typeOfTraining;
    public StepSituationFragment(String type) {
       this.typeOfTraining=type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_step_situation, container, false);
        getActivity().setTitle("Тренажёр");
        String token="";
        SharedPreferences mPrefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (mPrefs.contains("user")) {
            token = mPrefs.getString("user", "");
        }
        GetTrainingSituation getTrainingSituation= new GetTrainingSituation(token,"","quality");
        JSONObject getTrainingSituationData=null;
        try {
            getTrainingSituationData=getTrainingSituation.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int finishTraining=0;
        situationName = (TextView) rootView.findViewById(R.id.textViewTopStepSituationFragment);
        try {
            situationName.setText(getTrainingSituationData.getJSONObject("data").getString("title"));
            situationName = (TextView) rootView.findViewById(R.id.textViewTopStepSituationFragmentText);
            situationName.setText(getTrainingSituationData.getJSONObject("data").getString("text"));
            imageButtonPlayer =(ImageButton) rootView.findViewById(R.id.imgBtnPlaySituation);
            finishTraining=getTrainingSituationData.getJSONObject("data").getJSONArray("finish").getInt(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        imageButtonPlayer.setVisibility(View.INVISIBLE);
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        userAnswer=(EditText) rootView.findViewById(R.id.editTextStepSituationAnswer);
        //  String answer=userAnswer.getText().toString();
        nextButton = (Button) rootView.findViewById(R.id.buttonNextSituationFragment);
        counter+=1;

        final String finalToken = token;
        setTrainingSituationData = getTrainingSituationData;
        final int finalFinishTraining = finishTraining;
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click


                String answer=userAnswer.getText().toString();
                boolean finishPointCheck=false;
                if(counter==Integer.valueOf(finalFinishTraining)){
                    finishPointCheck=true;
                }else {
                    //for(int i=2; i<=Integer.valueOf(finalGetTrainingSituationData.getData().getFinish().get(0));i++) {
                    if (!answer.equals("")) {
                        counter+=1;
                        SetTrainingSituation setTrainingSituation = new SetTrainingSituation(finalToken, typeOfTraining,
                                String.valueOf(counter), setTrainingSituationData,answer);
                        //userAnswer.setText("");
                        try {
                            setTrainingSituationData = setTrainingSituation.execute().get();
                            JSONObject data = setTrainingSituationData.getJSONObject("data");
                            situationName = (TextView) rootView.findViewById(R.id.textViewTopStepSituationFragment);
                            situationName.setText(data.getString("title"));
                            situationName = (TextView) rootView.findViewById(R.id.textViewTopStepSituationFragmentText);
                            String replaceBreaks = data.getString("text");
                            replaceBreaks = replaceBreaks.replaceAll("\n", "");
                            replaceBreaks = replaceBreaks.trim().replaceAll(" +", " ");
                            situationName.setText(Html.fromHtml(replaceBreaks));
                            if(data.getJSONObject("training_form").get("input").getClass().equals(Boolean.class)){
                                userAnswer.setVisibility(View.INVISIBLE);
                            }else{
                                userAnswer.setVisibility(View.VISIBLE);
                            }

                            if(finishPointCheck){

                                //Put image of finish here
                            }
                            if (data.get("mp3").getClass().equals(String.class)) {
                                final MediaPlayer mediaPlayer = new MediaPlayer();
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                imageButtonPlayer.setVisibility(View.VISIBLE);
                                final String audioTraining = data.getString("mp3");
                                //Log.d("********",finalSetTrainingSituationData.getData().getMp3());
                                imageButtonPlayer.setOnClickListener(new View.OnClickListener()

                                                                     {
                                                                         public void onClick(View v) {
                                                                             if (flag) {
                                                                                 imageButtonPlayer.setImageResource(R.drawable.playbutton);
                                                                                 if (mediaPlayer.isPlaying())
                                                                                     mediaPlayer.pause();
                                                                                 flag = true;
                                                                             } else {
                                                                                 imageButtonPlayer.setImageResource(R.drawable.pausebutton);
                                                                                 Log.d("*********", audioTraining);
                                                                                 if (intialStage)
                                                                                     new Player(mediaPlayer)
                                                                                             .execute("https://super-ego.info" + audioTraining);

                                                                                 else {
                                                                                     if (!mediaPlayer.isPlaying())
                                                                                         mediaPlayer.start();
                                                                                 }
                                                                                 flag = !flag;
                                                                             }
                                                                         }
                                                                     }

                                );
                            }else{
                                imageButtonPlayer.setVisibility(View.INVISIBLE);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //}
                    }
                }


            }
        });

        return rootView;
    }
    class Player extends AsyncTask<String, Void, Boolean> {
        private MediaPlayer mediaPlayer;
        public Player(MediaPlayer mediaPlayer){
            this.mediaPlayer=mediaPlayer;
        }
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
                        imageButtonPlayer.setBackgroundResource(R.drawable.playbutton);
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
//    @Override
//    public void onPause() {
//        // TODO Auto-generated method stub
//        super.onPause();
//        if (mediaPlayer != null) {
//            mediaPlayer.reset();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }
    public class GetTrainingSituation extends AsyncTask<Void, Void, JSONObject> {

        private final String value;
        private final String type;
        private String goal_id;

        public GetTrainingSituation(String token,String goalAudioId,String typeSituation) {
            this.value = token;
            this.goal_id=goalAudioId;
            this.type=typeSituation;


        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            final String baseurl = RestUrl.BASE_URL + "v1/training/get-training?id=1&uri="+type+"&access-token=" + value;
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

                return new JSONObject(response);
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final JSONObject token) {
            if (token != null) {
                super.onPostExecute(token);
            }
            //  }
        }
    }

    public class SetTrainingSituation extends AsyncTask<Void, Void, JSONObject> {

        private final String value;
        private final String type;
        private final String situationID;
        private String goal_id;
        private JSONObject getTrainingData;
        private String mInputText;
        public SetTrainingSituation(String token, String typeSituation, String situation, JSONObject getTrainingSituationData, String inputText) {
            this.value = token;
            this.type = typeSituation;
            this.situationID = situation;
            this.getTrainingData = getTrainingSituationData;
            this.mInputText=inputText;
        }
        @Override
        protected JSONObject doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            // if(getTrainingData.getData().getTrainingForm().getFields()){
            final String baseurl = RestUrl.BASE_URL + "v1/training/get-training?access-token=" + value + "&uri=" + type + "&id=" + situationID;
            HttpURLConnection connection;
            OutputStreamWriter request = null;
            URL url = null;
            String response = null;
            String parameters =null;
            //"Training[quality]=" + "asdasda";


            try {
                JSONObject trainingForm=getTrainingData.getJSONObject("data").getJSONObject("training_form");
                if(!trainingForm.get("input").getClass().equals(Boolean.class)){
                    parameters="Training["+trainingForm.getJSONObject("input").getString("name")+"]="+mInputText;
                    Log.d(">>>>>>>>>",parameters);
                }
                Log.d(">>>>>>>>>",trainingForm.getString("id"));
//                    for(int i=0;i<=Integer.valueOf(getTrainingData.getJSONObject("data").getString("id"));) {
                if (!trainingForm.get("fields").getClass().equals(Boolean.class)) {
                    JSONArray fields=trainingForm.getJSONArray("fields");
                    for (int i=0; i < fields.length(); i++) {
                        JSONObject oneObject = fields.getJSONObject(i);
                        if(parameters!=null) {
                            parameters = parameters.concat("&Training[" + oneObject.getString("name") + "]=" + oneObject.getString("value"));
                        }else{
                            parameters ="Training[" + oneObject.getString("name") + "]=" + oneObject.getString("value");
                        }

                    }
                }
                // }
                Log.d("*******",parameters);
                url = new URL(baseurl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");

                request = new OutputStreamWriter(connection.getOutputStream());
                request.write(parameters);
                request.flush();
                request.close();
                String line = "";
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                // Response from server after login process will be stored in response variable.
                response = sb.toString();
                Log.d("<<<<<<<<<<<<<<<<<<", response);

                // You can perform UI operations here
                isr.close();
                reader.close();

                return new JSONObject(response);
            } catch (IOException e) {
                Log.d("********", "sfsdfdssdfsdfsf");
                return null;
                // Error
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }



        @Override
        protected void onPostExecute(final JSONObject token) {
            if (token != null) {
                super.onPostExecute(token);

            }
        }
    }



}
