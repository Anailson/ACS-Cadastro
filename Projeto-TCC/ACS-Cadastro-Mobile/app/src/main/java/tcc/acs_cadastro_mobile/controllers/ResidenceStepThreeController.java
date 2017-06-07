package tcc.acs_cadastro_mobile.controllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;
import tcc.acs_cadastro_mobile.views.NewResponsibleActivity;

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

    public HousingHistoricalModel[] getHousingHistorical(ListView lvwResponsibles) {
        return new HousingHistoricalModel[0];
    }

    private class NewResponsibleClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(fragment.getContext(), NewResponsibleActivity.class);
            fragment.getActivity().startActivity(intent);
        }
    }

    private class LvwResponsibleItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
