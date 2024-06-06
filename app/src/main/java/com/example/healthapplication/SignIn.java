package com.example.healthapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIn extends Fragment {

    View v;
    FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        auth = FirebaseAuth.getInstance();

        TextView email = v.findViewById(R.id.email);
        TextView pswrd = v.findViewById(R.id.password);

        Button button = (Button) v.findViewById(R.id.go_to_main) ;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (email.length() != 0 && pswrd.length() != 0) {
                    auth.signInWithEmailAndPassword(email.getText().toString().toLowerCase(), pswrd.getText().toString())
                            .addOnCompleteListener(getActivity(), task -> {
                                if (task.isSuccessful()) {
                                    getNameSurname(v);
                                    ((MainActivity) getActivity()).start();
                                }
                                else {
                                    Toast.makeText(v.getContext(), "Ошибка входа.", Toast.LENGTH_SHORT).show();
                                    email.setText("");
                                    pswrd.setText("");
                                }
                            });
                }
                else {
                    Toast.makeText(v.getContext(), "Ошибка входа.", Toast.LENGTH_SHORT).show();
                    email.setText("");
                    pswrd.setText("");
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.sign_in, container, false);
        return v;
    }

    private void getNameSurname(View view) {
        db.collection("users")
                .whereEqualTo("email", auth.getCurrentUser().getEmail().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String title;
                        for (DocumentSnapshot doc : task.getResult()) {
                            title = doc.get("name").toString() + " " + doc.get("surname").toString();
                            Toast.makeText(view.getContext(), "Добро пожаловать, " + title, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
