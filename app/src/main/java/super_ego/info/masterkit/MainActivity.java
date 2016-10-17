package super_ego.info.masterkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import super_ego.info.masterkit.login.LoginActivity;
import super_ego.info.masterkit.model.UserPOJO;
import super_ego.info.masterkit.util.RestUrl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private UserGetData userDataTask = null;
    private UserPOJO newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LearningFragment learningFragment = new LearningFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frgmCont, learningFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        String value = "";

        SharedPreferences mPrefs = getSharedPreferences("", MODE_PRIVATE);
        if (mPrefs.contains("user")) {
            value = (mPrefs.getString("user", ""));
            //Log.d("******",value);
            userDataTask = new UserGetData(value);
        }

        if (extras != null) {
            value = extras.getString("token");
            //The key argument here must match that used in the other activity
            userDataTask = new UserGetData(value);

        }
        //   }


    }

    private void setUserName() {
        try {
            newUser = userDataTask.execute((Void) null).get();
            TextView fnameANDlname = (TextView) findViewById(R.id.user_name);
            fnameANDlname.setText(newUser.getFirst_name() + " " + newUser.getLast_name());
            TextView level = (TextView) findViewById(R.id.user_level_text);
            level.setText(newUser.getLevel().toString());
            TextView learningLevel = (TextView) findViewById(R.id.learning_level_text);
            learningLevel.setText(newUser.getStep() + "/" + newUser.getSteps_count());
            ProgressBar learningProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            NumberFormat format = NumberFormat.getCurrencyInstance();
            Number number = format.parse(newUser.getConsciousness());
            learningProgressBar.setProgress(number.intValue());
            Log.d("*******", newUser.getImage());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void logout(View v) {
        Button logout = (Button) findViewById(R.id.logout);
        assert logout != null;
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences myPrefs = getSharedPreferences("",
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Вы действительно хотите закрыть приложение?")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frgmCont, learningFragment).commit();


        } else if (id == R.id.training) {

        } else if (id == R.id.master_kit) {

        } else if (id == R.id.goals) {
            GoalsFragment goalsFragment = new GoalsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frgmCont, goalsFragment).commit();

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

                // Error
            }


        }


        protected void onPostExecute(final UserPOJO token) {
            if (token != null) {

                super.onPostExecute(token);
            }

        }

        @Override
        protected void onCancelled() {

        }
    }
}
