package super_ego.info.masterkit.adapter;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.List;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.fragments.learning_fragment.LearningStepFragment;
import super_ego.info.masterkit.model.ListItemVideoStep;

/**
 * Created by Andrey on 26.10.2016.
 */

public class RecycleViewAdapterLearningStep extends RecyclerView.Adapter<RecycleViewAdapterLearningStep.ViewHolder> {
    private static final String API_KEY = "AIzaSyDVDCkSRgCsB3vnv8_s5FWeC1lOAYvtBZo";
    private List<ListItemVideoStep> records;
    private LearningStepFragment mContext;


    public RecycleViewAdapterLearningStep(List<ListItemVideoStep> records, LearningStepFragment mContext) {
        this.records = records;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.you_tube_api_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListItemVideoStep record = records.get(position);
        holder.textViewVideoStep.setText(record.getVideoStep());
        holder.textViewNameOfStep.setText(record.getNameofStep());
        holder.textViewVideoQuestion.setText(record.getQuestion());
        holder.editTextAnswer.setText("Какой то правильный ответ");
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
                FragmentTransaction transaction = mContext.getFragmentManager().beginTransaction();
                transaction.replace(R.id.youtube_layout_learning_step_video, youTubePlayerFragment).commit();
                Log.d("FRAGMENT", holder.getAdapterPosition()+"");

                youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {

                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                        if (!wasRestored) {
                            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                            player.loadVideo(record.getYoutubelink());
//                            player.play();
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                        String errorMessage = error.toString();
                        Log.d("errorMessage:", errorMessage);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewVideoStep;
        private TextView textViewNameOfStep;
        private TextView textViewVideoQuestion;
        private EditText editTextAnswer;
        private FrameLayout frameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewVideoStep = (TextView) itemView.findViewById(R.id.textViewVideoStep);
            textViewNameOfStep = (TextView) itemView.findViewById(R.id.textViewNameofStep);
            textViewVideoQuestion = (TextView) itemView.findViewById(R.id.textViewQuestionFromVideo);
            editTextAnswer = (EditText) itemView.findViewById(R.id.editTextAnswerVideo);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.youtube_layout_learning_step_video);
        }
    }
}
