package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.ResidenceListAdapter;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.interfaces.ITextSearchListener;
import tcc.acs_cadastro_mobile.models.ResidenceModel;
import tcc.acs_cadastro_mobile.persistence.ResidencePersistence;
import tcc.acs_cadastro_mobile.views.ResidenceAddActivity;

public class ResidenceListController {

    private Context context;
    private List<ResidenceModel> residences;
    private Listeners listener;

    public ResidenceListController(Context context){
        this.context = context;
        this.residences = ResidencePersistence.getAll();
    }

    public ListAdapter getAdapter(){
        return new ResidenceListAdapter(context, residences);
    }

    private Listeners getListener() {
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public ITextSearchListener getSearchTextChanged(){
        return getListener();
    }

    public View.OnClickListener getClickListener() {
        return getListener();
    }

    private class Listeners implements View.OnClickListener, ITextSearchListener<ResidenceModel>{

        //ITextSearchListener<ResidenceModel>
        @Override
        public List<ISearcher> search(String search){
            List<ISearcher> founded = new ArrayList<>();

            for (ResidenceModel residence : residences) {
                if (residence.addressContainsKey(search)) {
                    founded.add(residence);
                }
            }
            return founded;
        }

        @Override
        public List<ISearcher> search(int search) {
            List<ISearcher> founded = new ArrayList<>();

            for (ResidenceModel residence : residences) {
                if (residence.cepContainsKey(search)) {
                    founded.add(residence);
                }
            }
            return founded;
        }

        @Override
        public ArrayAdapter<ResidenceModel> updateListView(List<ISearcher> list) {
            List<ResidenceModel> residences = new ArrayList<>();
            for(ISearcher searcher : list){
                residences.add((ResidenceModel) searcher);
            }
            return new ResidenceListAdapter(context, residences);
        }

        //View.OnClickListener
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
