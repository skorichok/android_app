package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.adapter.CalendarGridViewAdapter;
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


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_calendar);
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
                Intent intent = new Intent(context, DayActivity.class);
                intent.putExtra("day", Formats.GYM_HELPER_DATE_FORMAT.format(datesArray.get(position).getTime()));
                startActivity(intent);
            }
        });
    }
}