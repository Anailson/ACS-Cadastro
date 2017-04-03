package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenStepOneController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.HealthConditionsDataModel;

public class CitizenStepThreeFragment extends Fragment {

    private static final String HEALTH_CONDITIONS_DATA = "HEALTH_CONDITIONS_DATA";

    private CitizenStepOneController controller;
    private ICitizenData citizenData;
    private HealthConditionsDataModel healthConditions;

    public static Fragment newInstance(HealthConditionsDataModel healthConditions){
        Bundle bundle = new Bundle();
        bundle.putSerializable(HEALTH_CONDITIONS_DATA, healthConditions);
        Fragment fragment = new CitizenStepThreeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        citizenData = (ICitizenData) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_ctz_add_3, container, false);
        controller = new CitizenStepOneController(this);
        healthConditions = (HealthConditionsDataModel) getArguments().getSerializable(HEALTH_CONDITIONS_DATA);

        Spinner spnCtzWeight = (Spinner) view.findViewById(R.id.spn_ctz_weight);

        spnCtzWeight.setAdapter(controller.getSpinnerAdapter(R.array.weight));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        healthConditions = new HealthConditionsDataModel();
        citizenData.send(healthConditions);
    }
}
