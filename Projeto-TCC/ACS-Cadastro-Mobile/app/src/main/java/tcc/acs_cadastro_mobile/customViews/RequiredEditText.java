package tcc.acs_cadastro_mobile.customViews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatEditText;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;

public class RequiredEditText extends AppCompatEditText implements IRequiredView {

    private TextView title;

    public RequiredEditText(Context context) {
        super(context);
    }

    public RequiredEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RequiredEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    @Override
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
