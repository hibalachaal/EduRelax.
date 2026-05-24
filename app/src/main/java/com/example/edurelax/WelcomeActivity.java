package com.example.edurelax;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    EditText editName;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        editName =
                findViewById(R.id.editName);

        btnContinue =
                findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {

            String name =
                    editName.getText().toString();

            SharedPreferences preferences =
                    getSharedPreferences(
                            "UserData",
                            MODE_PRIVATE);

            SharedPreferences.Editor editor =
                    preferences.edit();

            editor.putString("username", name);

            editor.apply();

            Intent intent =
                    new Intent(this,
                            MainActivity.class);

            startActivity(intent);

            finish();
        });
    }
}