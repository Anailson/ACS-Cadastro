package tcc.acs_cadastro_mobile.required;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;

public class RequiredRadioGroup extends RadioGroup implements IRequiredView {

    private TextView title;

    public RequiredRadioGroup(Context context) {
        super(context);
    }

    public RequiredRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isInvalid(String index) {
        return getCheckedRadioButtonId() == -1;
    }

    @Override
    public void setTitle(TextView title) {
        this.title = title;
    }

    @Override
    public void setError(CharSequence text) {

        int errorColor = getResources().getColor(R.color.error);

        for(int i = 0; i< getChildCount(); i++){
            if(getChildAt(i) instanceof RadioButton){
                RadioButton child = (RadioButton) getChildAt(i);
                child.setTextColor(errorColor);
            }
        }
        if(title != null){
            title.setTextColor(errorColor);
        }
    }

    @Override
    public void clearError() {

        int defaultColor = getResources().getColor(R.color.color_default);

        for(int i = 0; i< getChildCount(); i++){
            if(getChildAt(i) instanceof RadioButton){
                RadioButton child = (RadioButton) getChildAt(i);
                child.setTextColor(defaultColor);
            }
        }
        if(title != null){
            title.setTextColor(defaultColor);
        }
    }
}
