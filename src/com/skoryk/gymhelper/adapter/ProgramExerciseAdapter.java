package com.skoryk.gymhelper.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.entity.ProgramExercise;

import java.util.ArrayList;

public class ProgramExerciseAdapter extends ArrayAdapter<ProgramExercise> {

    Context context;
    int layoutResourceId;
    ArrayList<ProgramExercise> data = null;

    public ProgramExerciseAdapter(Context context, int layoutResourceId, ArrayList<ProgramExercise> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProgramExerciseHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ProgramExerciseHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView) row.findViewById(R.id.exerciseName);
            holder.setsCount = (TextView) row.findViewById(R.id.setsCount);

            row.setTag(holder);
        } else {
            holder = (ProgramExerciseHolder) row.getTag();
        }

        ProgramExercise exercise = data.get(position);
        holder.txtTitle.setText(exercise.getExercise().getName());
        if (exercise.getSetsCount() != 0) {
            holder.setsCount.setText("(" + exercise.getSetsCount() + ")");
        } else {
            holder.setsCount.setText("");
        }
        int j = ((Activity) context).getResources().getIdentifier("dumbbel", "drawable", ((Activity) context).getPackageName());
        holder.imgIcon.setImageResource(j);

        return row;
    }

    static class ProgramExerciseHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView setsCount;
    }
}