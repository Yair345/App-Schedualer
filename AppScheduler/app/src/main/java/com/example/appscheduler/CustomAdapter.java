package com.example.appscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.appscheduler.AppInfo;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private java.util.ArrayList<AppInfo> apps;

    public CustomAdapter(Context context, ArrayList<AppInfo> apps)
    {
        this.context = context;
        this.apps = apps;
    }

    @Override
    public int getCount() {
        if (apps != null && apps.size() > 0)
            return apps.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return apps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.app_set, parent, false);

        LinearLayout mainLayout = convertView.findViewById(R.id.mainLayout);
        ImageView appIcon = convertView.findViewById(R.id.appIcon);
        TextView appName = convertView.findViewById(R.id.appName);
        Switch onOffApp = convertView.findViewById(R.id.switch1);

        appIcon.setImageDrawable(apps.get(position).getIcon());
        appName.setText(apps.get(position).getName());

        onOffApp.setChecked(false);

        UserSingleton singleton = com.example.appscheduler.UserSingleton.getInstance();
        singleton.blocked = new ArrayList<>();


        onOffApp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked) {
                    onOffApp.setChecked(true);
                    singleton.blocked.add(apps.get(position).getName());
                 }
                 else {
                     onOffApp.setChecked(false);
                     singleton.blocked.remove(apps.get(position).getName());
                 }
             }
        });

        return convertView;
    }
}