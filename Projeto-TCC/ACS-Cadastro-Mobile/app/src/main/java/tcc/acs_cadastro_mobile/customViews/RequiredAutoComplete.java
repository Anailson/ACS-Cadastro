package tcc.acs_cadastro_mobile.customViews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;


public class RequiredAutoComplete extends AppCompatAutoCompleteTextView
         implements IRequiredView, TextWatcher, AdapterView.OnItemClickListener{

    boolean clicked;
    private AutoFillListener listener;

    public RequiredAutoComplete(Context context) {
        super(context);
        init();
    }

    public RequiredAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setThreshold(1);
        setAutoFillListener(getListener());
    }

    public void setAutoFillListener(AutoFillListener listener) {
        if(listener != null) {
            addTextChangedListener(this);
            setOnItemClickListener(this);
            this.listener = listener;
        } else{
            this.listener = getListener();
        }
    }

    private AutoFillListener getListener(){
        return new AutoFillListener() {
            @Override
            public void searching(EditText editText) {}

            @Override
            public void selectItem(EditText editText) {}

            @Override
            public void changedAfterSelected(EditText editText) {}
        };
    }

    @Override
    public void setTitle(TextView title) {}

    @Override
    public boolean isInvalid(String... text) {
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
    }

    @Override
    public void clearError() {

        setError(null);
        getBackground().clearColorFilter();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(listener != null && !s.toString().equals("")) {
            listener.searching(this);
            if (clicked) listener.changedAfterSelected(this);
            clicked = false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null){
            clicked = true;
            listener.selectItem(this);
        }
    }

    public interface AutoFillListener {

        void searching(EditText editText);

        void selectItem(EditText editText);

        void changedAfterSelected(EditText editText);
    }
}
