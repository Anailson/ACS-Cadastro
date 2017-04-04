package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.ResidenceListAdapter;
import tcc.acs_cadastro_mobile.models.ResidenceModel;
import tcc.acs_cadastro_mobile.views.CitizenAddActivity;
import tcc.acs_cadastro_mobile.views.ResidenceAddActivity;

public class ResidenceController {

    private Context context;
    private List<ResidenceModel> residences;
    private View.OnClickListener clickListener;

    public ResidenceController(Context context){
        this.context = context;
        this.residences = getResidences();
    }

    public ListAdapter getAdapter(){
        return new ResidenceListAdapter(context, residences);
    }


    public View.OnClickListener getClickListener() {

        if (clickListener == null) {
            clickListener = new OnClickListener();
        }
        return clickListener;
    }

    private List<ResidenceModel> getResidences(){
        List<ResidenceModel> residences = new ArrayList<>();
        residences.add(new ResidenceModel("Joao"));
        residences.add(new ResidenceModel("Maria"));
        residences.add(new ResidenceModel("André"));
        residences.add(new ResidenceModel("Marcia"));
        residences.add(new ResidenceModel("Carlos"));
        residences.add(new ResidenceModel("Francisco"));
        residences.add(new ResidenceModel("Erique"));
        residences.add(new ResidenceModel("Dona Francisca da Silva"));
        return residences;
    }

    private class OnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.fab_add_residence:
                    context.startActivity(new Intent(context, ResidenceAddActivity.class));
                    break;
            }
        }
    }
}
