package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.view.View;
import android.widget.NumberPicker;

import java.util.Calendar;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

public class CalendarController {

    private Activity activity;
    private Listeners listener;
    private ICalendarListener showCalendar;
    private int id;

    public CalendarController (Activity activity){
        this.activity = activity;
        this.showCalendar = listener = new Listeners();
    }

    public void setShowCalendar(int id, ICalendarListener showCalendar) {
        if(showCalendar != null){
            this.showCalendar = showCalendar;
            this.id = id;
        }
    }

    public View.OnClickListener getClickListener(){
        if (listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public static String[] getYears(){

        int maxValue = 150;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String [] years = new String[maxValue];

        for(int i = 0; i < maxValue; i++){
            years[i] = Integer.toString(i + currentYear - maxValue);
        }
        return years;
    }

    private void save(){
        int iDay = ((NumberPicker) activity.findViewById(R.id.npkr_day)).getValue();
        int iMonth = ((NumberPicker) activity.findViewById(R.id.npkr_month)).getValue();
        int iYear = ((NumberPicker) activity.findViewById(R.id.npkr_year)).getValue();

        showCalendar.onOk(id, new CalendarActivity.Date(iDay, iMonth, iYear));
        cancel();
    }

    private void cancel(){
        showCalendar.onCancel();
        activity.finish();
        id = 0;
        showCalendar = new Listeners();
    }


    private class Listeners implements View.OnClickListener, ICalendarListener {
        //View.OnClickListener
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_calendar_ok: save(); break;
                case R.id.btn_calendar_cancel: cancel(); break;
            }
        }

        //ShowCalendarListener
        @Override
        public void onOk(int id, CalendarActivity.Date date) {}

        @Override
        public void onCancel() {}
    }
}
