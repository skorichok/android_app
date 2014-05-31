package com.skoryk.gymhelper.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.skoryk.gymhelper.entity.Exercise;

public class ExerciseDao {
	private DBHelper dbHelper;

	public ExerciseDao(Context context) {
		this.dbHelper = new DBHelper(context);
	}
	
	public List<Exercise> getAll() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c = db.query("exercises", null, null, null, null, null, null);
		List<Exercise> result = new ArrayList<Exercise>();
	    if (c != null) {
	      if (c.moveToFirst()) {
	        do {
	        	Exercise exercise = new Exercise();
	        	exercise.setId(c.getInt(c.getColumnIndex("id")));
	        	exercise.setName(c.getString(c.getColumnIndex("name")));
	        	result.add(exercise);
	        } while (c.moveToNext());
	      }
	      c.close();
	    }
		
		return result;
	}

    public Exercise getById(Integer id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("exercises", null, "where id = " + id, null, null, null, null);
        List<Exercise> result = new ArrayList<Exercise>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Exercise exercise = new Exercise();
                    exercise.setId(c.getInt(c.getColumnIndex("id")));
                    exercise.setName(c.getString(c.getColumnIndex("name")));
                    result.add(exercise);
                } while (c.moveToNext());
            }
            c.close();
        }

        return result.get(0);
    }
}
