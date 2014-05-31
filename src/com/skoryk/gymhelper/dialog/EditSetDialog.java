package com.skoryk.gymhelper.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.skoryk.gymhelper.R;

public class EditSetDialog extends AlertDialog
{
    private TextView weightText;
    private TextView repsText;
    private TextView weightValue;
    private TextView repsValue;
    private Context context;

    public EditSetDialog(Context context)
    {
        super(context, R.style.AppBaseTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View dialogView = (RelativeLayout) getLayoutInflater()
                .inflate(R.layout.edit_set_dialog, null);
        setView(dialogView);

        SeekBar weightSeekBar = (SeekBar)dialogView.findViewById(R.id.weight_seek_bar);
        SeekBar repsSeekBar = (SeekBar)dialogView.findViewById(R.id.reps_seek_bar);
        weightText = (TextView)dialogView.findViewById(R.id.weight_text_view);
        repsText = (TextView)dialogView.findViewById(R.id.reps_text_view);
        weightValue = (TextView)dialogView.findViewById(R.id.weight_value);
        repsValue = (TextView)dialogView.findViewById(R.id.reps_value);

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                weightValue.setText(String.valueOf(progress));
            }
        });

        repsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                repsValue.setText(String.valueOf(progress));
            }
        });


    }
}