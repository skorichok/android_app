package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.skoryk.gymhelper.R;


public class DayActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        Intent intent = getIntent();
        Integer dayNumber = intent.getIntExtra("dayNumber", 1);
        TextView textView = (TextView) findViewById(R.id.dayNumber);
        textView.setText(String.valueOf(dayNumber));
    }
}