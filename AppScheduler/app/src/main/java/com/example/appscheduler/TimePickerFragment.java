package com.example.appscheduler;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    /**
     * This class create the fragment for choose the time
     */

    private TimePickedListener listener;

    public static interface TimePickedListener {
        /**
         * create an interface that needed because it was implements
         */
        void onTimePicked(String time);
    }

    @Override
    public TimePickerDialog onCreateDialog(Bundle savedInstanceState) {
        /**
         * create the "window" that showed up
         */
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        listener = (TimePickedListener) getActivity();

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        /**
         * send the input from the user
         */
        String printMinute = "";
        if(minute < 10)
            printMinute = "0" + Integer.toString(minute);
        else
            printMinute = Integer.toString(minute);
        listener.onTimePicked(Integer.toString(hourOfDay) + ":" + printMinute);
    }
}