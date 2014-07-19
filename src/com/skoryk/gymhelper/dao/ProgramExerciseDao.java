package com.skoryk.gymhelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.entity.Exercise;
import com.skoryk.gymhelper.entity.ProgramExercise;
import com.skoryk.gymhelper.utils.Formats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProgramExerciseDao {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private SetDao setDao;

    public ProgramExerciseDao(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        setDao = new SetDao(context);
    }

    public ArrayList<ProgramExercise> getProgramExercisesByTrainingId(Integer trainingId) {
        String query = context.getResources().getString(R.string.getProgramExercisesByTrainingId);
        Cursor c = db.rawQuery(query, new String[]{trainingId.toString()});

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
                        programExercise.setDate(Formats.GYM_HELPER_DATE_FORMAT
                                .parse(c.getString(c.getColumnIndex("date"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    programExercise.setTime(c.getString(c.getColumnIndex("time")));
                    programExercise.setTrainingId(c.getInt(c.getColumnIndex("training_id")));
                    programExercise.setSets(setDao.getExercisesSets(programExercise.getId()));
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
                        programExercise.setDate(Formats.GYM_HELPER_DATE_FORMAT
                                .parse(c.getString(c.getColumnIndex("date"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    programExercise.setTime(c.getString(c.getColumnIndex("time")));
                    programExercise.setTrainingId(c.getInt(c.getColumnIndex("training_id")));
                    return programExercise;
                } while (c.moveToNext());
            }
            c.close();
        }

        return null;
    }

    public ProgramExercise createProgramExercise(ProgramExercise programExercise) {
        ContentValues cv = new ContentValues();
        cv.put("date", Formats.GYM_HELPER_DATE_FORMAT.format(programExercise.getDate()));
        cv.put("time", Formats.GYM_HELPER_TIME_FORMAT.format(Calendar.getInstance().getTime()));
        cv.put("exercise_id", programExercise.getExercise().getId().toString());
        cv.put("training_id", programExercise.getTrainingId().toString());

        Long rowID = db.insert("program_exercises", null, cv);

        programExercise.setId(Integer.valueOf(rowID.toString()));

        return programExercise;
    }
}
