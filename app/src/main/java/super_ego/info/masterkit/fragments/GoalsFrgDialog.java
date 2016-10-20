package super_ego.info.masterkit.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.RESTapi.SetGoalToAPI;
import super_ego.info.masterkit.util.RestUrl;

import static android.content.Context.MODE_PRIVATE;
import static super_ego.info.masterkit.R.id.editText;

public class GoalsFrgDialog extends DialogFragment implements
        DialogInterface.OnClickListener {
   // EditText editText;
//    FrgGoalsMoney frgGoalsMoney = new FrgGoalsMoney();
//    FrgGoalsHelth frgGoalsHelth = new FrgGoalsHelth();
//    FrgGoalsLove frgGoalsLove = new FrgGoalsLove();
//    FrgGoalsTarget frgGoalsTarget = new FrgGoalsTarget();
    private View form=null;
    EditText editText;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        form= getActivity().getLayoutInflater()
                .inflate(R.layout.goal_dialog, null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        editText = (EditText) form.findViewById(R.id.editText);

        return(builder.setTitle("Введите Вашу Цель").setView(form)
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton(android.R.string.cancel, null).create());

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String token="";
        SharedPreferences mPrefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (mPrefs.contains("user")) {
            token=mPrefs.getString("user","");
        }
          List<Fragment> fragments=getActivity().getSupportFragmentManager().getFragments();
          for(Fragment i:fragments){
              if(i.getUserVisibleHint()) {
                  if(i.getClass().getName().contains("Money")){
                      Log.d("**********", "you are in "+editText.getText().toString());
                      SetGoalToAPI setMoneyGoal= new SetGoalToAPI(token,editText.getText().toString(),"money");
                      setMoneyGoal.execute();
                  }else if(i.getClass().getName().contains("Love")){
                      SetGoalToAPI setMoneyGoal= new SetGoalToAPI(token,editText.getText().toString(),"relationship");
                      setMoneyGoal.execute();
                  }else if(i.getClass().getName().contains("Target")){
                      SetGoalToAPI setMoneyGoal= new SetGoalToAPI(token,editText.getText().toString(),"destiny");
                      setMoneyGoal.execute();
                  }else if(i.getClass().getName().contains("Helth")){
                      SetGoalToAPI setMoneyGoal= new SetGoalToAPI(token,editText.getText().toString(),"body");
                      setMoneyGoal.execute();
                  }

              }
          }
    }

    @Override
    public void onDismiss(DialogInterface unused) {
        super.onDismiss(unused);
    }

    @Override
    public void onCancel(DialogInterface unused) {
        super.onCancel(unused);
    }

}
