package com.avisosms.iuri.avisasms.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.adapters.AdapterListaDePacientes;
import com.avisosms.iuri.avisasms.dataHandler.PacienteHandler;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;
import com.avisosms.iuri.avisasms.suporte.Funcoes;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by iuri on 6/18/2016.
 */

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ConsultasListaFragmento extends FragmentPagerAdapter {

    static int tabQuantidade;

    public ConsultasListaFragmento(FragmentManager fm, int tabQuantidade) {
        super(fm);
//        Realm realm = Realm.getDefaultInstance();
//
//        Calendar calendar = Funcoes.dataHoje();
//
//        RealmResults<Consulta> consultas = realm.where(Consulta.class)
//                .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
//                .findAll();
//
//
//        for (Consulta consulta : consultas) {
//            Log.i("Consulta", consulta.getDataDoAtendimentoEmMilissegundo() + " <> " + calendar.getTimeInMillis());
//        }

        this.tabQuantidade = tabQuantidade;

//        realm.close();

    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position);
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
        AdapterListaDePacientes adapter;
        AlphaInAnimationAdapter animAdapter;
        DynamicListView mDynamicListView;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            realm = Realm.getDefaultInstance();


            final int numeroSecao = getArguments().getInt(ARG_SECTION_NUMBER);

            final Calendar calendar = Funcoes.dataHoje();

            RealmResults<Consulta> consultas = realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis()).findAll();

            final Consulta consulta = consultas.get(numeroSecao);

            final View rootView = inflater.inflate(R.layout.consulta_lista_de_pacientes, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(consulta.getMedico().getCorIndicativa() +"  " +consulta.getMedico().getNome() + " numSecao " + numeroSecao);


            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.fragment_tab_layout_indicadores);
            float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        /*LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = (int) pixels;
        params.weight = (int) pixels;*/

            //static  int id = 1;// getResources().getIdentifier("gameover", "drawable", getPackageName());
            for (  int i = 0; i < tabQuantidade; i++) {
                ImageView imgView = new ImageView(getContext());
                //imgView.setId(0x7f0d0079);
                imgView.setImageResource(R.drawable.icon_clipboard);
                imgView.setLayoutParams(new LinearLayout.LayoutParams((int)pixels,(int) pixels));//new LinearLayout.LayoutParams(30, 30));

                if(i == numeroSecao){
                    int color = getResources().getColor(R.color.colorAccent); //The color u want
                    imgView.setColorFilter(color);
                }

                linearLayout.addView(imgView);

            }

            Button btn_ordenar = (Button)  rootView.findViewById(R.id.btn_ordenar);
            btn_ordenar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter = new AdapterListaDePacientes(rootView.getContext(), consulta.getPacientes().sort("atendido", Sort.DESCENDING));

                    mDynamicListView.setAdapter(adapter);
                    mDynamicListView.invalidate();
                    mDynamicListView.setSelection(mDynamicListView.getCount());
                    Toast.makeText(v.getContext(), "Color selected: #", Toast.LENGTH_SHORT).show();

                }
            });

            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fragment_list_fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    RealmResults<Consulta> consultas = realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis()).findAll();

                    final Consulta consulta = consultas.get(numeroSecao);

                   /* Paciente paciente = new PacienteHandler().newPaciente(realm, new Paciente("Novo paciente","(79) 999652-5874", animAdapter.getCount(), consulta.getId()));

                    realm.beginTransaction();
                    consulta.getPacientes().add(paciente);

                    realm.commitTransaction();*/

                    adapter = new AdapterListaDePacientes(rootView.getContext(), consulta.getPacientes().sort("ordem"));
/*
                    adapter.notifyDataSetInvalidated();

                    animAdapter.notifyDataSetChanged();*/
                    animAdapter.notifyDataSetInvalidated();


                    mDynamicListView.setAdapter(adapter);
                    mDynamicListView.invalidate();
                    mDynamicListView.setSelection(mDynamicListView.getCount());
                    //mDynamicListView.invalidateViews();
                    //mDynamicListView.refreshDrawableState();

                    Snackbar.make(view, "Add <> paciente, para o médico " + consulta.getMedico().getId(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            ///////List
            mDynamicListView = (DynamicListView) rootView.findViewById(R.id.fragment_lista_dynamicListView);

             adapter = new AdapterListaDePacientes(rootView.getContext(), consulta.getPacientes().sort("ordem"));

            animAdapter = new AlphaInAnimationAdapter(adapter);
            animAdapter.setAbsListView(mDynamicListView);
            mDynamicListView.setAdapter(animAdapter);

            adapter.notifyDataSetInvalidated();



            mDynamicListView.enableDragAndDrop();
            mDynamicListView.setDraggableManager(new TouchViewDraggableManager(R.id.list_row_draganddrop_touchview));
            /*mDynamicListView.setOnItemMovedListener(new MyOnItemMovedListener(adapter, this));
            mDynamicListView.setOnItemLongClickListener(new MyOnItemLongClickListener(mDynamicListView));*/

            // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.principal_content_fab);
            // fab.setOnClickListener(new MyOnItemClickListener(mDynamicListView, adapter));

            //adapter.notifyDataSetInvalidated();
          /*  Toast.makeText(getContext(), " " + getArguments().getInt(ARG_SECTION_NUMBER), Toast.LENGTH_SHORT).show();*/

            return rootView;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (realm != null)
                realm.close();
        }


        private class MyOnItemClickListener implements AdapterView.OnClickListener {

            private final DynamicListView mListView;
            ArrayAdapter adapter;

            MyOnItemClickListener(final DynamicListView listView, ArrayAdapter adapter) {
                mListView = listView;
                this.adapter = adapter;
            }

            @Override
            public void onClick(View v) {
                mListView.insert(mListView.getCount(), new Paciente("Item adicionado", "telefone", 1, 0));

                Toast.makeText(v.getContext(), "Adicionar Joption para add Paciente", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        // Select the last row so it will scroll into view...
                        mListView.setSelection(adapter.getCount() - 1);
                    }
                });
            }
        }

        private class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener {

            private final DynamicListView mListView;

            MyOnItemLongClickListener(final DynamicListView listView) {
                mListView = listView;
            }

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                if (mListView != null) {
                    mListView.startDragging(position - mListView.getHeaderViewsCount());

                }

                return true;
            }
        }

        private class MyOnItemMovedListener implements OnItemMovedListener {

            private final ArrayAdapter<Paciente> mAdapter;

            private Toast mToast;
            private Activity mActivity;

            MyOnItemMovedListener(final ArrayAdapter<Paciente> adapter, Activity activity) {
                mAdapter = adapter;
                mActivity = activity;
            }

            @Override
            public void onItemMoved(final int originalPosition, final int newPosition) {
                if (mToast != null) {
                    mToast.cancel();
                }

               /* mToast = Toast.makeText(mActivity, mActivity.getString(R.string.moved, mAdapter.getItem(newPosition).getNome(), newPosition), Toast.LENGTH_SHORT);
                mToast.show();
*/

//            mToast = Toast.makeText(mActivity, originalPosition+ " to " + newPosition, Toast.LENGTH_SHORT);
//            mToast.show();
//
//            mToast = Toast.makeText(mActivity, mAdapter.getItem(newPosition).getNome() + " new Position ", Toast.LENGTH_SHORT);
//            mToast.show();

            }
        }


    }
}
