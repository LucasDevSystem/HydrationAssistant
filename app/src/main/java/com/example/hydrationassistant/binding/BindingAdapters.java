package com.example.hydrationassistant.binding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hydrationassistant.CoposAdapter;
import com.example.hydrationassistant.modelo.Copo;

import java.util.List;

public class BindingAdapters {

    @BindingAdapter("setAdapter")
    public static void setRecyclerViewAdapter(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("submitList")
    public static void submitList(@NonNull RecyclerView recyclerView, List<Copo> recados) {
        ((CoposAdapter)recyclerView.getAdapter()).atualizar(recados);
    }
}
