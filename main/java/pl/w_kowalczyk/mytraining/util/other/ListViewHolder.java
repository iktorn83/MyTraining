package pl.w_kowalczyk.mytraining.util.other;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.model.HistoryModel;

public class ListViewHolder extends RecyclerView.ViewHolder {
    private ListAdapter.OnClickListener onClickListener;
    private View listItem;
    private TextView list_activity;
    private TextView list_date;
    private TextView list_heartrate;
    private TextView list_time;
    private TextView number_activity;
    public ListViewHolder(View view, ListAdapter.OnClickListener onClickListener) {
        super(view);
        listItem = view.findViewById(R.id.list_item);
        list_activity = view.findViewById(R.id.list_activity);
        list_date = view.findViewById(R.id.list_date);
        list_heartrate = view.findViewById(R.id.list_heartrate);
        list_time = view.findViewById(R.id.list_time);
        number_activity = view.findViewById(R.id.number_activity);
        this.onClickListener = onClickListener;
    }
    public void bindView(HistoryModel historyModel, int position) {
        list_activity.setText(historyModel.activity);
        list_date.setText(historyModel.date);
        list_heartrate.setText(historyModel.heartrate);
        list_time.setText(historyModel.time);
        number_activity.setText(String.valueOf(position+1));
        listItem.setOnClickListener(
                v -> onClickListener.onClicked(historyModel,position));
    }
}
