package com.example.appscheduler;

import android.graphics.drawable.Drawable;

public class AppInfo {
    /**
    * The class was made for the list of applications and what important for me.
    */
    private String name;
    private Drawable icon;
    private boolean blocked;



    public AppInfo(String name, Drawable icon)
    {
        /***
         * Constructor
         */
        this.icon = icon;
        this.name = name;
        this.blocked = false;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
