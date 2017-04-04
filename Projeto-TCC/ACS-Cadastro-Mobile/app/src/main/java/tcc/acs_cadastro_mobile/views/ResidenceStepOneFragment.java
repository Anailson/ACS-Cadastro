package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.AddressDataModel;

public class ResidenceStepOneFragment extends Fragment {

    private static final String ADDRESS_DATA = "ADDRESS_DATA";

    private IResidenceData residenceData;
    private AddressDataModel addressData;

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


        if(addressData == null){
            fillFields();
        }

        return view;
    }

    private void fillFields(){
        
    }
}