package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.persistence.AddressDataPersistence;
import tcc.acs_cadastro_mobile.subModels.CityLocation;
import tcc.acs_cadastro_mobile.subModels.Phones;
import tcc.acs_cadastro_mobile.subModels.StreetLocation;

public class ResidenceStepOneController extends StepsController {

    private final int AL = 1;
    private final int BA = 2;
    private final int SE = 3;

    private Fragment fragment;
    private AdapterView.OnItemSelectedListener itemSelectedListener;

    public ResidenceStepOneController(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public AdapterView.OnItemSelectedListener getItemSelectedListener() {
        if (itemSelectedListener == null) {
            itemSelectedListener = new ItemSelectedListener();
        }
        return itemSelectedListener;
    }

    public StreetLocation getStreetLocation(Spinner spnPlaceType, EditText edtPlaceName,EditText edtNumber,
                                            EditText edtComplement) {
        return AddressDataPersistence.getStreetLocation(getFields(spnPlaceType), getFields(edtPlaceName),
                getInt(edtNumber), getFields(edtComplement));
    }

    public CityLocation getCityLocation(EditText edtNeighborhood, Spinner spnUf, Spinner spnCity,
                                        EditText edtCep) {
        return AddressDataPersistence.getCityLocation(getFields(edtNeighborhood), getFields(spnUf),
                getFields(spnCity), getLong(edtCep));
    }

    public Phones getPhones(EditText edtHomePhone, EditText edtReferencePhone) {
        return AddressDataPersistence.getPhones(getFields(edtHomePhone), getFields(edtReferencePhone));
    }

    public boolean isRequiredFieldsFilled(IRequiredView edtPlaceName, IRequiredView edtNumber,
                IRequiredView edtNeighborhood, IRequiredView spnUf, IRequiredView spnCity, IRequiredView edtCep) {
        startErrors();
        applyError(edtPlaceName);
        applyError(edtNumber);
        applyError(edtNeighborhood);
        applyError(spnUf);
        applyError(spnCity);
        applyError(edtCep);
        return hasError();
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
            case AL: spinner.setAdapter(getAdapter(R.array.al_cities)); break;
            case BA: spinner.setAdapter(getAdapter(R.array.ba_cities)); break;
            case SE: spinner.setAdapter(getAdapter(R.array.se_cities)); break;
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
