package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class SpinnerAdapter {

    private Context context;

    public SpinnerAdapter(Context context){
        this.context = context;
    }

    public ArrayAdapter<String> getAdapter(int arrayResource){

        String [] values = context.getResources().getStringArray(arrayResource);
        return getAdapter(values);
    }

    public ArrayAdapter<String> getAdapter(String [] values){
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, values);
    }
}
