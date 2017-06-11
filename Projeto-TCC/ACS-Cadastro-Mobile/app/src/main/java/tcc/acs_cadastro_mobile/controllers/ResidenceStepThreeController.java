package tcc.acs_cadastro_mobile.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;
import tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity;

public class ResidenceStepThreeController {

    private Fragment fragment;

    private LvwResponsibleItemClickListener itemClickListener;
    private NewResponsibleClickListener clickListener;

    public ResidenceStepThreeController(Fragment fragment){
        this.fragment = fragment;
    }

    public AdapterView.OnItemClickListener getItemClickListener() {
        if(itemClickListener == null){
            itemClickListener = new LvwResponsibleItemClickListener();
        }
        return itemClickListener;
    }

    public View.OnClickListener getClickListener() {
        if(clickListener == null){
            clickListener = new NewResponsibleClickListener();
        }
        return clickListener;
    }

    public static HousingHistoricalModel[] getArray(RealmList<HousingHistoricalModel> housingHistorical){
        return housingHistorical == null ? null : housingHistorical.toArray(new HousingHistoricalModel[housingHistorical.size()]);
    }

    public static RealmList<HousingHistoricalModel> getList(Bundle bundle, String key) {
        HousingHistoricalModel[] objects = (HousingHistoricalModel[]) bundle.getSerializable(key);
        return objects == null ? null : new RealmList<>(objects);
    }

    public RealmList<HousingHistoricalModel> getHousingHistorical(ListView lvwResponsibles) {
        return new RealmList<>();
    }

    private class NewResponsibleClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(fragment.getContext(), ResidenceNewResponsibleActivity.class);
            fragment.getActivity().startActivity(intent);
        }
    }

    private class LvwResponsibleItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
