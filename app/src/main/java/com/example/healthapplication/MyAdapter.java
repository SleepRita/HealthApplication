package com.example.healthapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VHolder> {
    private List<String> names;
    private List<String> cards;
    private List<String> diseases;

    public MyAdapter(List<String> names, List<String> cards, List<String> diseases) {
        this.names = names;
        this.cards = cards;
        this.diseases = diseases;

    }

    @NonNull
    @Override
    public MyAdapter.VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.patients_list, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.VHolder holder, int position) {
        String name;
        String card;
        String disease;

        if (position < names.size()){
            name = names.get(position);
        }
        else{
            name = "-";
        }
        if (position < cards.size()) {
            card = cards.get(position);
        } else {
            card = "-";
        }
        if (position < diseases.size()) {
            disease = diseases.get(position);
        }else{
            disease = "-";
        }

        holder.name_p.setText(name);
        holder.card_p.setText(card);
        holder.disease_p.setText(disease);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    static class VHolder extends RecyclerView.ViewHolder {
        TextView name_p;
        TextView card_p;
        TextView disease_p;

        VHolder(View view) {
            super(view);
            name_p = view.findViewById(R.id.patient_name);
            card_p = view.findViewById(R.id.patient_card);
            disease_p = view.findViewById(R.id.patient_diseases);
        }
    }
}
