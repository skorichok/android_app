package com.skoryk.gymhelper.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.skoryk.gymhelper.R;

import java.util.ArrayList;

public class CalendarGridViewAdapter extends ArrayAdapter<Integer> {
    Context context;
    int layoutResourceId;
    ArrayList<Integer> data = new ArrayList<Integer>();

    public CalendarGridViewAdapter(Context context, int layoutResourceId,
                                 ArrayList<Integer> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
//            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Integer day = data.get(position);
        holder.txtTitle.setText(day.toString());
//        holder.imageItem.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.gym_icon));
        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
//        ImageView imageItem;
    }
}