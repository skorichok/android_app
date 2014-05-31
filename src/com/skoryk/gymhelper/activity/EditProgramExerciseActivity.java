package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.adapter.ExerciseSetsAdapter;
import com.skoryk.gymhelper.dao.ProgramExerciseDao;
import com.skoryk.gymhelper.dao.SetDao;
import com.skoryk.gymhelper.dialog.EditSetDialog;
import com.skoryk.gymhelper.entity.ProgramExercise;
import com.skoryk.gymhelper.entity.Set;

import java.util.ArrayList;

public class EditProgramExerciseActivity extends Activity {
    private Integer programExerciseId;
    private SetDao setDao;
    private ProgramExerciseDao programExerciseDao;
    private ArrayList<Set> sets;
    private ProgramExercise programExercise;
    private ExerciseSetsAdapter adapter;
    private AlertDialog editSetDialog;
    private SeekBar weightSeekBar;
    private SeekBar repsSeekBar;
    private TextView weightValue;
    private TextView repsValue;
    private TextView weightText;
    private TextView repsText;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_program_exercise);
        setDao = new SetDao(this);
        programExerciseDao = new ProgramExerciseDao(this);
        Intent intent = getIntent();
        programExerciseId = intent.getIntExtra("programExerciseId", 0);
        programExercise = programExerciseDao.getProgramExerciseById(programExerciseId);

        sets = setDao.getExercisesSets(programExerciseId);
        adapter = new ExerciseSetsAdapter(this,
                R.layout.set_list_item, sets);
        ListView setsListView = (ListView) findViewById(R.id.sets_list_view);
        setsListView.setAdapter(adapter);

        TextView headerText = (TextView) findViewById(R.id.header_text);
        TextView addNewSetText = (TextView) findViewById(R.id.add_new_set_text);
    }

    public void addNewSet(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = (RelativeLayout) getLayoutInflater()
                .inflate(R.layout.edit_set_dialog, null);
        builder.setView(dialogView);

        editSetDialog = builder.create();
        editSetDialog.show();
        weightSeekBar = (SeekBar)editSetDialog.getWindow().findViewById(R.id.weight_seek_bar);
        repsSeekBar = (SeekBar)editSetDialog.getWindow().findViewById(R.id.reps_seek_bar);
        weightValue = (TextView)editSetDialog.getWindow().findViewById(R.id.weight_value);
        repsValue = (TextView)editSetDialog.getWindow().findViewById(R.id.reps_value);
        weightText = (TextView)editSetDialog.getWindow().findViewById(R.id.weight_text_view);
        repsText = (TextView)editSetDialog.getWindow().findViewById(R.id.reps_text_view);
        ImageView buttonOk = (ImageView) editSetDialog.findViewById(R.id.button_ok);

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weightValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        repsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                repsValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set set = new Set();
                set.setProgramExerciseId(programExerciseId);
                set.setReps(repsSeekBar.getProgress());
                set.setWeight(weightSeekBar.getProgress());

                sets.add(setDao.createExerciseSet(set));
                adapter.notifyDataSetChanged();
                editSetDialog.dismiss();
            }
        });
    }


    private EditSetDialog createDialog() {
        EditSetDialog.Builder adb = new EditSetDialog.Builder(this);
        adb.setTitle("Add exercise");
        return (EditSetDialog) adb.create();
    }

//    private void init(){
//        FontUtils.setHeaderTypeFace(getAssets(), weightText);
//        FontUtils.setHeaderTypeFace(getAssets(), weightValue);
//        FontUtils.setHeaderTypeFace(getAssets(), repsText);
//        FontUtils.setHeaderTypeFace(getAssets(), repsValue);
//    }
}