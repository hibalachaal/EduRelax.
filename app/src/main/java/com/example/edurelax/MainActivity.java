package com.example.edurelax;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    View cardRespiration, cardSon, cardTimer, cardCitation, cardJournal;
    TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cardRespiration = findViewById(R.id.cardRespiration);
        cardSon = findViewById(R.id.cardSon);
        cardTimer = findViewById(R.id.cardTimer);
        cardCitation = findViewById(R.id.cardCitation);
        cardJournal = findViewById(R.id.cardJournal);
        txtWelcome = findViewById(R.id.txtWelcome);


        cardRespiration.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, respirationactivity.class);
            startActivity(intent);
        });

        cardSon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, sonsrelaxants.class);
            startActivity(intent);
        });

        cardTimer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_timer.class);
            startActivity(intent);
        });

        cardCitation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CitationActivity.class);
            startActivity(intent);
        });


        cardJournal.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, JournalActivity.class);
            startActivity(intent);
        });
        SharedPreferences preferences =
                getSharedPreferences(
                        "UserData",
                        MODE_PRIVATE);

        String username =
                preferences.getString(
                        "username",
                        "Utilisateur");
                 txtWelcome.setText("Bonjour " + username + " 💙");
    }
}




