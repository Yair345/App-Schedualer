package com.example.appscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class StartModeActivity extends AppCompatActivity{

    private ArrayList<String> blockApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mode);

    }

    public void onClick(View v)
    {
        UserSingleton singleton = com.example.appscheduler.UserSingleton.getInstance();

        if (singleton.blocked.isEmpty())
        {
            Toast.makeText(StartModeActivity.this, "You need to choose apps first!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, ChooseTimeActivity.class);
            startActivity(intent);
        }
    }

    public void onEditClick(View view)
    {
        Intent intent = new Intent(this, ChooseAppsActivity.class);
        startActivity(intent);
    }
}