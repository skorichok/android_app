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
import com.skoryk.gymhelper.entity.Set;

import java.util.ArrayList;

public class ExerciseSetsAdapter extends ArrayAdapter<Set> {

    Context context;
    int layoutResourceId;
    ArrayList<Set> data = null;

    public ExerciseSetsAdapter(Context context, int layoutResourceId, ArrayList<Set> data) {
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
        SetHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new SetHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.setImgIcon);
            holder.txtTitle = (TextView) row.findViewById(R.id.setTxtTitle);

            row.setTag(holder);
        } else {
            holder = (SetHolder) row.getTag();
        }

        Set set = data.get(position);
        holder.txtTitle.setText("Set No " + set.getSetNumber() + "   "
                + set.getWeight() + "kg   " + set.getReps() + " reps");
        int j = ((Activity) context).getResources().getIdentifier("set_icon", "drawable", ((Activity) context).getPackageName());
        holder.imgIcon.setImageResource(j);

        return row;
    }

    static class SetHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}