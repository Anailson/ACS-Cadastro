package tcc.acs_cadastro_mobile.customViews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;

public class RequiredCalendarEditText extends CalendarEditText implements IRequiredView {

    private TextView title;

    public RequiredCalendarEditText(Context context) {
        super(context);
    }

    public RequiredCalendarEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    } @Override
    public boolean isInvalid(String... text){
        String upperCase = getText().toString().toUpperCase();
        for(String s : text){
            if(upperCase.equals(s.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setTitle(TextView title) {
        this.title = title;
    }

    @Override
    public void setError(CharSequence error) {
        super.setError(error);

        int errorColor = getResources().getColor(R.color.error);
        getBackground().setColorFilter(errorColor, PorterDuff.Mode.SRC_IN);

        if(title != null){
            title.setTextColor(errorColor);
        }
    }

    @Override
    public void clearError() {
        setError(null);

        getBackground().clearColorFilter();
        if(title != null){
            title.setTextColor(getResources().getColor(R.color.color_default));
        }
    }
}
