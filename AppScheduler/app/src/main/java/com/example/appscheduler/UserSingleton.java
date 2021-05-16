package com.example.appscheduler;

import java.util.ArrayList;

public class UserSingleton {
    /**
     * This class is my "personal" memory where I can save anything
     */
    private static UserSingleton instance = null; // only one instance

    private ArrayList<String> candidates; // all apps
    public ArrayList<String> blocked; // blocked apps

    public static UserSingleton getInstance(){
        /**
         * The func returns the instance of the class.
         * if it was made already so return the instance.
         */
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
