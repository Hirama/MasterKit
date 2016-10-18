package super_ego.info.masterkit.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import super_ego.info.masterkit.R;

/**
 * Created by Max Shalavin on 17.10.2016.
 */

public class FrgGoalsParent extends Fragment {
    ArrayAdapter<String> adapter;
    ListView listGoalsLove;
    List<String> list;

    protected List getGoalsServer() {
        list = new ArrayList<>();
        list.add("testlove1");
        list.add("testlove2");
        list.add("testlove3");
        return list;
    }

    public void setListGoalsServer(String[] massGoalsFromServer) {
        list.clear();
        Collections.addAll(this.list, massGoalsFromServer);

        adapter.notifyDataSetChanged();
    }

    protected void createAdapterAndListView(View v, int listGoal) {
        listGoalsLove = (ListView) v.findViewById(listGoal);
        adapter = new ArrayAdapter<String>(this.getContext(), R.layout.my_list_item, getGoalsServer());
        listGoalsLove.setAdapter(adapter);
    }
    static class TestViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        Button undoButton;

        public TestViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_for_goal, parent, false));
            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
            undoButton = (Button) itemView.findViewById(R.id.undo_button);
        }

    }
}


