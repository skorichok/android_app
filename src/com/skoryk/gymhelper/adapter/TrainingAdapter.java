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
import com.skoryk.gymhelper.entity.Training;

public class TrainingAdapter extends ArrayAdapter<Training> {

    Context context;
    int layoutResourceId;
    Training data[] = null;

    public TrainingAdapter(Context context, int layoutResourceId, Training[] data) {
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
        ExerciseHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ExerciseHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView) row.findViewById(R.id.trainingName);

            row.setTag(holder);
        } else {
            holder = (ExerciseHolder) row.getTag();
        }

        Training training = data[position];
        holder.txtTitle.setText("Training #" + position);
        int j = ((Activity) context).getResources().getIdentifier("dumbbel", "drawable", ((Activity) context).getPackageName());
        holder.imgIcon.setImageResource(j);

        return row;
    }

    static class ExerciseHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}