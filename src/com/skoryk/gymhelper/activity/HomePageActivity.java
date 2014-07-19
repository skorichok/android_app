package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.dao.ExerciseDao;
import com.skoryk.gymhelper.dao.ProgramExerciseDao;
import com.skoryk.gymhelper.dao.TrainingDao;
import com.skoryk.gymhelper.entity.Exercise;
import com.skoryk.gymhelper.entity.Training;

import java.util.List;

public class HomePageActivity extends Activity {
    private List<Exercise> exercises;
    private Button startButton;
    private Button resumeButton;
    private Button finishButton;
    private int trainingId;
    private ExerciseDao exerciseDao;
    private TrainingDao trainingDao;
    private ProgramExerciseDao programExerciseDao;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exerciseDao = new ExerciseDao(this);
        trainingDao = new TrainingDao(this);
        programExerciseDao = new ProgramExerciseDao(this);
        startButton = (Button) findViewById(R.id.start_training_button);
        resumeButton = (Button) findViewById(R.id.resume_training_button);
        finishButton = (Button) findViewById(R.id.finish_training_button);
    }

    public void calendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void progress(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }

    public void startTraining(View view) {
        Intent intent = new Intent(this, TrainingActivity.class);
        trainingId = trainingDao.createTraining();
        intent.putExtra("trainingId", trainingId);
        startActivity(intent);
    }

    public void resumeTraining(View view) {
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("trainingId", trainingId);
        startActivity(intent);
    }

    public void finishTraining(View view) {
        trainingDao.finishTraining();
        startButton.setVisibility(View.VISIBLE);
        resumeButton.setVisibility(View.INVISIBLE);
        finishButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        Training training = trainingDao.getCurrentTraining();
        if (null != training) {
            trainingId = training.getId();
            startButton.setVisibility(View.INVISIBLE);
            resumeButton.setVisibility(View.VISIBLE);
            finishButton.setVisibility(View.VISIBLE);
        } else {
            startButton.setVisibility(View.VISIBLE);
            resumeButton.setVisibility(View.INVISIBLE);
            finishButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}