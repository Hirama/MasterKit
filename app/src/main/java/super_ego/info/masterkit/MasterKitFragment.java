package super_ego.info.masterkit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import super_ego.info.masterkit.adapter.ItemClickSupport;
import super_ego.info.masterkit.adapter.RecycleViewAdapterMasterKit;
import super_ego.info.masterkit.fragments.GoalYouTubePlayer;
import super_ego.info.masterkit.model.VideoDataPOJO;
import super_ego.info.masterkit.model.VideoModel;
import super_ego.info.masterkit.model.YouTubeVideoPOJO;
import super_ego.info.masterkit.util.RestUrl;

import static android.content.Context.MODE_PRIVATE;

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
        ImageView imageView = (ImageView) liView.findViewById(R.id.imageView);

        videoList = (RecyclerView) liView.findViewById(R.id.video_recycle_view);
        final TextView videoTitleMain = (TextView) liView.findViewById(R.id.textViewVideoTitleMain);
        TextView videoDateMain = (TextView) liView.findViewById(R.id.textViewVideoTimeStamp);
        getActivity().setTitle("Master Kit Live");


        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        videoList.setLayoutManager(mLayoutManager);
        String token = "";
        SharedPreferences mPrefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (mPrefs.contains("user")) {
            token = mPrefs.getString("user", "");
        }
        YouTubeVideoPOJO youTubeVideoPOJO = null;
        GetVideoInfo getVideoInfo = new GetVideoInfo(token);
        try {
            youTubeVideoPOJO = getVideoInfo.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final List<VideoModel> records = new ArrayList<>();
        List<VideoDataPOJO> videoDataPOJOs = youTubeVideoPOJO.getData().getHistory();

        final VideoDataPOJO videoDataPOJOLast = youTubeVideoPOJO.getData().getLast();

        videoTitleMain.setText(videoDataPOJOLast.getTitle());
        videoDateMain.setText(videoDataPOJOLast.getSubtitle());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = getActivity().getSupportFragmentManager();
                GoalYouTubePlayer goalYouTubePlayer = new GoalYouTubePlayer(videoDataPOJOLast.getVideo());

                fragManager.beginTransaction()
                        .replace(R.id.frgmContMain, goalYouTubePlayer)
                        .addToBackStack(null)
                        .commit();

            }
        });


        for (VideoDataPOJO i : videoDataPOJOs) {
            VideoModel videoModelhistory = new VideoModel();
            videoModelhistory.setDate(i.getSubtitle());
            videoModelhistory.setName(i.getTitle());
            videoModelhistory.setImage(R.mipmap.small_video);
            videoModelhistory.setVideoId(i.getVideo());
            records.add(videoModelhistory);
        }

        mAdapter = new RecycleViewAdapterMasterKit(records);
        ItemClickSupport.addTo(videoList)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Toast.makeText(getContext(), "Text!", Toast.LENGTH_SHORT).show();
                        Log.d("WOW SUCH WORK", String.valueOf(position));
                        FragmentManager fragManager = getActivity().getSupportFragmentManager();
                        VideoModel videoModel = records.get(position);
                        GoalYouTubePlayer goalYouTubePlayer = new GoalYouTubePlayer(videoModel.getVideoId());

                        fragManager.beginTransaction()
                                .replace(R.id.frgmContMain, goalYouTubePlayer)
                                .addToBackStack(null)
                                .commit();
                    }
                });
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

    public class GetVideoInfo extends AsyncTask<Void, Void, YouTubeVideoPOJO> {


        private final String value;


        GetVideoInfo(String token) {
            value = token;
        }

        @Override
        protected YouTubeVideoPOJO doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final String baseurl = RestUrl.BASE_URL + "v1/live/get-lives" + "?access-token=" + value;
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
                return gson.fromJson(response, YouTubeVideoPOJO.class);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final YouTubeVideoPOJO token) {
            if (token != null) {
                super.onPostExecute(token);
                Gson gson = new Gson();
                String json = gson.toJson(token);
                SharedPreferences mPrefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("video", json);
                prefsEditor.commit();

            }
        }

    }
}
