package super_ego.info.masterkit.fragments.trainer_goal_fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import super_ego.info.masterkit.R;


public class TrainerGoalsActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_goals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String count = getIntent().getStringExtra("Target");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TrainerGoalsMainFragment trainerGoalsMainFragment = new TrainerGoalsMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("goalId", count);
        trainerGoalsMainFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frgmTrainerFrame, trainerGoalsMainFragment).commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }

    }
}
