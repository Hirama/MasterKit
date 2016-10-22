package super_ego.info.masterkit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import super_ego.info.masterkit.login.LoginActivity;
import super_ego.info.masterkit.model.GoalResultPOJO;
import super_ego.info.masterkit.model.UserPOJO;
import super_ego.info.masterkit.util.RestUrl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private UserGetData userDataTask = null;
    private UserPOJO newUser;
    public FragmentManager fragmentManager = getSupportFragmentManager();
    public static DrawerLayout drawer;
    public static Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        LearningFragment learningFragment = new LearningFragment();
        fragmentManager.beginTransaction().add(R.id.frgmContMain, learningFragment).commit();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        String value = "";

        SharedPreferences mPrefs = getSharedPreferences("data", MODE_PRIVATE);
        if (mPrefs.contains("user")) {
            value = mPrefs.getString("user", "");
            //Log.d("******",value);
            userDataTask = new UserGetData(value);
            Bundle bundle = new Bundle();
            bundle.putString("token", value);
            learningFragment.setArguments(bundle);
            GetGoals getGoals = new GetGoals(value);
            getGoals.execute();
//            SetGoalToAPI setGoalToAPI= new SetGoalToAPI(value,"Checked","Cheked");
//            Log.d("***********",setGoalToAPI.sendGoalsToApi());
        }

        if (extras != null) {
            value = extras.getString("token");
            //The key argument here must match that used in the other activity
            userDataTask = new UserGetData(value);
            Bundle bundle = new Bundle();
            bundle.putString("token", value);
            learningFragment.setArguments(bundle);
            GetGoals getGoals = new GetGoals(value);
            getGoals.execute();
//            SetGoalToAPI setGoalToAPI= new SetGoalToAPI(value,"Checked","Cheked");
//            Log.d("***********",setGoalToAPI.sendGoalsToApi());
        }

    }

    private void setUserName() {
        try {
            newUser = userDataTask.execute((Void) null).get();
            TextView fnameANDlname = (TextView) findViewById(R.id.user_name);
            fnameANDlname.setText(newUser.getFirst_name() + " " + newUser.getLast_name());
            TextView level = (TextView) findViewById(R.id.user_level_text);
            level.setText(String.valueOf(newUser.getLevel()));
            TextView learningLevel = (TextView) findViewById(R.id.learning_level_text);
            learningLevel.setText(newUser.getStep() + "/" + newUser.getSteps_count());
            ProgressBar learningProgressBar = (ProgressBar) findViewById(R.id.progressBar);

            learningProgressBar.setProgress(Integer.valueOf(newUser.getConsciousness()));
//            ImageView setAvatar = (ImageView) findViewById(R.id.user_photo);
            CircularImageView circularImageView = (CircularImageView) findViewById(R.id.user_photo);

            // Set Border
            //            circularImageView.setBorderColor(getResources().getColor(R.color.colorPrimary));
            circularImageView.setBorderWidth(1);
            // Add Shadow with default param
            circularImageView.addShadow();
            // or with custom param
            circularImageView.setShadowRadius(10);
            circularImageView.setShadowColor(Color.TRANSPARENT);

            DownloadImageTask downloadImageTask = new DownloadImageTask(circularImageView, newUser.getImage());
            downloadImageTask.execute();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void logout(View v) {
        Button logout = (Button) findViewById(R.id.logout);
        assert logout != null;
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences myPrefs = getSharedPreferences("data",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Вы действительно хотите закрыть приложение?")
//                    .setCancelable(false)
//                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            MainActivity.this.finish();
//                        }
//                    })
//                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    });
//            AlertDialog alert = builder.create();
//            alert.show();
//        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Update drawer info about user :D
        setUserName();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.learning) {
            LearningFragment learningFragment = new LearningFragment();
            fragmentManager.beginTransaction().replace(R.id.frgmContMain, learningFragment).addToBackStack(null).commit();

        } else if (id == R.id.training) {
            TrainerFragment trainerFragment = new TrainerFragment();
            fragmentManager.beginTransaction().replace(R.id.frgmContMain, trainerFragment).commit();

        } else if (id == R.id.master_kit) {
            MasterKitFragment masterKitFragment = new MasterKitFragment();
            fragmentManager.beginTransaction().replace(R.id.frgmContMain, masterKitFragment).commit();

        } else if (id == R.id.goals) {
            GoalsFragment goalsFragment = new GoalsFragment();
            fragmentManager.beginTransaction().replace(R.id.frgmContMain, goalsFragment).commit();

        }
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserGetData extends AsyncTask<Void, Void, UserPOJO> {


        private final String value;


        UserGetData(String token) {
            value = token;
        }

        @Override
        protected UserPOJO doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final String baseurl = RestUrl.BASE_URL + "v1/user/get-user-data" + "?access-token=" + value;
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
                return gson.fromJson(response, UserPOJO.class);
            } catch (IOException e) {
                return null;
            }


        }

        @Override
        protected void onPostExecute(final UserPOJO token) {
            if (token != null) {
                super.onPostExecute(token);
                Gson gson = new Gson();
                String json = gson.toJson(token);
                SharedPreferences mPrefs = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("userInfo", json);
                prefsEditor.commit();


            }

        }

        @Override
        protected void onCancelled() {

        }
    }


    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String urlForAvatar;

        public DownloadImageTask(ImageView bmImage, String imageUrl) {
            this.bmImage = bmImage;
            this.urlForAvatar = imageUrl;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urlForAvatar).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public class GetGoals extends AsyncTask<Void, Void, GoalResultPOJO> {


        private final String value;

        public GetGoals(String token) {
            this.value = token;

        }

        @Override
        protected GoalResultPOJO doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final String baseurl = RestUrl.BASE_URL + "v1/user/get-goals" + "?access-token=" + value;
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
                return gson.fromJson(response, GoalResultPOJO.class);
            } catch (IOException e) {
                return null;
            }


        }

        @Override
        protected void onPostExecute(final GoalResultPOJO token) {
            if (token != null) {
                super.onPostExecute(token);
                Gson gson = new Gson();
                String json = gson.toJson(token);
                SharedPreferences mPrefs = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("goals", json);
                prefsEditor.commit();

            }

            //  }

        }
        @Override
        protected void onCancelled () {

        }
    }


}
