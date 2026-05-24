package com.example.edurelax;

public class Mood {

    int id;
    String date;
    String mood;

    public Mood(int id, String date, String mood) {
        this.id = id;
        this.date = date;
        this.mood = mood;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMood() {
        return mood;
    }
}
