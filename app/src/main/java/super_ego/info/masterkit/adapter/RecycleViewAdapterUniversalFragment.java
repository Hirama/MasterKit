package super_ego.info.masterkit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.model.TempModel;

/**
 * Created by Andrey on 19.10.2016.
 */

public class RecycleViewAdapterUniversalFragment  extends RecyclerView.Adapter<RecycleViewAdapterUniversalFragment.ViewHolder> {
    private List<TempModel> records;

    public RecycleViewAdapterUniversalFragment(List<TempModel> records) {
        this.records = records;
    }

    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public RecycleViewAdapterUniversalFragment.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.situation_list_item_fragment, viewGroup, false);
        return new RecycleViewAdapterUniversalFragment.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterUniversalFragment.ViewHolder holder, int position) {
        TempModel record = records.get(position);
        holder.textView_description_step.setText(record.getName());
        holder.imageView.setImageResource(record.getImageId());
    }


    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
//    @Override
//    public void onBindViewHolder(RecycleViewAdapterSituationFragment.ViewHolder viewHolder, int i) {
//        MaterialsPOJO record = records.get(i);
//        viewHolder.textView_desciption_step.setText(record.getTitle());
//        String stepNumber = "ШАГ " + record.getStep().toString();
//        viewHolder.textView_step.setText(stepNumber);
//        if (Objects.equals(record.getStatus(), "locked")) {
//            viewHolder.imageButton.setImageResource(R.drawable.ic_lock_outline_black_24dp);
//        } else if (Objects.equals(record.getStatus(), "active")) {
//            viewHolder.imageButton.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
//        } else if (Objects.equals(record.getStatus(), "passed")) {
//            viewHolder.imageButton.setImageResource(R.drawable.ic_done_black_24dp);
//        }
//    }
    @Override
    public int getItemCount() {
        return records.size();
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_description_step;
        private ImageView imageView;
        private ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_situation_item_fragment);
            textView_description_step = (TextView) itemView.findViewById(R.id.textView_situation_item_list_fragment);
        }
    }
}
