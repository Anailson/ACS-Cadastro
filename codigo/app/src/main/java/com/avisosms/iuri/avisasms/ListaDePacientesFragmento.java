package com.avisosms.iuri.avisasms;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.adapters.AdapterListaDePacientes;
import com.avisosms.iuri.avisasms.adapters.AdapterListaDePacientes2;
import com.avisosms.iuri.avisasms.objetos.Agendamento;
import com.avisosms.iuri.avisasms.suporte.Funcoes;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;
import com.thebluealliance.spectrum.SpectrumDialog;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 6/18/2016.
 */

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ListaDePacientesFragmento extends FragmentPagerAdapter {

    int tabQuantidade;

    public ListaDePacientesFragmento(FragmentManager fm, int tabQuantidade) {
        super(fm);
        Realm realm = Realm.getDefaultInstance();

        Calendar calendar = Funcoes.dataHoje();

        RealmResults<Agendamento> agendamentos = realm.where(Agendamento.class)
                .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
                .findAll();


        for (Agendamento agendamento : agendamentos) {
            Log.i("Agendamento", agendamento.getDataDoAtendimentoEmMilissegundo() + " <> " + calendar.getTimeInMillis());
        }

        this.tabQuantidade = agendamentos.size() - 1;

        realm.close();

    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return tabQuantidade;//3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
            default:
                return "default";
        }
    }

    @Override
    public int getItemPosition(Object object) {

        return super.getItemPosition(object);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        AdapterListaDePacientes2 adapter;
        DynamicListView mDynamicListView;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        Realm realm;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            realm = Realm.getDefaultInstance();


            int numeroSecao = getArguments().getInt(ARG_SECTION_NUMBER);

            Calendar calendar = Funcoes.dataHoje();

            RealmResults<Agendamento> agendamentos = realm.where(Agendamento.class).equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis()).findAll();

            Agendamento agendamento = agendamentos.get(numeroSecao - 1);

            View rootView = inflater.inflate(R.layout.consultas_do_dia, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(agendamento.getMedico().getNome() + " numSecao " + numeroSecao);


            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fragment_list_fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    showDialog1();
                    Snackbar.make(view, "Add <> paciente, para o médico " + getArguments().getInt(ARG_SECTION_NUMBER), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            ///////List
            DynamicListView mDynamicListView = (DynamicListView) rootView.findViewById(R.id.fragment_lista_dynamicListView);

            AdapterListaDePacientes adapter = new AdapterListaDePacientes(rootView.getContext(), agendamento.getPacientes().sort("ordem"));

            AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter(adapter);
            animAdapter.setAbsListView(mDynamicListView);
            mDynamicListView.setAdapter(animAdapter);


            mDynamicListView.enableDragAndDrop();
            mDynamicListView.setDraggableManager(new TouchViewDraggableManager(R.id.list_row_draganddrop_touchview));
            /*mDynamicListView.setOnItemMovedListener(new MyOnItemMovedListener(adapter, this));
            mDynamicListView.setOnItemLongClickListener(new MyOnItemLongClickListener(mDynamicListView));*/

            // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.principal_content_fab);
            // fab.setOnClickListener(new MyOnItemClickListener(mDynamicListView, adapter));

            //adapter.notifyDataSetInvalidated();


            return rootView;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (realm != null)
                realm.close();
        }

        private void showDialog1() {
            new SpectrumDialog.Builder(getContext())
                    .setColors(R.array.cores)
                    .setSelectedColorRes(R.color.orange)
                    .setDismissOnColorSelected(false)
                    // .setOutlineWidth(8)
                    .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(boolean positiveResult, @ColorInt int color) {
                            if (positiveResult) {
                                Toast.makeText(getContext(), "Color selected: #" + Integer.toHexString(color).toUpperCase(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Dialog cancelled", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).build().show(getFragmentManager(), "dialog_demo_1");

        }

    }
}
