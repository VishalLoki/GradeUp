package com.example.gradeup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.l_blue));
        getWindow().setNavigationBarColor(getColor(R.color.l_blue));
        setContentView(R.layout.activity_main);
    }
}