package com.example.edurelax;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MoodAdapter extends ArrayAdapter<Mood> {
    public interface OnEditClickListener {
        void onEditClick(Mood mood);
    }
    Activity context;
    ArrayList<Mood> moodList;
    DatabaseHelper databaseHelper;
    OnEditClickListener listener;

    public MoodAdapter(Activity context,
                       ArrayList<Mood> moodList,
                       DatabaseHelper databaseHelper,
                       OnEditClickListener listener) {

        super(context,
                R.layout.item_mood,
                moodList);

        this.context = context;
        this.moodList = moodList;
        this.databaseHelper = databaseHelper;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                context.getLayoutInflater();

        View rowView =
                inflater.inflate(R.layout.item_mood, null, true);

        TextView txtMoodItem =
                rowView.findViewById(R.id.txtMoodItem);

        ImageButton btnDelete =
                rowView.findViewById(R.id.btnDelete);

        Mood mood =
                moodList.get(position);

        txtMoodItem.setText(
                mood.getDate()+ " - " + mood.getMood());

        btnDelete.setOnClickListener(v -> {

            databaseHelper.deleteMood(
                    mood.getId());

            moodList.remove(position);

            notifyDataSetChanged();
        });
        ImageButton btnEdit = rowView.findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(mood);
            }
        });

        return rowView;
    }
}
