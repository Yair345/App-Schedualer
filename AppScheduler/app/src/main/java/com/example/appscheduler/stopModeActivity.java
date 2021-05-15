package com.example.appscheduler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.appscheduler.serverConnection;

public class stopModeActivity extends AppCompatActivity {
    Socket socket;
    PrintWriter printWriter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_mode);

        int targetHour = getIntent().getExtras().getInt("hour");
        int targetMinute = getIntent().getExtras().getInt("minute");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        Log.d("MyTag", dtf.format(now));

        String[] setNumbers = dtf.format(now).split(":");
        int hour = Integer.parseInt(setNumbers[0]);
        int minute = Integer.parseInt(setNumbers[1]);

        int diffH = Math.abs(targetHour - hour);
        int diffM = Math.abs(targetMinute - minute);

        String difference = Integer.toString(diffH) + "." + Integer.toString(diffM);

        JSONObject obj = new JSONObject();

        UserSingleton singleton = com.example.appscheduler.UserSingleton.getInstance();
        ArrayList<String> apps = new ArrayList<>();

        apps = singleton.getBlocked();

        for (String app: apps)
        {
            obj.put(app, difference);
        }

        serverConnection connection = new serverConnection(obj.toJSONString());
        new Thread(connection).start();

//        sendMsg(obj.toJSONString());

        Log.d("MyTag", obj.toJSONString());

    }

    private String rot13 ( String s )
    {
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

    public void onBackClick(View view){
        Intent intent = new Intent(this, StartModeActivity.class);
        startActivity(intent);
    }
}