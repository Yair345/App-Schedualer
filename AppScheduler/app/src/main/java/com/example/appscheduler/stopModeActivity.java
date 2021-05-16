package com.example.appscheduler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.appscheduler.serverConnection;

public class stopModeActivity extends AppCompatActivity {
    /**
     * This class get the data (apps and time) and prepare it to send to the server
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_mode);

        // hour and minute that the user choose
        int targetHour = getIntent().getExtras().getInt("hour");
        int targetMinute = getIntent().getExtras().getInt("minute");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm"); // get the hour now
        LocalDateTime now = LocalDateTime.now();

        String[] setNumbers = dtf.format(now).split(":");
        int hour = Integer.parseInt(setNumbers[0]);
        int minute = Integer.parseInt(setNumbers[1]);

        // how long is the block
        int diffH = Math.abs(targetHour - hour);
        int diffM = Math.abs(targetMinute - minute);

        String difference = Integer.toString(diffH) + "." + Integer.toString(diffM);

        JSONObject obj = new JSONObject(); // send to the server a json string

        UserSingleton singleton = com.example.appscheduler.UserSingleton.getInstance();
        ArrayList<String> apps = new ArrayList<>();

        apps = singleton.getBlocked(); // getting the blocked apps

        for (String app: apps)
        {
            obj.put(app, difference);
        }

        serverConnection connection = new serverConnection(rot13(obj.toJSONString())); // create server connection
        new Thread(connection).start(); // need to create a new thread


        ArrayList<String> newArray = new ArrayList<>(); // delete the exist list of apps
        singleton.setBlocked(newArray);

    }

    private String rot13 ( String s )
    {
        /**
         * doing the rot13 thing
         */
        String newString = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;

            newString += c;
        }

        return newString;
    }

    public void onBackClick(View view)
    {
        /**
         * if the back button was clicked so go to the first activity
         */
        Intent intent = new Intent(this, StartModeActivity.class);
        startActivity(intent);
    }
}