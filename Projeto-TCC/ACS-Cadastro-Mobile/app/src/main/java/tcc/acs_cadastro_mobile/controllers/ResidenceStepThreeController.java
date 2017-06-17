package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.adapters.HousingHistoricalListAdapter;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;
import tcc.acs_cadastro_mobile.views.ResidenceNewResponsibleActivity;

public class ResidenceStepThreeController {

    private Fragment fragment;

    private Listener listener;

    public ResidenceStepThreeController(Fragment fragment){
        this.fragment = fragment;
    }

    public AdapterView.OnItemClickListener getItemClickListener() {
        if(listener == null){
            listener = new Listener();
        }
        return listener;
    }

    public View.OnClickListener getClickListener() {
        if(listener == null){
            listener = new Listener();
        }
        return listener;
    }

    public static HousingHistoricalModel[] getArray(RealmList<HousingHistoricalModel> housingHistorical){
        return housingHistorical == null ? null : housingHistorical.toArray(new HousingHistoricalModel[housingHistorical.size()]);
    }

    public static RealmList<HousingHistoricalModel> getList(Bundle bundle, String key) {
        HousingHistoricalModel[] objects = (HousingHistoricalModel[]) bundle.getSerializable(key);
        return objects == null ? null : new RealmList<>(objects);
    }

    public RealmList<HousingHistoricalModel> getHousingHistorical(ListView listview) {
        return ((HousingHistoricalListAdapter) listview.getAdapter()).getHousingHistorical();
    }

    public ArrayAdapter<HousingHistoricalModel> getAdapter(Context context,
                               RealmList<HousingHistoricalModel> housingHistorical) {
        return new HousingHistoricalListAdapter(context, housingHistorical);
    }

    private class Listener implements View.OnClickListener, AdapterView.OnItemClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(fragment.getContext(), ResidenceNewResponsibleActivity.class);
            fragment.startActivityForResult(intent, ResidenceNewResponsibleActivity.RESULT);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
