package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.adapters.CitizenListAdapter;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.views.CitizenAddActivity;

public class CitizenController {

    private Context context;
    private List<CitizenModel> citizens;
    private ClickListener clickListener;
    private TextChangeListener textChangeListener;

    public CitizenController(Context context){
        this.context = context;
        this.citizens = getCitizens();
    }

    public ListAdapter getAdapter(){
        return new CitizenListAdapter(context, citizens);
    }

    public TextWatcher getTextChangedListener(final ListView lvwCitizens){

        if(textChangeListener == null){
            textChangeListener = new TextChangeListener(lvwCitizens);
        }
        return textChangeListener;
    }

    public View.OnClickListener getOnClickListener(){

        if(clickListener == null){
            clickListener = new ClickListener();
        }
        return clickListener;
    }

    private ListAdapter updateListView(String search){

        if(isNumber(search)){
            return new CitizenListAdapter(context, searchBySusNumber(search));
        }
        return new CitizenListAdapter(context, searchByName(search));
    }

    private boolean isNumber(String search){
        return search.matches("[0-9]+");
    }

    private List<CitizenModel> searchByName(String search){
        List<CitizenModel> founded = new ArrayList<>();

        for (CitizenModel citizen : citizens) {
            if (citizen.containsKey(search)) {
                founded.add(citizen);
            }
        }
        return founded;
    }

    private List<CitizenModel> searchBySusNumber(String search){
        List<CitizenModel> founded = new ArrayList<>();
        for (CitizenModel citizen : citizens) {

            if (citizen.getSusNum().toLowerCase().contains(search.toLowerCase().trim())) {
                founded.add(citizen);
            }
        }
        return founded;
    }

    private List<CitizenModel> getCitizens(){
        List<CitizenModel> citizens = new ArrayList<>();
        citizens.add(new CitizenModel("Joao"));
        citizens.add(new CitizenModel("Maria"));
        citizens.add(new CitizenModel("André"));
        citizens.add(new CitizenModel("Marcia"));
        citizens.add(new CitizenModel("Carlos"));
        citizens.add(new CitizenModel("Francisco"));
        citizens.add(new CitizenModel("Erique"));
        citizens.add(new CitizenModel("Dona Francisca da Silva"));
        return citizens;
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, CitizenAddActivity.class));
        }
    }

    private class TextChangeListener implements TextWatcher{

        private ListView lvwCitizens;

        public TextChangeListener(ListView lvwCitizens){
            this.lvwCitizens = lvwCitizens;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            ListAdapter adapter = updateListView(charSequence.toString());
            lvwCitizens.setAdapter(adapter);
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }
}
