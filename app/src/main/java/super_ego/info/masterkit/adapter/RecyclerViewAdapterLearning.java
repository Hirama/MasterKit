package super_ego.info.masterkit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import super_ego.info.masterkit.R;
import super_ego.info.masterkit.model.MaterialsPOJO;

/**
 * Created by Andrey on 18.10.2016.
 */

public class RecyclerViewAdapterLearning extends RecyclerView.Adapter<RecyclerViewAdapterLearning.ViewHolder> {
    private List<MaterialsPOJO> records;

    public RecyclerViewAdapterLearning(List<MaterialsPOJO> records) {
        this.records = records;
    }

    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.learning_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MaterialsPOJO record = records.get(i);
        viewHolder.textView_desciption_step.setText(record.getTitle());
        viewHolder.textView_step.setText("ШАГ " + String.valueOf(record.getStep()));
        if (Objects.equals(record.getStatus(), "locked")) {
            viewHolder.imageButton.setImageResource(R.drawable.ic_lock_outline_black_24dp);
        } else if (Objects.equals(record.getStatus(), "active")) {
            viewHolder.imageButton.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
        } else if (Objects.equals(record.getStatus(), "passed")) {
            viewHolder.imageButton.setImageResource(R.drawable.ic_done_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_desciption_step;
        private TextView textView_step;
        private ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_step = (TextView) itemView.findViewById(R.id.textView_step);
            textView_desciption_step = (TextView) itemView.findViewById(R.id.textView_desciption_step);
            imageButton = (ImageButton) itemView.findViewById(R.id.learning_step_status_image_button);
        }
    }
}
