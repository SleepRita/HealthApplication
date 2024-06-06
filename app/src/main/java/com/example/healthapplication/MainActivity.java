package com.example.healthapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    public boolean isPatient = true;
    FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        SignIn f1 = new SignIn();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner1,f1);
        ft.commit();
    }

    public void toSignIn(View view){
        SignIn f1 = new SignIn();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner1,f1);
        ft.commit();
    }

    public void toSignUp(View view){
        SignUp f2 = new SignUp();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner1,f2);
        ft.commit();
    }

    public void toWho(View view){
        Who f3 = new Who();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner1,f3);
        ft.commit();
    }

    // Перебрасывает на другую activity (Account)
    public void start() {
        auth = FirebaseAuth.getInstance();
        String email = auth.getCurrentUser().getEmail();

        db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(t -> {
                    if (t.isSuccessful()) {
                        for (DocumentSnapshot doc : t.getResult()) {
                            isPatient = doc.get("role").toString().equals("patient");
                        }
                        if (isPatient)
                        {
                            Intent intent = new Intent(this, AccountPatient.class);
                            intent.putExtra("is_patient", isPatient);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(this, AccountDoctor.class);
                            intent.putExtra("is_patient", isPatient);
                            startActivity(intent);
                        }
                    }
                });

    }
}