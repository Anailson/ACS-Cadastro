package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import java.io.Serializable;
import java.util.Formatter;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CalendarController;
import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;

public class CalendarActivity extends AppCompatActivity implements Serializable {

    public static final int RESP_BIRTH = 1;
    public static final int NEW_RESP_BIRTH = 2;
    public static final int BIRTH = 3;
    public static final String ID = "ID";
    public static final String DATA = "DATA";

    private static Fragment fragment;

    public static void newInstance(Context context, int id, ICalendarListener listener){
        CalendarActivity.fragment = CalendarFragment.newInstance(id, listener);
        Intent intent = new Intent(context, CalendarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.float_calendar_activity);

        defineFloating();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_calendar, fragment).commit();
    }

    private void defineFloating(){

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * .9);
        int height = (int)(dm.heightPixels * .6);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            width = (int) (dm.widthPixels * .6);
            height = (int)(dm.heightPixels * .9);
        }

        getWindow().setLayout(width, height);
    }

    public static class Date implements Serializable{

        private final String DEFAULT = "-";

        private int dayIndex, monthIndex, yearIndex;
        private String monthText;

        public Date(int day, int month, int year) {
            this.dayIndex = day;
            this.monthIndex = month;
            this.yearIndex = year;
            this.monthText = DEFAULT;
        }

        public int getDay() {
            return dayIndex + 1;
        }

        public int getMonth() {
            return monthIndex + 1;
        }

        public String getMonthValue(Context context){
            if(monthText.equals(DEFAULT)){
                monthText = context.getResources().getStringArray(R.array.months)[monthIndex];
            }
            return monthText;
        }

        public int getYear() {
            return Integer.parseInt(CalendarController.getYears()[yearIndex]);
        }

        public String formattedDate(Context context){
            return new Formatter().format("%d/%s/%d", getDay(), getMonthValue(context), getYear()).toString();
        }

        public String formattedSimpleDate(){
            return new Formatter().format("%d/%d/%d", getDay(), getMonth(), getYear()).toString();
        }
    }
}
