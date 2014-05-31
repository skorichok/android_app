package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.adapter.ExerciseAdapter;
import com.skoryk.gymhelper.dao.ExerciseDao;
import com.skoryk.gymhelper.entity.Exercise;

import java.util.List;


public class ListActivity extends Activity {

    ExerciseDao exerciseDao;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        exerciseDao = new ExerciseDao(this);

        List<Exercise> exercises = exerciseDao.getAll();
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        Exercise[] exercises1 = new Exercise[exercises.size()];
        for (int i = 0; i < exercises.size(); i++) {
            exercises1[i] = exercises.get(i);
        }

        ExerciseAdapter adapter = new ExerciseAdapter(this,
                R.layout.exercise_list_item, exercises1);

        lvMain.setAdapter(adapter);

    }
}