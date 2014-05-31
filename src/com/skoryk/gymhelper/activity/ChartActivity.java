package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.custom.PieGraph;
import com.skoryk.gymhelper.custom.PieSlice;

import java.util.ArrayList;

public class ChartActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        PieGraph pieGraph = (PieGraph) findViewById(R.id.pieChart);
        String[] colors = this.getResources().getStringArray(R.array.colors);
        for (int i = 0; i < 5; i++) {
            PieSlice  pieSlice = new PieSlice();
            pieSlice.setValue(i + 50);
            pieSlice.setColor(Color.parseColor(colors[i]));
            pieSlice.setTitle(i + 50 + " %");
            pieGraph.addSlice(pieSlice);
        }
    }
}