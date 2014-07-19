package com.skoryk.gymhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.skoryk.gymhelper.activity.HomePageActivity;
import com.skoryk.gymhelper.activity.WelcomeActivity;

public class MainActivity extends Activity {
    private SharedPreferences sPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getSharedPreferences("MyPrefsFile", 0);
        Boolean startedNotFirstTime = sPref.getBoolean("notFirstTime", false);
        Intent intent;
        if (startedNotFirstTime) {
            intent = new Intent(this, HomePageActivity.class);
        } else {
            intent = new Intent(this, WelcomeActivity.class);
        }
        startActivity(intent);
    }
}
