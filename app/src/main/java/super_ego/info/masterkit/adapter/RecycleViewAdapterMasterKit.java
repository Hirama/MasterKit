package super_ego.info.masterkit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.model.VideoModel;

/**
 * Created by Andrey on 20.10.2016.
 */

public class RecycleViewAdapterMasterKit extends RecyclerView.Adapter<RecycleViewAdapterMasterKit.ViewHolder> {

    private List<VideoModel> records;

    public RecycleViewAdapterMasterKit(List<VideoModel> records) {
        this.records = records;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_list_item_master_kit, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterMasterKit.ViewHolder holder, int i) {
        VideoModel videoModel = records.get(i);
        holder.textView_date.setText(videoModel.getDate());
        holder.textView_name.setText(videoModel.getName());
        holder.imageView.setImageResource(videoModel.getImage());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_date;
        private TextView textView_name;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_date = (TextView) itemView.findViewById(R.id.textViewDateVideo);
            textView_name = (TextView) itemView.findViewById(R.id.textViewVideoName);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewVideoImage);

        }
    }
}
