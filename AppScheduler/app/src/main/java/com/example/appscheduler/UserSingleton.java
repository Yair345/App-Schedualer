package com.example.appscheduler;

import java.util.ArrayList;

public class UserSingleton {
    private static UserSingleton instance = null;

    private ArrayList<String> candidates;
    public ArrayList<String> blocked;

    public static UserSingleton getInstance(){
        if (instance == null)
            instance = new UserSingleton();
        return instance;
    }

    public ArrayList<String> getCandidates(){
        return this.candidates;
    }

    public void setCandidates(ArrayList<String> apps){
        this.candidates = apps;
    }

    public ArrayList<String> getBlocked() {
        return blocked;
    }

    public void setBlocked(ArrayList<String> blocked) {
        this.blocked = blocked;
    }
}
