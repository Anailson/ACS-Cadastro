package com.avisosms.iuri.avisasms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iuri on 7/5/2016.
 */
public class PrincipalConsultasDoDia extends Fragment {


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.principal_lista_de_pacientes, container, false);
        view.refreshDrawableState();


        /*Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        ListaDePacientesFragmento mListaDePacientesFragmento;
        ViewPager mViewPager;
        mListaDePacientesFragmento = new ListaDePacientesFragmento(getChildFragmentManager(), 2);

        // Set up the ViewPager with the sections adapter.
        //setContentView(R.layout.fragment_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mListaDePacientesFragmento);

        return view;

    }
}
