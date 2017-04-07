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
import tcc.acs_cadastro_mobile.gps.DefineLocation;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.AddressDataModel;

public class ResidenceStepOneFragment extends Fragment {

    private static final String ADDRESS_DATA = "ADDRESS_DATA";

    private IResidenceData residenceData;
    private AddressDataModel addressData;

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

        spnPlaceType = (Spinner) view.findViewById(R.id.spn_rsd_place_type);
        edtPlaceName = (EditText) view.findViewById(R.id.edt_rsd_place_name);
        edtNumber = (EditText) view.findViewById(R.id.edt_rsd_number);
        edtComplement = (EditText) view.findViewById(R.id.edt_rsd_complement);
        edtNeighborhood = (EditText) view.findViewById(R.id.edt_rsd_neighborhood);
        spnUf = (Spinner) view.findViewById(R.id.spn_rsd_uf);
        spnCity = (Spinner) view.findViewById(R.id.spn_rsd_city);
        edtCep = (EditText) view.findViewById(R.id.edt_rsd_cep);
        edtHomePhone = (EditText) view.findViewById(R.id.edt_rsd_home_phone);
        edtReferencePhone = (EditText) view.findViewById(R.id.edt_rsd_reference_phone);

        if(addressData == null){
            fillFields();
        }

        DefineLocation defineLocation = new DefineLocation(getContext());
        defineLocation.searchLocation();
        defineLocation.execute();
        return view;
    }

    private void fillFields(){

    }
}