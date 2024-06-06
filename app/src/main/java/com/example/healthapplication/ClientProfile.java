package com.example.healthapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ClientProfile extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean isP;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        EditText s = v.findViewById(R.id.edit_surname);
        EditText n = v.findViewById(R.id.edit_name);
        EditText p = v.findViewById(R.id.edit_patronymic);
        EditText c = v.findViewById(R.id.edit_card);

        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail().toString();

        db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String title;
                        for (DocumentSnapshot doc : task.getResult()) {
                            title = doc.get("surname").toString();
                            s.setText(title);
                        }
                        for (DocumentSnapshot doc : task.getResult()) {
                            title = doc.get("name").toString();
                            n.setText(title);
                        }
                        for (DocumentSnapshot doc : task.getResult()) {
                            title = doc.get("patronymic").toString();
                            p.setText(title);
                        }
                        for (DocumentSnapshot doc : task.getResult()) {
                            isP = doc.get("role").toString().equals("patient");
                        }
                        if (isP) {
                            for (DocumentSnapshot doc : task.getResult()) {
                                title = doc.get("card").toString();
                                c.setText(title);
                            }
                        }
                        else {
                            v.findViewById(R.id.text_card).setVisibility(View.INVISIBLE);
                            c.setVisibility(View.INVISIBLE);
                        }
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.client_profile, container, false);
    }
}
