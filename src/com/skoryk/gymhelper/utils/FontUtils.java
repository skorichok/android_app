package com.skoryk.gymhelper.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontUtils {
    public static void setHeaderTypeFace(AssetManager assetManager, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(assetManager, "Cabold_Comic_demo.otf"));
    }
}
