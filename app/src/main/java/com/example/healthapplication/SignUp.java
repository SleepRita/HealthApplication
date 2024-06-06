package com.example.healthapplication;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends Fragment {
    View v;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.go_to_main);
        TextView email = view.findViewById(R.id.email);
        TextView password = view.findViewById(R.id.password);
        TextView password2 = view.findViewById(R.id.confirm_password);

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(v -> {
            if (!password.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
                    && !password2.getText().toString().isEmpty()){
                if (password.getText().toString().equals(password2.getText().toString())) {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(getActivity(), task -> {
                                if (task.isSuccessful()) {
                                    addUser(email.getText().toString().toLowerCase(), "-",
                                            "-", "-", view);
                                    ((MainActivity) getActivity()).toWho(view);
                                } else {
                                    email.setText("");
                                    password.setText("");
                                    password2.setText("");
                                }
                            });
                }
                else {
                    Toast.makeText(view.getContext(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(view.getContext(), "Все должно быть заполнено!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.sign_up, container, false);
        return v;
    }

    public void addUser(String email, String name, String surname, String patronymic, View view) {
        Map<String, Object> user = new HashMap<>();
        ArrayList<String> arr = new ArrayList<>();
        arr.add("");

        user.put("email", email);
        user.put("surname", surname);
        user.put("name", name);
        user.put("patronymic", patronymic);

        db.collection("users").document(email).set(user);
    }
}

