package com.example.appscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseTimeActivity extends AppCompatActivity
        implements TimePickerFragment.TimePickedListener{

    TextView showTime;
    Button startButton;

    int hour = -1, minute = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);

        showTime = findViewById(R.id.showTheTimePicked);
        startButton = findViewById(R.id.startButton);
    }

    private void whatTime(String s)
    {
        String[] setNumbers = s.split(":");
        hour = Integer.parseInt(setNumbers[0]);
        minute = Integer.parseInt(setNumbers[1]);
    }

    public void showTimePickerDialog (View v)
    {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimePicked(String time) {
        showTime.setText(time);
        whatTime(time);
    }

    public void onClick(View v)
    {
        if (hour == -1)
            Toast.makeText(this, "You need to choose time first!", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(this, stopModeActivity.class);
            intent.putExtra("hour", hour);
            intent.putExtra("minute", minute);
            startActivity(intent);
        }
    }
}