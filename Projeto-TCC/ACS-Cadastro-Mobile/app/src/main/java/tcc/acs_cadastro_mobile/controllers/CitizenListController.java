package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.adapters.CitizenListAdapter;
import tcc.acs_cadastro_mobile.interfaces.TextSearchChanged;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.views.CitizenAddActivity;

public class CitizenListController{

    private Context context;
    private List<CitizenModel> citizens;
    private TextSearch textSearch;
    private ClickListener clickListener;

    public CitizenListController(Context context){
        this.context = context;
        this.citizens = CitizenPersistence.getAll();
    }

    public ListAdapter getAdapter(){
        return new CitizenListAdapter(context, citizens);
    }

    public TextWatcher getSearchTextChanged(ListView listView){
        if(textSearch == null){
            textSearch = new TextSearch(context, listView);
        }
        return textSearch;
    }

    public View.OnClickListener getOnClickListener(){

        if(clickListener == null){
            clickListener = new ClickListener();
        }
        return clickListener;
    }

    private class TextSearch extends TextSearchChanged<CitizenModel> {

        private Context context;
        TextSearch(Context context, ListView listView) {
            super(listView);
            this.context = context;
        }

        @Override
        protected ArrayAdapter<CitizenModel> updateListView(List<CitizenModel> list) {
            return new CitizenListAdapter(context, list);
        }

        @Override
        protected List<CitizenModel> searchByName(String search){
            List<CitizenModel> founded = new ArrayList<>();

            for (CitizenModel citizen : citizens) {
                if (citizen.nameContainsKey(search)) {
                    founded.add(citizen);
                }
            }
            return founded;
        }

        @Override
        protected List<CitizenModel> searchByNumber(String search) {
            List<CitizenModel> founded = new ArrayList<>();
            for (CitizenModel citizen : citizens) {

                if (citizen.susContainsKey(search)) {
                    founded.add(citizen);
                }
            }
            return founded;
        }
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, CitizenAddActivity.class));
        }
    }
}
