package com.example.edurelax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Edurelax.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "moods";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MOOD = "mood";

    public DatabaseHelper(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_MOOD + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    // INSERT
    public boolean insertMood(String date,
                              String mood) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(COLUMN_DATE, date);
        values.put(COLUMN_MOOD, mood);

        long result =
                db.insert(TABLE_NAME,
                        null,
                        values);

        return result != -1;
    }

    // READ
    public Cursor getAllMoods() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_NAME,
                null);
    }

    // DELETE
    public void deleteMood(int id) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        db.delete(TABLE_NAME,
                COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(id)
                });
    }

    // UPDATE
    public void updateMood(int id,
                           String date,
                           String mood) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(COLUMN_DATE, date);
        values.put(COLUMN_MOOD, mood);

        db.update(TABLE_NAME,
                values,
                COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(id)
                });
    }
}