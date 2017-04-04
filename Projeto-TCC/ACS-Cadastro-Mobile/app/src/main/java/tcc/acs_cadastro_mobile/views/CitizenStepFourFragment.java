package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenStepFourController;
import tcc.acs_cadastro_mobile.interfaces.ICitizenData;
import tcc.acs_cadastro_mobile.models.StreetSituationDataModel;

public class CitizenStepFourFragment extends Fragment {

    private static final String STREET_SITUATION_DATA = "STREET_SITUATION_DATA";

    private CitizenStepFourController controller;
    private StreetSituationDataModel streetSituation;
    private ICitizenData citizenData;


    public static Fragment newInstance(StreetSituationDataModel streetSituation){
        Bundle bundle = new Bundle();
        bundle.putSerializable(STREET_SITUATION_DATA, streetSituation);
        Fragment fragment = new CitizenStepFourFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_ctz_add_4, container, false);
        controller = new CitizenStepFourController(this);
        streetSituation = (StreetSituationDataModel) getArguments().getSerializable(STREET_SITUATION_DATA);

        Spinner spnCtzStreetTime = (Spinner) view.findViewById(R.id.spn_ctz_street_time);
        Spinner spnCtzFoodPerDay = (Spinner) view.findViewById(R.id.spn_ctz_food_per_day);

        spnCtzStreetTime.setAdapter(controller.getSpinnerAdapter(R.array.street_time));
        spnCtzFoodPerDay.setAdapter(controller.getSpinnerAdapter(R.array.food_per_day));

        if(streetSituation != null){
            fillFields();
        }

        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }

    private void getFields(){

    }

    private void fillFields(){

        streetSituation = new StreetSituationDataModel();
        citizenData.send(streetSituation);
    }
}

