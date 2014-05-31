package com.skoryk.gymhelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.entity.Exercise;
import com.skoryk.gymhelper.entity.ProgramExercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProgramExerciseDao {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public ProgramExerciseDao(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<ProgramExercise> getProgramExercises(Date date) {
        String query = context.getResources().getString(R.string.getProgramExercisesByDate);
        Cursor c = db.rawQuery(query, new String[]{new SimpleDateFormat("dd-MMM-yyyy").format(date)});

        ArrayList<ProgramExercise> programExerciseList = new ArrayList<ProgramExercise>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    ProgramExercise programExercise = new ProgramExercise();
                    programExercise.setId(c.getInt(c.getColumnIndex("id")));
                    Exercise exercise = new Exercise(
                        c.getInt(c.getColumnIndex("exerciseId")),
                        c.getString(c.getColumnIndex("exerciseName"))
                    );
                    programExercise.setExercise(exercise);
                    try {
                        programExercise.setDate(new SimpleDateFormat("dd-MMM-yyyy")
                                .parse(c.getString(c.getColumnIndex("date"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    programExercise.setTime(c.getString(c.getColumnIndex("time")));
                    programExercise.setTrainingNumber(c.getInt(c.getColumnIndex("trainingNumber")));
                    programExerciseList.add(programExercise);
                } while (c.moveToNext());
            }
            c.close();
        }

        return programExerciseList;
    }

    public ProgramExercise getProgramExerciseById(Integer id) {
        String query = context.getResources().getString(R.string.getProgramExercisesById);
        Cursor c = db.rawQuery(query, new String[]{id.toString()});

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    ProgramExercise programExercise = new ProgramExercise();
                    programExercise.setId(c.getInt(c.getColumnIndex("id")));
                    Exercise exercise = new Exercise(
                        c.getInt(c.getColumnIndex("exerciseId")),
                        c.getString(c.getColumnIndex("exerciseName"))
                    );
                    programExercise.setExercise(exercise);
                    try {
                        programExercise.setDate(new SimpleDateFormat("dd-MMM-yyyy")
                                .parse(c.getString(c.getColumnIndex("date"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    programExercise.setTime(c.getString(c.getColumnIndex("time")));
                    programExercise.setTrainingNumber(c.getInt(c.getColumnIndex("trainingNumber")));
                    return programExercise;
                } while (c.moveToNext());
            }
            c.close();
        }

        return null;
    }

    public ProgramExercise createProgramExercise(ProgramExercise programExercise) {
        ContentValues cv = new ContentValues();
        cv.put("date", new SimpleDateFormat("dd-MMM-yyyy").format(programExercise.getDate()));
        cv.put("time", new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime()));
        cv.put("exercise_id", programExercise.getExercise().getId().toString());
        cv.put("training_number", programExercise.getTrainingNumber().toString());

        Long rowID = db.insert("program_exercises", null, cv);

        programExercise.setId(Integer.valueOf(rowID.toString()));

        return programExercise;
    }
}
