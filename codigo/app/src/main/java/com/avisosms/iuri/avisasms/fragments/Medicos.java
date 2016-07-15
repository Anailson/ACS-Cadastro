package com.avisosms.iuri.avisasms.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.adapters.AdapterListaDeMedicos;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.thebluealliance.spectrum.SpectrumDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuri on 7/5/2016.
 */
public class Medicos extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.medico_layout, container, false);
        view.refreshDrawableState();

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.medicos);


        ListView mListView = (ListView) view.findViewById(R.id.medicos_listview);
        List<Medico> objects = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            objects.add(new Medico("Jonathas Martins", "Fonoaldiologo", "(79) 9 9670-2237"));
        }

        /* Setup the adapter */
        AdapterListaDeMedicos adapter = new AdapterListaDeMedicos(getContext(), objects);
        mListView.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.medico_layout_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           startActivity(new Intent(getActivity(), MedicoAdicionar.class));
            }
        });

        return view;

    }



    private SearchView searchView;
    private String pesquisa = "";
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_medicos, menu);

        final MenuItem searchMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                pesquisa = s;
                //TODO pesquisar clientes
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {

        }

        return super.onOptionsItemSelected(item);
    }

}
