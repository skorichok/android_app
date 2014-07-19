package com.skoryk.gymhelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.skoryk.gymhelper.entity.Training;
import com.skoryk.gymhelper.utils.Formats;

import java.util.ArrayList;
import java.util.Date;

public class TrainingDao {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public TrainingDao(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void finishTraining() {
        ContentValues cv = new ContentValues();
        cv.put("is_finished", 1);

        db.update("trainings", cv, "is_finished=0", null);
    }

    public int createTraining() {
        ContentValues cv = new ContentValues();
        cv.put("date", Formats.GYM_HELPER_DATE_FORMAT.format(new Date()));

        return (int) db.insert("trainings", null, cv);
    }

    public Training getCurrentTraining() {
        Cursor cursor = db.query(true, "trainings", null, "is_finished = 0", null, null, null, null, null);
        Training training = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    training = new Training();
                    training.setDate(new Date(cursor.getString(cursor.getColumnIndex("date"))));
                    training.setFinished(cursor.getInt(cursor.getColumnIndex("is_finished")) == 1);
                    training.setId(cursor.getInt(cursor.getColumnIndex("id")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return training;
    }

    public ArrayList<Training> getTrainingsByDate(String date) {
        Cursor cursor = db.query(true, "trainings", null, "date = '" + date + "'", null, null, null, null, null);
        Training training = null;
        ArrayList<Training> result = new ArrayList<Training>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    training = new Training();
                    training.setDate(new Date(cursor.getString(cursor.getColumnIndex("date"))));
                    training.setFinished(cursor.getInt(cursor.getColumnIndex("is_finished")) == 1);
                    training.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    result.add(training);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return result;
    }
}
