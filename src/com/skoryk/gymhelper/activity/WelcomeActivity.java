package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.skoryk.gymhelper.R;

public class WelcomeActivity extends Activity {
    private SharedPreferences sPref;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sPref = getSharedPreferences("MyPrefsFile", 0);
    }

    public void start(View view) {
        EditText userName = (EditText) findViewById(R.id.user_name);
        Intent intent = new Intent(this, HomePageActivity.class);

        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean("notFirstTime", true);
        ed.putString("userName", userName.getText().toString());
        ed.commit();

        startActivity(intent);
    }

}