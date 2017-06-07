package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ResidenceStepOneController;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.AddressDataModel;

public class ResidenceStepOneFragment extends Fragment {

    private static final String ADDRESS_DATA = "ADDRESS_DATA";

    private IResidenceData residenceData;
    private AddressDataModel addressData;

    private ResidenceStepOneController controller;
    private Spinner spnPlaceType, spnUf, spnCity;
    private EditText edtPlaceName, edtNumber, edtComplement, edtNeighborhood, edtCep, edtHomePhone,
            edtReferencePhone;

    public static Fragment newInstance(AddressDataModel addressData) {
        Fragment fragment = new ResidenceStepOneFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ADDRESS_DATA, addressData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        residenceData = (IResidenceData) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater layout, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = layout.inflate(R.layout.content_rsd_add_1, container, false);
        addressData = (AddressDataModel) getArguments().getSerializable(ADDRESS_DATA);
        controller = new ResidenceStepOneController(this);

        edtPlaceName = (EditText) view.findViewById(R.id.edt_rsd_place_name);
        edtNumber = (EditText) view.findViewById(R.id.edt_rsd_number);
        edtComplement = (EditText) view.findViewById(R.id.edt_rsd_complement);
        edtNeighborhood = (EditText) view.findViewById(R.id.edt_rsd_neighborhood);
        edtCep = (EditText) view.findViewById(R.id.edt_rsd_cep);
        edtHomePhone = (EditText) view.findViewById(R.id.edt_rsd_home_phone);
        edtReferencePhone = (EditText) view.findViewById(R.id.edt_rsd_reference_phone);
        spnPlaceType = (Spinner) view.findViewById(R.id.spn_rsd_place_type);
        spnUf = (Spinner) view.findViewById(R.id.spn_rsd_uf);
        spnCity = (Spinner) view.findViewById(R.id.spn_rsd_city);

        spnPlaceType.setAdapter(controller.getSpinnerAdapter(R.array.place_type));
        spnUf.setAdapter(controller.getSpinnerAdapter(R.array.uf));
        spnCity.setAdapter(controller.getSpinnerAdapter(R.array.se_cities));

        spnUf.setOnItemSelectedListener(controller.getItemSelectedListener());

        if(addressData != null){
            fillFields();
        }

        //TODO: use GPS location to fill some fields (...?)
        //DefineLocation defineLocation = new DefineLocation(getContext());
        //defineLocation.searchLocation();
        //defineLocation.execute();
        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }

    private void getFields(){
        String placeType = controller.getFields(spnPlaceType);
        String placeName = controller.getFields(edtPlaceName);
        int number = Integer.parseInt(controller.getFields(edtNumber));
        String complement = controller.getFields(edtComplement);
        String neighborhood = controller.getFields(edtNeighborhood);
        String uf = controller.getFields(spnUf);
        String city = controller.getFields(spnCity);
        String cep = controller.getFields(edtCep);
        String phoneHome = controller.getFields(edtHomePhone);
        String phoneReference = controller.getFields(edtReferencePhone);

        addressData = new AddressDataModel(placeType, placeName, number, complement, neighborhood,
                uf, city, cep, phoneHome, phoneReference);
        residenceData.send(addressData);
    }

    private void fillFields(){

        int indexUf = controller.getIndex(addressData.getUf(), R.array.uf);

        controller.fillField(spnPlaceType, controller.getIndex(addressData.getPlaceType(), R.array.place_type));
        controller.fillField(edtPlaceName, addressData.getPlaceName());
        controller.fillField(edtNumber, String.valueOf(addressData.getNumber()));
        controller.fillField(edtComplement, addressData.getComplement());
        controller.fillField(edtNeighborhood, addressData.getNeighborhood());
        controller.fillField(spnUf, indexUf);
        controller.fillField(spnCity, controller.getCityIndex(indexUf, addressData.getCity()));
        controller.fillField(edtCep, addressData.getCep());
        controller.fillField(edtHomePhone, addressData.getPhoneHome());
        controller.fillField(edtReferencePhone, addressData.getPhoneReference());
    }
}