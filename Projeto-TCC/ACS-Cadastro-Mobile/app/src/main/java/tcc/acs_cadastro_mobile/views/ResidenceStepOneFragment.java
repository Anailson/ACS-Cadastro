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
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.persistence.AddressDataPersistence;
import tcc.acs_cadastro_mobile.customViews.RequiredEditText;
import tcc.acs_cadastro_mobile.customViews.RequiredSpinner;
import tcc.acs_cadastro_mobile.subModels.CityLocation;
import tcc.acs_cadastro_mobile.subModels.Phones;
import tcc.acs_cadastro_mobile.subModels.StreetLocation;

public class ResidenceStepOneFragment extends Fragment implements IRequiredFields {

    private static final String ADDRESS_DATA = "ADDRESS_DATA";

    private IResidenceData residenceData;
    private AddressDataModel addressData;

    private ResidenceStepOneController controller;
    private RequiredEditText edtPlaceName, edtNumber, edtNeighborhood, edtCep;
    private RequiredSpinner spnUf, spnCity;

    private Spinner spnPlaceType;
    private EditText edtComplement, edtHomePhone, edtReferencePhone;

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

        edtPlaceName = (RequiredEditText) view.findViewById(R.id.edt_rsd_place_name);
        edtNumber = (RequiredEditText) view.findViewById(R.id.edt_rsd_number);
        edtComplement = (EditText) view.findViewById(R.id.edt_rsd_complement);
        edtNeighborhood = (RequiredEditText) view.findViewById(R.id.edt_rsd_neighborhood);
        edtCep = (RequiredEditText) view.findViewById(R.id.edt_rsd_cep);
        edtHomePhone = (EditText) view.findViewById(R.id.edt_rsd_home_phone);
        edtReferencePhone = (EditText) view.findViewById(R.id.edt_rsd_reference_phone);
        spnPlaceType = (Spinner) view.findViewById(R.id.spn_rsd_place_type);
        spnUf = (RequiredSpinner) view.findViewById(R.id.spn_rsd_uf);
        spnCity = (RequiredSpinner) view.findViewById(R.id.spn_rsd_city);

        spnPlaceType.setAdapter(controller.getAdapter(R.array.place_type));
        spnUf.setAdapter(controller.getAdapter(R.array.uf));
        spnCity.setAdapter(controller.getAdapter(R.array.se_cities));

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

        StreetLocation street = controller.getStreetLocation(spnPlaceType, edtPlaceName, edtNumber, edtComplement);
        CityLocation city = controller.getCityLocation(edtNeighborhood, spnUf, spnCity, edtCep);
        Phones phones = controller.getPhones(edtHomePhone, edtReferencePhone);

        addressData = AddressDataPersistence.getInstance(street, city, phones);
        residenceData.send(addressData);
        super.onDetach();
    }

    @Override
    public boolean isRequiredFieldsFilled(){
        return controller.isRequiredFieldsFilled(edtPlaceName, edtNumber, edtNeighborhood, spnUf,
                spnCity, edtCep);
    }

    private void fillFields(){

        int indexUf = controller.getIndex(addressData.getUf(), R.array.uf);

        controller.fillField(spnPlaceType, controller.getIndex(addressData.getPlaceType(), R.array.place_type));
        controller.fillField(edtPlaceName, addressData.getPlaceName());
        controller.fillField(edtNumber, StepsController.getEmptyOrValue(addressData.getNumber()));
        controller.fillField(edtComplement, addressData.getComplement());
        controller.fillField(edtNeighborhood, addressData.getNeighborhood());
        controller.fillField(spnUf, indexUf);
        controller.fillField(spnCity, controller.getCityIndex(indexUf, addressData.getCityName()));
        controller.fillField(edtCep, StepsController.getEmptyOrValue(addressData.getCep()));
        controller.fillField(edtHomePhone, addressData.getPhoneHome());
        controller.fillField(edtReferencePhone, addressData.getPhoneReference());
    }
}