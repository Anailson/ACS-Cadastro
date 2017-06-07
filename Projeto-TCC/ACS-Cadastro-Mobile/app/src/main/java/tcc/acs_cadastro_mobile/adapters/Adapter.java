package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class Adapter{

    private Context context;

    public Adapter(Context context){
        this.context = context;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource){

        String [] values = context.getResources().getStringArray(arrayResource);
        return getSpinnerAdapter(values);
    }

    public ArrayAdapter<String> getSpinnerAdapter(String [] values){
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, values);
    }
}
