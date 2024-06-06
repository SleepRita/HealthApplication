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

public class DoctorPatients extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MyAdapter adapter;

    ArrayList<String> fio;
    ArrayList<String> cards;
    ArrayList<String> diseases;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rView = view.findViewById(R.id.patients_list);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));

        fio = new ArrayList<>();
        cards = new ArrayList<>();
        diseases = new ArrayList<>();

        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String names;
                        String card;
                        String disease;
                        for (DocumentSnapshot doc : task.getResult()) {
                            boolean pat = doc.get("role").toString().equals("patient");
                            if (pat) {
                                names = doc.getData().get("name").toString() + " " +
                                        doc.getData().get("surname").toString() + " " +
                                        doc.getData().get("patronymic").toString();
                                card = doc.getData().get("card").toString();
                                disease = doc.getData().get("diseases").toString();

                                fio.add(names.toString());
                                cards.add(card.toString());
                                diseases.add(disease.toString());
                            }
                        }
                        MyAdapter adapter = new MyAdapter(fio, cards, diseases);
                        rView.setAdapter(adapter);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doctor_patients, container, false);
    }
}
