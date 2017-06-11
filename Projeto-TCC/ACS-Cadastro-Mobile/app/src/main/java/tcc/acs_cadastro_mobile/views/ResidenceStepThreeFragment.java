package tcc.acs_cadastro_mobile.views;

import android.content.Context;
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
import tcc.acs_cadastro_mobile.adapters.HousingHistoricalListAdapter;
import tcc.acs_cadastro_mobile.controllers.ResidenceStepThreeController;
import tcc.acs_cadastro_mobile.interfaces.IResidenceData;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;

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

        lvwResponsibles = (ListView) view.findViewById(R.id.lvw_responsible);
        Button btnNewResp = (Button) view.findViewById(R.id.btn_rsd_new_resp);
        controller = new ResidenceStepThreeController(this);

        //lvwResponsible.setOnItemClickListener(controller.getItemClickListener());
        btnNewResp.setOnClickListener(controller.getClickListener());

        if(housingHistorical != null){
            fillFields();
        }
        return view;
    }

    @Override
    public void onDetach() {
        housingHistorical = controller.getHousingHistorical(lvwResponsibles);
        residenceData.send(housingHistorical);
        super.onDetach();
    }

    private void fillFields(){
        lvwResponsibles.setAdapter(new HousingHistoricalListAdapter(getContext(), housingHistorical));
    }
}