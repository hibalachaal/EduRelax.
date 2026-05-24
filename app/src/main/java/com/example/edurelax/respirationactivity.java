package com.example.edurelax;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class respirationactivity extends AppCompatActivity {

    private TextView txtInstruction;
    private View circle;
    private Button btnStart, btnStop, btnBack;

    private ObjectAnimator scaleUpX, scaleUpY;
    private ObjectAnimator scaleDownX, scaleDownY;
    private boolean isRunning = false;
    private Thread breathingThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respirationactivity);


        txtInstruction = findViewById(R.id.txtInstruction);
        circle = findViewById(R.id.circle);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnBack = findViewById(R.id.btnBack);

        // Animation agrandir (inspiration)
        scaleUpX = ObjectAnimator.ofFloat(circle, "scaleX", 1f, 1.5f);
        scaleUpX.setDuration(4000);

        scaleUpY = ObjectAnimator.ofFloat(circle, "scaleY", 1f, 1.5f);
        scaleUpY.setDuration(4000);

        // Animation réduire (expiration)
        scaleDownX = ObjectAnimator.ofFloat(circle, "scaleX", 1.5f, 1f);
        scaleDownX.setDuration(4000);

        scaleDownY = ObjectAnimator.ofFloat(circle, "scaleY", 1.5f, 1f);
        scaleDownY.setDuration(4000);

        // Bouton démarrer
        btnStart.setOnClickListener(v -> startBreathing());

        // Bouton arrêter
        btnStop.setOnClickListener(v -> stopBreathing());
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void startBreathing() {
        if (isRunning) return; // éviter plusieurs threads

        isRunning = true;

        breathingThread = new Thread(() -> {
            while (isRunning) {


                runOnUiThread(() -> {
                    txtInstruction.setText("Inspire...");
                    scaleUpX.start();
                    scaleUpY.start();
                });

                sleep(4000);

                if (!isRunning) break;


                runOnUiThread(() -> {
                    txtInstruction.setText("Expire...");
                    scaleDownX.start();
                    scaleDownY.start();
                });

                sleep(4000);
            }
        });

        breathingThread.start();
    }

    private void stopBreathing() {
        isRunning = false;

        if (breathingThread != null) {
            breathingThread.interrupt();
            breathingThread = null;
        }

        txtInstruction.setText("Appuie sur démarrer");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
