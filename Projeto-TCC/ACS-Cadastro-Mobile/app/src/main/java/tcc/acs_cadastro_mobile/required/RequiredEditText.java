package tcc.acs_cadastro_mobile.required;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;

public class RequiredEditText extends android.support.design.widget.TextInputEditText implements IRequiredView {

    private TextView title;

    public RequiredEditText(Context context) {
        super(context);
        this.title = null;
    }

    public RequiredEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.title = null;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    @Override
    public boolean isInvalid(String text){
        return getText().toString().toUpperCase().equals(text.toUpperCase());
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
