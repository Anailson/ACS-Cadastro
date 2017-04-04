package com.avisosms.iuri.avisasms.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.suporte.Funcoes;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 7/5/2016.
 */
public class Consultas extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.consultas, container, false);
        view.refreshDrawableState();

        Realm realm = Realm.getDefaultInstance();

        Calendar calendar = Funcoes.dataHoje();

        RealmResults<Consulta> consultas = realm.where(Consulta.class)
                .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
                .findAll();

        for (Consulta consulta : consultas) {
            Log.i("Consulta", consulta.getDataDoAtendimentoEmMilissegundo() + " <> " + calendar.getTimeInMillis());
        }

        ///////////

        ConsultasListaFragmento mConsultasListaFragmento;
        ViewPager mViewPager;
        mConsultasListaFragmento = new ConsultasListaFragmento(getChildFragmentManager(), consultas.size());

        realm.close();

        // Set up the ViewPager with the sections adapterListaDePacientes.
        //setContentView(R.layout.fragment_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mConsultasListaFragmento);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 1:
//                        lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard);
                        //Toast.makeText(getContext(), imgView.getId() + "", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                       // lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard2);
                        break;
                    case 3:
                        //lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard3);
                        break;
                    case 4:
                        //lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard4);
                        break;
                    case 5:
                        //lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard5);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        return view;
    }

    static int id = 1;

    // Returns a valid id that isn't in use
    public int findId(){
        View v = view.findViewById(++id);

        while (v != null){
            v = view.findViewById(++id);
        }

        return id++;
    }
}
