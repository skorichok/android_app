package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.adapter.CalendarGridViewAdapter;
import com.skoryk.gymhelper.adapter.ExerciseAdapter;
import com.skoryk.gymhelper.adapter.TrainingAdapter;
import com.skoryk.gymhelper.dao.TrainingDao;
import com.skoryk.gymhelper.entity.Exercise;
import com.skoryk.gymhelper.entity.Training;
import com.skoryk.gymhelper.utils.CalendarUtils;
import com.skoryk.gymhelper.utils.Formats;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CalendarActivity extends Activity {

    private GridView gridView;
    private ArrayList<GregorianCalendar> datesArray = new ArrayList<GregorianCalendar>();
    private ArrayList<Integer> daysArray = new ArrayList<Integer>();
    private Context context;
    private String currentMonth;
    private int currentMonthId;
    private int currentYearId;
    private TextView monthNameTextView;
    private int daysInMonth;
    private TrainingDao trainingDao;
    private ArrayList<Training> trainings;
    private String choseDay;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_calendar);

        trainingDao = new TrainingDao(this);

        monthNameTextView = (TextView) findViewById(R.id.month_name);
        currentMonth = CalendarUtils.getCurrentMonth();

        currentMonthId = CalendarUtils.getCurrentMonthId();
        currentYearId = CalendarUtils.getCurrentYearId();
        monthNameTextView.setText(currentMonth);
        daysInMonth = CalendarUtils.getDaysInCurrentMonth();
        for (int i = 1; i <= daysInMonth; i++){
            datesArray.add(new GregorianCalendar(currentYearId, currentMonthId, i));
            daysArray.add(i);
        }

        gridView = (GridView) findViewById(R.id.calendar);
        CalendarGridViewAdapter gridViewAdapter =
                new CalendarGridViewAdapter(this, R.layout.grid_cell, datesArray, daysArray);
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id)
            {
                choseDay = Formats.GYM_HELPER_DATE_FORMAT.format(datesArray.get(position).getTime());
                showDialog(1);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Choose Training");
        View view = (RelativeLayout) getLayoutInflater()
                .inflate(R.layout.choose_training_dialog, null);
        adb.setView(view);
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, final Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        ListView exerciseListView = (ListView) dialog.getWindow().findViewById(R.id.training_list_view);

        trainings = trainingDao.getTrainingsByDate(choseDay);
        Training[] trainingArray = new Training[trainings.size()];
        for (int i = 0; i < trainings.size(); i++) {
            trainingArray[i] = trainings.get(i);
        }
        TrainingAdapter adapter = new TrainingAdapter(this,
                R.layout.training_list_item, trainingArray);
        exerciseListView.setAdapter(adapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });
    }
}