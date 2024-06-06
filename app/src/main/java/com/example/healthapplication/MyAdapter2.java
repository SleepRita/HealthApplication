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

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.VHolder> {
    private List<String> names;
    private List<String> descripts;

    public MyAdapter2(List<String> names, List<String> descripts) {
        this.names = names;
        this.descripts = descripts;

    }

    @NonNull
    @Override
    public MyAdapter2.VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.diseases_list, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.VHolder holder, int position) {
        String name;
        String descript;

        if (position < names.size()){
            name = names.get(position);
        }
        else{
            name = "-";
        }
        if (position < descripts.size()) {
            descript = descripts.get(position);
        } else {
            descript = "-";
        }

        holder.name_p.setText(name);
        holder.descript_p.setText(descript);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    static class VHolder extends RecyclerView.ViewHolder {
        TextView name_p;
        TextView descript_p;

        VHolder(View view) {
            super(view);
            name_p = view.findViewById(R.id.disease_name);
            descript_p = view.findViewById(R.id.disease_description);
        }
    }
}
