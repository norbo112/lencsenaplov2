package com.norbo.project.lencsenaplov2.ui.rcviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.LencseListItemBinding;

import java.util.List;

public class LencseAdapter extends RecyclerView.Adapter<LencseAdapter.ViewHolder> {

    private List<Lencse> lencseList;
    private Context context;

    public LencseAdapter(List<Lencse> lencseList, Context context) {
        this.lencseList = lencseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LencseListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.lencse_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lencseList.get(position));
    }

    @Override
    public int getItemCount() {
        return lencseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final LencseListItemBinding binding;

        public ViewHolder(LencseListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Lencse currentLencse) {
            binding.setLencseadat(currentLencse);
            binding.executePendingBindings();
        }
    }
}
