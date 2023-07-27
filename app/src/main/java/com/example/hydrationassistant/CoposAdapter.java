package com.example.hydrationassistant;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hydrationassistant.databinding.CopoItemBinding;
import com.example.hydrationassistant.databinding.CopoItemBindingImpl;
import com.example.hydrationassistant.modelo.Copo;

import java.util.Collections;
import java.util.List;


public class CoposAdapter extends RecyclerView.Adapter<CoposAdapter.CopoViewHolder> {
    private List<Copo> recados;
    private LifecycleOwner lifecycleOwner;
    private  CoposViewModel coposVm;

    public CoposAdapter(LifecycleOwner lifecycleOwner ,CoposViewModel coposVm) {
        this.recados = Collections.emptyList();
        this.lifecycleOwner = lifecycleOwner;
        this.coposVm = coposVm;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void atualizar(List<Copo> recados) {
        this.recados = recados;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CopoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CopoItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.copo_item, parent, false);
        CopoViewHolder holder = new CopoViewHolder((CopoItemBindingImpl) binding);

        holder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coposVm.onItemClick(holder.getAdapterPosition());
            }
        }));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CopoViewHolder holder, int position) {
        Copo r = recados.get(position);
        holder.bind(r);
    }

    @Override
    public int getItemCount() {
        return recados.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull CopoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.binding.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CopoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.binding.setLifecycleOwner(null);
    }

    static class CopoViewHolder extends RecyclerView.ViewHolder{
        CopoItemBindingImpl binding;

        public CopoViewHolder(@NonNull CopoItemBindingImpl binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(Copo r) {
            binding.setVm(new CopoViewModel(r));
            binding.executePendingBindings();
        }
    }
}
