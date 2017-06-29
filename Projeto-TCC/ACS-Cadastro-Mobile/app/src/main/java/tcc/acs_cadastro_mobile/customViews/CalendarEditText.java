package tcc.acs_cadastro_mobile.customViews;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;
import tcc.acs_cadastro_mobile.views.CalendarActivity;

public class CalendarEditText extends AppCompatEditText
        implements View.OnClickListener {

    private Context context;
    private ICalendarListener listener;

    public CalendarEditText(Context context) {
        super(context);
        init(context);
    }

    public CalendarEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setShowCalendarListener(ICalendarListener listener) {
        this.listener = listener;
    }

    private void init(Context context) {
        this.context = context;
        setFocusableInTouchMode(false);
        setOnClickListener(this);
        setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_menu_my_calendar, 0);
    }

    @Override
    public void onClick(View v) {
        CalendarActivity.newInstance(context, getId(), listener);
    }
}
