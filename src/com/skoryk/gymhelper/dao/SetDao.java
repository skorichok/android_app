package com.skoryk.gymhelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.entity.Set;

import java.util.ArrayList;

public class SetDao {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public SetDao(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<Set> getExercisesSets(Integer programExerciseId) {
        String query = context.getResources().getString(R.string.getProgramExerciseSets);
        Cursor c = db.rawQuery(query, new String[]{programExerciseId.toString()});

        ArrayList<Set> result = new ArrayList<Set>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Set set = new Set();
                    set.setProgramExerciseId(c.getInt(c.getColumnIndex("program_exercise_id")));
                    set.setSetNumber(c.getInt(c.getColumnIndex("set_number")));
                    set.setReps(c.getInt(c.getColumnIndex("reps")));
                    set.setWeight(c.getInt(c.getColumnIndex("weight")));
                    result.add(set);
                } while (c.moveToNext());
            }
            c.close();
        }

        return result;
    }

    public Set createExerciseSet(Set set) {
        ArrayList<Set> sets = getExercisesSets(set.getProgramExerciseId());
        set.setSetNumber(sets.size() + 1);
        ContentValues cv = new ContentValues();
        cv.put("program_exercise_id", set.getProgramExerciseId());
        cv.put("set_number", set.getSetNumber());
        cv.put("reps", set.getReps());
        cv.put("weight", set.getWeight());

        db.insert("sets", null, cv);

        return set;
    }
}
