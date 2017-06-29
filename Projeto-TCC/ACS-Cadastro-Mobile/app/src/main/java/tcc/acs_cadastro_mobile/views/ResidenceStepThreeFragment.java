package tcc.acs_cadastro_mobile.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ResidenceStepThreeController;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;
import tcc.acs_cadastro_mobile.persistence.HousingHistoricalPersistence;

import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.BIRTH_DATE;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.FAMILY_INCOME;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.FAMILY_RECORD;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.LIVES_SINCE;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.MEMBERS;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.MOVED;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.NUM_SUS;
import static tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity.RESULT;

public class ResidenceStepThreeFragment extends Fragment{

    private static final String HOUSING_HISTORICAL = "HOUSING_HISTORICAL";

    private IResidenceData residenceData;
    private RealmList<HousingHistoricalModel> housingHistorical;
    private ResidenceStepThreeController controller;

    private ListView lvwResponsibles;

    public static Fragment newInstance(RealmList<HousingHistoricalModel> housingHistorical) {
        Fragment fragment = new ResidenceStepThreeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(HOUSING_HISTORICAL, ResidenceStepThreeController.getArray(housingHistorical));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        residenceData = (IResidenceData) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_rsd_add_3, container, false);

        housingHistorical = ResidenceStepThreeController.getList(getArguments(), HOUSING_HISTORICAL);

        Button btnNewResp = (Button) view.findViewById(R.id.btn_rsd_new_resp);
        controller = new ResidenceStepThreeController(this);
        lvwResponsibles = (ListView) view.findViewById(R.id.lvw_responsible);

        //lvwResponsible.setOnItemClickListener(controller.getItemClickListener());
        btnNewResp.setOnClickListener(controller.getClickListener());

        fillFields();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if(requestCode == RESULT && resultCode == Activity.RESULT_OK){

            long numSus = intent.getLongExtra(NUM_SUS, 0);
            long record = intent.getLongExtra(FAMILY_RECORD, 0);
            String birthDate = intent.getStringExtra(BIRTH_DATE);
            String income = intent.getStringExtra(FAMILY_INCOME);
            int members = intent.getIntExtra(MEMBERS, 0);
            String livesSince = intent.getStringExtra(LIVES_SINCE);
            boolean moved = intent.getBooleanExtra(MOVED, false);

            HousingHistoricalModel historical = HousingHistoricalPersistence.getHousingHistoricalModel(
                    numSus, record, birthDate, income, members, livesSince, moved);
            housingHistorical.add(historical);
            fillFields();
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onDetach() {
        housingHistorical = controller.getHousingHistorical(lvwResponsibles);
        residenceData.send(housingHistorical);
        super.onDetach();
    }

    private void fillFields(){
        lvwResponsibles.setAdapter(controller.getAdapter(getContext(),housingHistorical));
    }
}