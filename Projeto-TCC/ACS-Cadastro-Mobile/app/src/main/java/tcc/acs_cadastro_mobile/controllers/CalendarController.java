package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

public class CalendarController {

    private Activity activity;
    private ClickListener listener;

    public CalendarController (Activity activity){
        this.activity = activity;
    }

    public View.OnClickListener getClickListener(){
        if (listener == null){
            listener = new ClickListener();
        }
        return listener;
    }

    private void save(){
        String [] date = new String[3];
        int iDay = ((NumberPicker) activity.findViewById(R.id.npkr_day)).getValue();
        int iMonth = ((NumberPicker) activity.findViewById(R.id.npkr_month)).getValue();
        int iYear = ((NumberPicker) activity.findViewById(R.id.npkr_year)).getValue();

        date[CalendarActivity.DAY] = activity.getResources().getStringArray(R.array.days)[iDay];
        date[CalendarActivity.MONTH] = activity.getResources().getStringArray(R.array.months)[iMonth];
        date[CalendarActivity.YEAR] = getYears()[iYear];

        Intent result = new Intent();
        result.putExtra(CalendarActivity.VALUE, date);
        activity.setResult(Activity.RESULT_OK, result);

        Log.e("SAVE", date[CalendarActivity.DAY] + "/" + date[CalendarActivity.MONTH] + "/" + date[CalendarActivity.YEAR]);
        cancel();
    }

    private void cancel(){
        activity.finish();
    }


    public String[] getYears(){

        int maxValue = 150;
        int currentYear = 2017;
        String [] years = new String[maxValue];

        for(int i = 0; i < maxValue; i++){
            years[i] = Integer.toString(i + currentYear - maxValue);
        }
        return years;
    }


    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_calendar_ok: save(); break;
                case R.id.btn_calendar_cancel: cancel(); break;
            }
        }
    }
}
