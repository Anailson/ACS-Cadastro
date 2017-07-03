package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.VisitListAdapter;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.interfaces.ITextSearchListener;
import tcc.acs_cadastro_mobile.models.VisitModel;
import tcc.acs_cadastro_mobile.persistence.VisitPersistence;
import tcc.acs_cadastro_mobile.views.VisitAddActivity;

public class VisitListController {

    private Context context;
    private Listeners listener;
    private List<VisitModel> visits;

    public VisitListController(Context context) {
        this.context = context;
        this.visits = VisitPersistence.getAll();
        Log.e("Visits.lenght", String.valueOf(visits.size()));
    }

    private Listeners getListener() {
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public ITextSearchListener getSearchListener() {
        return getListener();
    }

    public ListAdapter getAdapter() {
        return new VisitListAdapter(context, visits);
    }

    public View.OnClickListener getClickListener() {
        return getListener();
    }


    private class Listeners implements View.OnClickListener, ITextSearchListener<VisitModel>{

        //View.OnClickListener
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fab_add_visit:
                    context.startActivity(new Intent(context, VisitAddActivity.class));
                    break;
            }
        }

        //ITextSearchListener
        @Override
        public List<ISearcher> search(int search) {
            List<ISearcher> founded = new ArrayList<>();
            for(VisitModel visit : visits){
                if(visit.numSusContainsKey(search) || visit.recordContainsKey(search)){
                    founded.add(visit);
                }
            }
            return founded;
        }

        @Override
        public List<ISearcher> search(String search) {
            List<ISearcher> founded = new ArrayList<>();
            for(VisitModel visit : visits){
                if(visit.nameContainsKey(search)){
                    founded.add(visit);
                }
            }

            return founded;
        }

        @Override
        public ArrayAdapter<VisitModel> updateListView(List<ISearcher> list) {
            List<VisitModel> visits = new ArrayList<>();
            for(ISearcher searcher : list){
                visits.add((VisitModel)searcher);
            }
            return new VisitListAdapter(context, visits);
        }
    }
}
