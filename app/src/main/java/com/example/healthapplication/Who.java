package com.example.healthapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Who extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        Button button = (Button) v.findViewById(R.id.confirm) ;
        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RadioButton r = v.findViewById(R.id.patient);
                boolean p = r.isChecked();

                Map<String, Object> user = new HashMap<>();
                String email = mAuth.getCurrentUser().getEmail().toString();
                if (p){
                    user.put("role", "patient");
                    user.put("card", "-");
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add("");
                    user.put("diseases", arr);
                }
                else {
                    user.put("role", "doctor");
                }

                db.collection("users").document(email).update(user).addOnSuccessListener(unused -> {
                    Toast.makeText(view.getContext(), "Добро пожаловать!", Toast.LENGTH_SHORT).show();});

                ((MainActivity) getActivity()).start();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.who, container, false);
    }
}
