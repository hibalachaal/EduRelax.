package com.example.edurelax;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class JournalActivity extends AppCompatActivity {

    EditText editDate;

    RadioGroup radioGroupMood;

    Button btnSaveMood,btnBack;

    ListView listViewMoods;

    DatabaseHelper databaseHelper;

    ArrayList<Mood> moodList;

    MoodAdapter adapter;

    int selectedMoodId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        editDate = findViewById(R.id.editDate);

        radioGroupMood =
                findViewById(R.id.radioGroupMood);

        btnSaveMood =
                findViewById(R.id.btnSaveMood);
        btnBack =findViewById(R.id.btnBack);

        listViewMoods =
                findViewById(R.id.listViewMoods);

        databaseHelper =
                new DatabaseHelper(this);

        moodList = new ArrayList<>();

        adapter = new MoodAdapter(
                this,
                moodList,
                databaseHelper,

                mood -> {

                    selectedMoodId =
                            mood.getId();

                    editDate.setText(
                            mood.getDate());

                    String moodText =
                            mood.getMood();

                    if (moodText.contains("Heureux")) {

                        ((RadioButton)
                                findViewById(R.id.radioHappy))
                                .setChecked(true);

                    }

                    else if (moodText.contains("Passable")) {

                        ((RadioButton)
                                findViewById(R.id.radioNormal))
                                .setChecked(true);

                    }

                    else {

                        ((RadioButton)
                                findViewById(R.id.radioSad))
                                .setChecked(true);
                    }
                });;

        listViewMoods.setAdapter(adapter);

        loadMoods();


        btnSaveMood.setOnClickListener(v -> {

            String date =
                    editDate.getText().toString();

            int selectedId =
                    radioGroupMood.getCheckedRadioButtonId();

            if (selectedId == -1) {

                Toast.makeText(this,
                        "Choisis une humeur",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            RadioButton selectedMood =
                    findViewById(selectedId);

            String mood =
                    selectedMood.getText().toString();


            if (selectedMoodId == -1) {

                databaseHelper.insertMood(
                        date,
                        mood);

            }


            else {

                databaseHelper.updateMood(
                        selectedMoodId,
                        date,
                        mood);

                selectedMoodId = -1;
            }

            editDate.setText("");

            radioGroupMood.clearCheck();

            loadMoods();
        });

        // MODIFIER
        listViewMoods.setOnItemClickListener((parent,
                                              view,
                                              position,
                                              id) -> {

            Mood mood =
                    moodList.get(position);

            selectedMoodId =
                    mood.getId();

            editDate.setText(
                    mood.getDate());

            String moodText =
                    mood.getMood();

            if (moodText.contains("Heureux")) {

                ((RadioButton)
                        findViewById(R.id.radioHappy))
                        .setChecked(true);

            } else if (moodText.contains("Passable")) {

                ((RadioButton)
                        findViewById(R.id.radioNormal))
                        .setChecked(true);

            } else {

                ((RadioButton)
                        findViewById(R.id.radioSad))
                        .setChecked(true);
            }
        });
    }

    private void loadMoods() {

        moodList.clear();

        Cursor cursor =
                databaseHelper.getAllMoods();

        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(0);

            String date =
                    cursor.getString(1);

            String mood =
                    cursor.getString(2);

            moodList.add(
                    new Mood(id,
                            date,
                            mood));
        }

        adapter.notifyDataSetChanged();

        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

}