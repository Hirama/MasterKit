package super_ego.info.masterkit.RESTapi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import super_ego.info.masterkit.util.RestUrl;

/**
 * Created by Lion on 20.10.2016.
 */

public class SetGoalToAPI extends AsyncTask<Void, Void, String> {
    private String token;
    private String userGoal;
    private String type;

    public SetGoalToAPI(String tokenValue, String goal, String typeOfGoal) {
        this.token = tokenValue;
        this.userGoal = goal;
        this.type = typeOfGoal;
    }

    @Override
    protected String doInBackground(Void... params) {
        final String baseurl = RestUrl.BASE_URL + "v1/user/set-goal?access-token=" + token;
        HttpURLConnection connection;
        OutputStreamWriter request = null;
        URL url = null;
        String response = null;
        String parameters = "goal=" + userGoal + "&type=" + type;
        try {
            url = new URL(baseurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            request = new OutputStreamWriter(connection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line = "";
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                // Response from server after login process will be stored in response variable.
                response = sb.toString();

                // You can perform UI operations here
                isr.close();
                reader.close();
            } else {
                response = "";

            }

            return response;
        } catch (IOException e) {
            return null;
            // Error
        }

    }
    @Override
    protected void onPostExecute(final String response) {
        Log.d("**********************",response);
    }
}

