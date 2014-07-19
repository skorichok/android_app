package com.skoryk.gymhelper.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.dao.TrainingDao;
import com.skoryk.gymhelper.entity.Training;
import com.skoryk.gymhelper.utils.Formats;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarGridViewAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<GregorianCalendar> dates = new ArrayList<GregorianCalendar>();
    private ArrayList<Integer> days = new ArrayList<Integer>();
    private TrainingDao trainingDao;

    public CalendarGridViewAdapter(Context context, int layoutResourceId,
                                 ArrayList<GregorianCalendar> dates, ArrayList<Integer> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        trainingDao = new TrainingDao(context);
        this.days = data;
        this.dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        List<Training> trainings =
                trainingDao.getTrainingsByDate(Formats.GYM_HELPER_DATE_FORMAT.format(dates.get(position).getTime()));
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Integer day = days.get(position);
        holder.txtTitle.setText(day.toString());
        if (!trainings.isEmpty()) {
            holder.imageItem.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.gym_dumbel));
        } else {
            holder.imageItem.setImageBitmap(null);
        }
        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
    }
}