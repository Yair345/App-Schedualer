package com.example.appscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseTimeActivity extends AppCompatActivity
        implements TimePickerFragment.TimePickedListener{
    /**
     * The class was made for the choice of the time
     */

    TextView showTime;
    Button startButton;

    int hour = -1, minute = -1; // des time

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);

        // create view
        showTime = findViewById(R.id.showTheTimePicked);
        startButton = findViewById(R.id.startButton);
    }

    private void whatTime(String s)
    {
        /**
         * parse the string into integers
         */
        String[] setNumbers = s.split(":");
        hour = Integer.parseInt(setNumbers[0]);
        minute = Integer.parseInt(setNumbers[1]);
    }

    public void showTimePickerDialog (View v)
    {
        /**
         * call to the fragment who let the user choose the time
         */
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimePicked(String time) {
        /**
         * this function called automatic when the fragment is closed
         */
        showTime.setText(time);
        whatTime(time);
    }

    public void onClick(View v)
    {
        /**
         * this function called automatic when the "start" button clicked
         */
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