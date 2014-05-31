package com.skoryk.gymhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.skoryk.gymhelper.activity.*;
import com.skoryk.gymhelper.adapter.ExerciseAdapter;
import com.skoryk.gymhelper.adapter.ExerciseSpinner;
import com.skoryk.gymhelper.custom.PieGraph;
import com.skoryk.gymhelper.custom.PieSlice;
import com.skoryk.gymhelper.dao.ExerciseDao;
import com.skoryk.gymhelper.dao.ProgramExerciseDao;
import com.skoryk.gymhelper.entity.Exercise;
import com.skoryk.gymhelper.entity.ProgramExercise;

public class MainActivity extends Activity {
    public static final String USER_NAME_FIELD = "userName";

    ExerciseDao exerciseDao;
    int btn;
    ProgramExerciseDao programExerciseDao;
    SharedPreferences sPref;
    RelativeLayout view;
    List<Exercise> exercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exerciseDao = new ExerciseDao(this);
        programExerciseDao = new ProgramExerciseDao(this);


        if (startApplicationNotFirstTime()) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void start(View view) {
        EditText userName = (EditText) findViewById(R.id.editText1);
        Intent intent = new Intent(this, MainActivity.class);

        Editor ed = sPref.edit();
        ed.putString(USER_NAME_FIELD, userName.getText().toString());
        ed.commit();

        startActivity(intent);
    }

    private Boolean startApplicationNotFirstTime() {
        sPref = getPreferences(MODE_PRIVATE);
        Boolean startedNotFirstTime = sPref.getBoolean("notFirstTime", false);

        if (startedNotFirstTime) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_welcome);

            Editor ed = sPref.edit();
            ed.putBoolean("notFirstTime", true);
            ed.commit();
        }

        return startedNotFirstTime;
    }

    public void calendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void progress(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }


    public void viewAllTodayExercises(View view) {
//        List<ProgramExercise> programExercises = programExerciseDao.getProgramExercises(new Date());
//        if (null != programExercises) {
//            for(ProgramExercise programExercise : programExercises) {
//                System.out.println(
//                        "id=" + programExercise.getId() +
//                        "   date=" + programExercise.getDate().toString() +
//                        "   time=" + programExercise.getTime() +
//                        "   exerciseName" + programExercise.getExercise().getName() +
//                        "   trainingNumber" + programExercise.getTrainingNumber()
//                );
//            }
//        } else {
//            System.out.println("null");
//        }
    }


    public void startTraining(View view) {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }
}
