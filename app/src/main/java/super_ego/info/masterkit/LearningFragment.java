package super_ego.info.masterkit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;


import super_ego.info.masterkit.adapter.DividerItemDecoration;
import super_ego.info.masterkit.adapter.RecyclerViewAdapterLearning;
import super_ego.info.masterkit.model.LearingPlanPOJO;
import super_ego.info.masterkit.model.MaterialsPOJO;
import super_ego.info.masterkit.model.UserPOJO;
import super_ego.info.masterkit.util.RestUrl;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LearningFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LearningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningFragment extends Fragment {
    RecyclerView listView;
    String token;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public LearningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningFragment newInstance(String param1, String param2) {
        LearningFragment fragment = new LearningFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("data", 0);
        if (mPrefs.contains("user")) {
            token=mPrefs.getString("user", "");
        }else {
            token = getArguments().getString("token");
        }

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View liView = inflater.inflate(R.layout.fragment_learning, container, false);
        listView = (RecyclerView) liView.findViewById(R.id.list_view_recycle);
        getActivity().setTitle("Обучение");
//        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("data",MODE_PRIVATE);
//        if (mPrefs.contains("userInfo")) {
//            Gson gson = new Gson();
//            String json = mPrefs.getString("userInfo", "");
//            UserPOJO obj = gson.fromJson(json, UserPOJO.class);
//
//        }

//        ProgressBar learningProgressBar = (ProgressBar) listView.findViewById(R.id.progressBar);
//        learningProgressBar.setProgress(Integer.valueOf(getArguments().getString("progress")));
//
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        listView.setLayoutManager(mLayoutManager);
        listView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));

        List<MaterialsPOJO> records = null;
        try {
            records = populateRecords();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapterLearning(records);
        listView.setAdapter(mAdapter);
        return liView;
    }

    private List<MaterialsPOJO> populateRecords() throws ExecutionException, InterruptedException {
            GetLearningPlan plan = new GetLearningPlan(token);
            LearingPlanPOJO learningPlan=null;
            try {
                learningPlan=plan.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        return learningPlan.getResult();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }

    public class GetLearningPlan extends AsyncTask<Void, Void, LearingPlanPOJO> {


        private final String value;
        // private Activity mContex;

        GetLearningPlan(String token) {
            this.value = token;
            //this.mContex = contex;

        }

        @Override
        protected LearingPlanPOJO doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final String baseurl = RestUrl.BASE_URL + "v1/study/get-plan" + "?access-token=" + value;
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
                Gson gson = new Gson();
                return gson.fromJson(response, LearingPlanPOJO.class);
            } catch (IOException e) {
                return null;

                // Error
            }


        }

        @Override
        protected void onPostExecute(final LearingPlanPOJO learingPlanPOJO) {
            super.onPostExecute(learingPlanPOJO);
            Gson gson = new Gson();
            String json = gson.toJson(token);
            SharedPreferences mPrefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            prefsEditor.putString("materials", json);
            prefsEditor.commit();
        }

        @Override
        protected void onCancelled() {

        }
    }
}
