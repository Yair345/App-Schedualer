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

import com.example.appscheduler.UserSingleton;


public class ChooseAppsActivity extends AppCompatActivity {
    private Button backButton;
    private ListView appList;
    protected ArrayList<String> candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_apps);
        
        ArrayList<AppInfo> applications = new ArrayList<>();
        candidates = new ArrayList<>();

        ListView appList = findViewById(R.id.appsList);
        Button backButton = findViewById(R.id.backButton);

        PackageManager pm = getPackageManager();
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
                ApplicationInfo.CATEGORY_PRODUCTIVITY;

        String name = "";

        for(ApplicationInfo app : apps) {
            if ( (app.flags & myFlags) != 0 && app.icon != 0 && (app.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
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
        Intent intent = new Intent(this, StartModeActivity.class);
        startActivity(intent);
    }

}

