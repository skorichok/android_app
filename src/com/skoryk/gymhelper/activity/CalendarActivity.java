package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.adapter.CalendarGridViewAdapter;

import java.util.ArrayList;

public class CalendarActivity extends Activity {

    GridView gridView;
    ArrayList<Integer> gridArray = new ArrayList<Integer>();
    Context context;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_calendar);

        for (int i = 1; i <= 31; i++){
            gridArray.add(i);
        }

        gridView = (GridView) findViewById(R.id.calendar);
        CalendarGridViewAdapter gridViewAdapter = new CalendarGridViewAdapter(this, R.layout.grid_cell, gridArray);
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id)
            {
                Intent intent = new Intent(context, DayActivity.class);
                intent.putExtra("dayNumber", Integer.valueOf(gridArray.get(position)));
                startActivity(intent);
            }
        });
    }
}