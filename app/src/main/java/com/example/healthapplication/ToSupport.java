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
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class ToSupport extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        Button button = (Button) v.findViewById(R.id.send_message);
        TextView theme = v.findViewById(R.id.message_theme);
        TextView message = v.findViewById(R.id.message_body);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (theme.length() != 0 && message.length() != 0)
                {
                    theme.setText("");
                    message.setText("");
                    Toast.makeText(view.getContext(), "Отправлено!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view.getContext(), "Заполните оба поля.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.to_support, container, false);
    }
}
