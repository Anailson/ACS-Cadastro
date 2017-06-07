package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;

public class ResidenceStepOneController extends StepsController {

    private final int AL = 1;
    private final int BA = 2;
    private final int SE = 3;

    private Fragment fragment;
    private AdapterView.OnItemSelectedListener itemSelectedListener;

    public ResidenceStepOneController(Fragment fragment) {
        super(fragment);
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }

    public AdapterView.OnItemSelectedListener getItemSelectedListener() {
        if (itemSelectedListener == null) {
            itemSelectedListener = new ItemSelectedListener();
        }
        return itemSelectedListener;
    }

    public int getCityIndex(int indexUf, String city) {

        int array;
        switch (indexUf){
            case AL: array = R.array.al_cities; break;
            case BA: array = R.array.ba_cities; break;
            case SE: array = R.array.se_cities; break;
            default: throw new IllegalArgumentException();
        }
        return getIndex(city, array);
    }

    private void setCities(int index) {
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_rsd_city);
        switch (index){
            case AL: spinner.setAdapter(getSpinnerAdapter(R.array.al_cities)); break;
            case BA: spinner.setAdapter(getSpinnerAdapter(R.array.ba_cities)); break;
            case SE: spinner.setAdapter(getSpinnerAdapter(R.array.se_cities)); break;
            default: fillField(spinner, 0); break;
        }
        enableView(spinner, index != 0);
    }

    private class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {

            switch (adapterView.getId()) {
                case R.id.spn_rsd_uf: setCities(index);break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
