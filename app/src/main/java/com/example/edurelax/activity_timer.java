package com.example.edurelax;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

public class activity_timer extends AppCompatActivity {

    TextView txtTimer;
    Button btnStart, btnPause, btnReset, btnBack;

    CountDownTimer timer;
    long timeLeft = 25 * 60 * 1000; // 25 minutes

    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        txtTimer = findViewById(R.id.txtTimer);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);
        btnBack = findViewById(R.id.btnBack);


        updateTimerText();

        btnStart.setOnClickListener(v -> startTimer());
        btnPause.setOnClickListener(v -> pauseTimer());
        btnReset.setOnClickListener(v -> resetTimer());
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void startTimer() {
        if (isRunning) return;

        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                isRunning = false;
            }
        }.start();

        isRunning = true;
    }

    private void pauseTimer() {
        if (timer != null) {
            timer.cancel();
            isRunning = false;
        }
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timeLeft = 25 * 60 * 1000;
        updateTimerText();
        isRunning = false;
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String time = String.format("%02d:%02d", minutes, seconds);
        txtTimer.setText(time);
    }
}