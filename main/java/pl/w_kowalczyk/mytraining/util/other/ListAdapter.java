package pl.w_kowalczyk.mytraining.util.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.model.HistoryModel;

public class ListAdapter extends RecyclerView.Adapter {
    private final List<HistoryModel> historyModels;
    private final OnClickListener onClickListener;
    public ListAdapter(List<HistoryModel> historyModels, OnClickListener onClickListener) {
        this.historyModels = historyModels;
        this.onClickListener = onClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view, onClickListener);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HistoryModel historyModel = historyModels.get(position);
        ((ListViewHolder) holder).bindView(historyModel,position);
    }
    @Override
    public int getItemCount() {
        return historyModels.size();
    }
    public interface OnClickListener{
        void onClicked(HistoryModel historyModel, int position);
    }
}
