package com.example.edurelax;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class sonsrelaxants extends AppCompatActivity {

    Spinner spinnerSounds;
    Button btnPlay, btnStop ,btnBack;

    MediaPlayer mediaPlayer;

    String[] sounds = {"Pluie 🌧️", "Mer 🌊", "Forêt 🌲"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonsrelaxants);

        spinnerSounds = findViewById(R.id.spinnerSound);
        btnPlay = findViewById(R.id.btnplay);
        btnStop = findViewById(R.id.btnStop);
        btnBack = findViewById(R.id.btnBack);
        // Adapter pour le spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, sounds);

        spinnerSounds.setAdapter(adapter);

        btnPlay.setOnClickListener(v -> playSelectedSound());

        btnStop.setOnClickListener(v -> stopSound());
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void playSelectedSound() {

        stopSound();

        int position = spinnerSounds.getSelectedItemPosition();

        if (position == 0) {
            mediaPlayer = MediaPlayer.create(this, R.raw.rain);
        } else if (position == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.ocean);
        } else if (position == 2) {
            mediaPlayer = MediaPlayer.create(this, R.raw.forest);
        }

        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true); // boucle infinie
            mediaPlayer.start();
        }
    }

    private void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSound();
    }
}