package com.example.appscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ChooseAppsActivity extends AppCompatActivity {
    /**
     * The class was made for the choice of the applications
     */
    private Button backButton;
    private ListView appList;
    protected ArrayList<String> candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_apps);
        
        ArrayList<AppInfo> applications = new ArrayList<>(); // list of all the applications that installed into the phone (for the adapter)
        candidates = new ArrayList<>();

        ListView appList = findViewById(R.id.appsList);
        Button backButton = findViewById(R.id.backButton);

        PackageManager pm = getPackageManager(); // how I'm going to get all the apps
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);

        List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();

        int myFlags = ApplicationInfo.CATEGORY_AUDIO |
                ApplicationInfo.CATEGORY_GAME |
                ApplicationInfo.CATEGORY_IMAGE |
                ApplicationInfo.CATEGORY_MAPS |
                ApplicationInfo.CATEGORY_NEWS |
                ApplicationInfo.CATEGORY_SOCIAL |
                ApplicationInfo.CATEGORY_VIDEO |
                ApplicationInfo.CATEGORY_UNDEFINED |
                ApplicationInfo.CATEGORY_PRODUCTIVITY; // flags for the require apps

        String name = "";

        for(ApplicationInfo app : apps) {
            if ( (app.flags & myFlags) != 0 && app.icon != 0 && (app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) // if the app has an icon so I want to block it.
                installedApps.add(app);
        }

        for (ApplicationInfo app : installedApps)
        {
            name = (String)pm.getApplicationLabel(app);
            applications.add(new AppInfo(name, pm.getApplicationIcon(app)));
            candidates.add(name);
        }

        CustomAdapter adapter = new CustomAdapter(this, applications);

        appList.setAdapter(adapter);

    }

    public void onBackClick(View view)
    {
        /**
         * if the back button was clicked so this function is automatically called
         */
        Intent intent = new Intent(this, StartModeActivity.class);
        startActivity(intent);
    }

}