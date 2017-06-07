package tcc.acs_cadastro_mobile.views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.NumberPicker;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CalendarController;

public class CalendarActivity extends AppCompatActivity {

    public static final int DAY = 0;
    public static final int MONTH = 1;
    public static final int YEAR = 2;
    public static final int BIRTH = 3;
    public static final int RESP_BIRTH = 1;
    public static final String VALUE = "birth_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_calendar);

        CalendarController controller = new CalendarController(this);

        NumberPicker npkrDay = (NumberPicker) findViewById(R.id.npkr_day);
        NumberPicker npkrMonth = (NumberPicker) findViewById(R.id.npkr_month);
        NumberPicker npkrYear = (NumberPicker) findViewById(R.id.npkr_year);

        setNumberPicker(npkrDay, R.array.days);
        setNumberPicker(npkrMonth, R.array.months);
        setNumberPicker(npkrYear, controller.getYears());

        Button btnOk = (Button) findViewById(R.id.btn_calendar_ok);
        Button btnCancel = (Button) findViewById(R.id.btn_calendar_cancel);

        btnOk.setOnClickListener(controller.getClickListener());
        btnCancel.setOnClickListener(controller.getClickListener());

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

    private NumberPicker setNumberPicker(NumberPicker picker, int arrayResource){
        String [] values = getResources().getStringArray(arrayResource);
        return setNumberPicker(picker, values);
    }

    private NumberPicker setNumberPicker(NumberPicker picker, String [] values){
        picker.setMinValue(0);
        picker.setMaxValue(values.length - 1);
        picker.setDisplayedValues(values);
        return picker;
    }
}
