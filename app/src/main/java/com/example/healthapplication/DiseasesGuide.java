package com.example.healthapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.healthapplication.MyAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class DiseasesGuide extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MyAdapter adapter;

    ArrayList<String> fio;
    ArrayList<String> desriptions;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rView = view.findViewById(R.id.diseases_list);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));

        fio = new ArrayList<>();
        desriptions = new ArrayList<>();

        db.collection("diseases")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String names;
                        String desription;
                        for (DocumentSnapshot doc : task.getResult()) {
                            names = doc.getData().get("name").toString();
                            desription = doc.getData().get("description").toString();

                            fio.add(names.toString());
                            desriptions.add(desription.toString());
                        }
                        MyAdapter2 adapter = new MyAdapter2(fio, desriptions);
                        rView.setAdapter(adapter);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.diseases_guide, container, false);
    }
}
