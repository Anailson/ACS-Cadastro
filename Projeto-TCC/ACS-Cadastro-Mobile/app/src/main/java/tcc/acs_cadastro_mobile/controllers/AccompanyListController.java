package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.AccompanyListAdapter;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.interfaces.ITextSearchListener;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.persistence.AccompanyPersistence;
import tcc.acs_cadastro_mobile.views.AccompanyAddActivity;

public class AccompanyListController {

    private Context context;
    private List<AccompanyModel> accompanies;
    private Listeners listener;

    public AccompanyListController(Context context) {
        this.context = context;
        this.accompanies = AccompanyPersistence.getAll();
    }

    public ArrayAdapter getAdapter(){
        return new AccompanyListAdapter(context, accompanies);
    }

    private Listeners getListener() {
        if(listener == null){
            listener = new Listeners();
        }
        return listener;
    }

    public View.OnClickListener getClickListener(){
        return getListener();
    }

    public ITextSearchListener getSearchListener(){
        return getListener();
    }

    private class Listeners implements View.OnClickListener, ITextSearchListener<AccompanyModel> {


        //View.OnClickListener
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fab_add_accompany:
                    context.startActivity(new Intent(context, AccompanyAddActivity.class));
                    break;
            }
        }

        //ITextSearchListener
        @Override
        public List<ISearcher> searchByText(String search) {

            List<ISearcher> founded = new ArrayList<>();
            for (AccompanyModel accompany : accompanies) {
                if (accompany.nameContainsKey(search)) {
                    founded.add(accompany);
                }
            }
            return founded;
        }

        @Override
        public List<ISearcher> searchByNumber(String search) {

            List<ISearcher> founded = new ArrayList<>();
            for (AccompanyModel accompany : accompanies) {
                if (accompany.numSusContainsKey(search) || accompany.recordContainsKey(search)) {
                    founded.add(accompany);
                }
            }
            return founded;
        }

        @Override
        public ArrayAdapter<AccompanyModel> updateListView(List<ISearcher> founded) {
            List<AccompanyModel> accompanies = new ArrayList<>();
            for(ISearcher searcher : founded){
                accompanies.add((AccompanyModel) searcher);
            }
            return new AccompanyListAdapter(context, accompanies);
        }
    }
}
