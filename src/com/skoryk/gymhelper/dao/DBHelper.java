package com.skoryk.gymhelper.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.skoryk.gymhelper.R;

class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(context.getResources().getString(R.string.createExercises));
        db.execSQL(context.getResources().getString(R.string.createProgramExercises));
        db.execSQL(context.getResources().getString(R.string.createSets));

        //****INIT EXERCISES***************************************************************
        String[] exercises = context.getResources().getStringArray(R.array.initExercises);
        for (String exercise : exercises) {
            db.execSQL(exercise);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}