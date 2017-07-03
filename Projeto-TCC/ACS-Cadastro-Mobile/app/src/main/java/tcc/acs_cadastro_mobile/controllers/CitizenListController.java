package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.adapters.CitizenListAdapter;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.interfaces.ITextSearchListener;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.views.CitizenAddActivity;

public class CitizenListController{

    private Context context;
    private List<CitizenModel> citizens;
    private Listeners listener;

    public CitizenListController(Context context){
        this.context = context;
        this.citizens = CitizenPersistence.getAll();
    }

    public ArrayAdapter<CitizenModel> getAdapter(){
        return new CitizenListAdapter(context, citizens);
    }

    private Listeners getListener(){
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public ITextSearchListener getSearchTextChanged(){
        return getListener();
    }

    public View.OnClickListener getOnClickListener(){
        return getListener();
    }

    private class Listeners  implements View.OnClickListener, ITextSearchListener<CitizenModel>{


        //View.OnClickListener
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, CitizenAddActivity.class));
        }

        //TextSearchChanged<CitizenModel>

        @Override
        public List<ISearcher> search(String search){

            List<ISearcher> founded = new ArrayList<>();

            for (CitizenModel citizen : citizens) {
                if (citizen.nameContainsKey(search)) {
                    founded.add(citizen);
                }
            }
            return founded;
        }

        @Override
        public List<ISearcher> search(int search) {
            List<ISearcher> founded = new ArrayList<>();
            for (CitizenModel citizen : citizens) {

                if (citizen.susContainsKey(search)) {
                    founded.add(citizen);
                }
            }
            return founded;
        }

        @Override
        public ArrayAdapter<CitizenModel> updateListView(List<ISearcher> list) {
            List<CitizenModel> citizens = new ArrayList<>();
            for(ISearcher searcher : list){
                citizens.add((CitizenModel) searcher);
            }
            return new CitizenListAdapter(context, citizens);
        }
    }
}
