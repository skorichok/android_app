package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.adapter.ExerciseAdapter;
import com.skoryk.gymhelper.adapter.ProgramExerciseAdapter;
import com.skoryk.gymhelper.dao.ExerciseDao;
import com.skoryk.gymhelper.dao.ProgramExerciseDao;
import com.skoryk.gymhelper.entity.Exercise;
import com.skoryk.gymhelper.entity.ProgramExercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingActivity extends Activity {

    private ArrayList<ProgramExercise> exercises;
    private List<Exercise> exerciseList;
    private ProgramExerciseDao programExerciseDao;
    private ExerciseDao exerciseDao;
    private ProgramExerciseAdapter adapter;
    private ListView exercisesListView;
    private Context context;
    private int trainingId;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        trainingId = getIntent().getIntExtra("trainingId", 0);
        if (trainingId == 0 ) {
            throw new IllegalArgumentException("trainingId is null");
        }
        context = this;
        programExerciseDao = new ProgramExerciseDao(this);
        exerciseDao = new ExerciseDao(this);

        exercises = programExerciseDao.getProgramExercisesByTrainingId(trainingId);
        exercisesListView = (ListView) findViewById(R.id.exercises);
        adapter = new ProgramExerciseAdapter(this,
                R.layout.exercise_list_item, exercises);

        exercisesListView.setAdapter(adapter);
        exercisesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {

                Intent intent = new Intent(context, EditProgramExerciseActivity.class);
                intent.putExtra("programExerciseId", exercises.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    public void addExercise(View view) {
        showDialog(1);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Add exercise");
        View view = (RelativeLayout) getLayoutInflater()
                .inflate(R.layout.exercise_dialog, null);
        adb.setView(view);
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, final Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        ListView exerciseListView = (ListView) dialog.getWindow().findViewById(R.id.exercise_list_view);

        exerciseList = exerciseDao.getAll();
        Exercise[] exerciseArray = new Exercise[exerciseList.size()];
        for (int i = 0; i < exerciseList.size(); i++) {
            exerciseArray[i] = exerciseList.get(i);
        }
        ExerciseAdapter adapter = new ExerciseAdapter(this,
                R.layout.exercise_list_item, exerciseArray);
        exerciseListView.setAdapter(adapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                addExercise(exerciseList.get(position));
            }
        });
    }

    private void addExercise(Exercise exercise) {
        ProgramExercise programExercise = new ProgramExercise();
        programExercise.setExercise(exercise);
        programExercise.setDate(new Date());
        programExercise.setTrainingId(trainingId);
        programExerciseDao.createProgramExercise(programExercise);
        exercises = programExerciseDao.getProgramExercisesByTrainingId(trainingId);
        adapter.clear();
        adapter.addAll(exercises);
        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void onResume() {
//        adapter.clear();
//        exercises = programExerciseDao.getProgramExercisesByTrainingId(trainingId);
//        adapter.addAll(exercises);
//        adapter.notifyDataSetChanged();
//        super.onResume();
//    }
}