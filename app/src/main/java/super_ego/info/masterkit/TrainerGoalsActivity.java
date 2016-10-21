package super_ego.info.masterkit;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import super_ego.info.masterkit.fragments.trainer_goal_fragment.TrainerGoalsMainFragment;

public class TrainerGoalsActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_goals);
        TrainerGoalsMainFragment trainerGoalsMainFragment =new TrainerGoalsMainFragment();
        fragmentManager.beginTransaction().add(R.id.frgmContTrainerGoals, trainerGoalsMainFragment).commit();
    }
}
