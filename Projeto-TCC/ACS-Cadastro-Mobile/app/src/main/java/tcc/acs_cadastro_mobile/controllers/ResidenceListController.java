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

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.ResidenceListAdapter;
import tcc.acs_cadastro_mobile.interfaces.TextSearchChanged;
import tcc.acs_cadastro_mobile.models.ResidenceModel;
import tcc.acs_cadastro_mobile.views.ResidenceAddActivity;

public class ResidenceListController {

    private Context context;
    private List<ResidenceModel> residences;
    private TextSearch textSearch;
    private View.OnClickListener clickListener;

    public ResidenceListController(Context context){
        this.context = context;
        this.residences = getResidences();
    }

    public ListAdapter getAdapter(){
        return new ResidenceListAdapter(context, residences);
    }

    public TextWatcher getSearchTextChanged(ListView listView){
        if(textSearch == null){
            textSearch = new TextSearch(context, listView);
        }
        return textSearch;
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

    private class TextSearch extends TextSearchChanged<ResidenceModel> {

        private Context context;
        TextSearch(Context context, ListView listView) {
            super(listView);
            this.context = context;
        }

        @Override
        protected ArrayAdapter<ResidenceModel> updateListView(List<ResidenceModel> list) {
            return new ResidenceListAdapter(context, list);
        }

        @Override
        protected List<ResidenceModel> searchByName(String search){
            List<ResidenceModel> founded = new ArrayList<>();

            for (ResidenceModel residence : residences) {
                if (residence.addressContainsKey(search)) {
                    founded.add(residence);
                }
            }
            return founded;
        }

        @Override
        protected List<ResidenceModel> searchByNumber(String search) {
            List<ResidenceModel> founded = new ArrayList<>();

            for (ResidenceModel residence : residences) {
                if (residence.cepContainsKey(search)) {
                    founded.add(residence);
                }
            }
            return founded;
        }
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
