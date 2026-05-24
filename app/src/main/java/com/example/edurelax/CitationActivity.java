package com.example.edurelax;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CitationActivity extends AppCompatActivity {

    TextView txtCitation;
    Button btnNewCitation , btnBack;

    String[] citations = {

            "Crois en toi, chaque petit effort compte 💙",

            "Le succès commence par la confiance en soi ✨",

            "Respire profondément, tu avances chaque jour 🌿",

            "Même les petits progrès sont importants 🌸",

            "Tu es plus fort(e) que ton stress 🌟",

            "Chaque journée est une nouvelle chance ☀️"
    };

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citation);

        txtCitation = findViewById(R.id.txtCitation);
        btnNewCitation = findViewById(R.id.btnNewCitation);
        btnBack = findViewById(R.id.btnBack);
        btnNewCitation.setOnClickListener(v -> {

            int index = random.nextInt(citations.length);

            txtCitation.setText(citations[index]);
        });
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}