package tcc.acs_cadastro_mobile.customViews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.Spinner;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;

public class RequiredSpinner extends Spinner implements IRequiredView {

    private TextView title;

    public RequiredSpinner(Context context) {
        super(context);
        this.title = null;
    }

    public RequiredSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.title = null;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    @Override
    public boolean isInvalid(String... text) {
        if(getSelectedItemPosition() == 0){
            return true;
        }
        String upperCase = ((String) getSelectedItem()).toUpperCase();
        for(String s : text){
            if(upperCase.equals(s.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setError(CharSequence text) {
        int errorColor = getResources().getColor(R.color.error);
        getBackground().setColorFilter(errorColor, PorterDuff.Mode.SRC_ATOP);

        TextView view = (TextView) getSelectedView();
        view.setError(text);
        view.setTextColor(errorColor);
        if(title != null){
            title.setTextColor(errorColor);
        }
    }

    @Override
    public void clearError() {

        int defaultColor = getResources().getColor(R.color.error);
        getBackground().clearColorFilter();

        TextView view = (TextView) getSelectedView();
        view.setError(null);
        view.setTextColor(defaultColor);

        if(title != null){
            title.setTextColor(defaultColor);
        }
    }
}
