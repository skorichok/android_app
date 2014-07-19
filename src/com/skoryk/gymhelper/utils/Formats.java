package com.skoryk.gymhelper.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Formats {
    public static final SimpleDateFormat GYM_HELPER_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    public static final SimpleDateFormat GYM_HELPER_TIME_FORMAT = new SimpleDateFormat("HH-mm-ss");
}
