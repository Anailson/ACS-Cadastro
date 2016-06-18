package com.avisosms.iuri.avisasms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.adapters.AdapterListaDePacientes;
import com.avisosms.iuri.avisasms.objetos.Paciente;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.util.ArrayList;
import java.util.List;

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
            this.tabQuantidade = tabQuantidade;
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
            }
            return null;
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lista, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("Médico: " + getArguments().getInt(ARG_SECTION_NUMBER));

            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fragment_list_fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Add paciente, para o médico " + getArguments().getInt(ARG_SECTION_NUMBER), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            
            //////////////////////////List
            final DynamicListView mDynamicListView = (DynamicListView) rootView.findViewById(R.id.fragment_lista_dynamicListView);

            List<Paciente> objects = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                objects.add(new Paciente("Paciente nº " + i + ": Iúri Batista Teles\nMédico: " + getArguments().getInt(ARG_SECTION_NUMBER), "Telefone: (79) 9 9670-2237"));
            }


            ArrayAdapter<Paciente> adapter = new AdapterListaDePacientes(rootView.getContext(), objects, mDynamicListView);

            AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter(adapter);
            animAdapter.setAbsListView(mDynamicListView);
            mDynamicListView.setAdapter(animAdapter);


            mDynamicListView.enableDragAndDrop();
            mDynamicListView.setDraggableManager(new TouchViewDraggableManager(R.id.list_row_draganddrop_touchview));
            //mDynamicListView.setOnItemMovedListener(new MyOnItemMovedListener(adapter, this));
            // mDynamicListView.setOnItemLongClickListener(new MyOnItemLongClickListener(mDynamicListView));

            // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.principal_content_fab);
            // fab.setOnClickListener(new MyOnItemClickListener(mDynamicListView, adapter));


            return rootView;
        }


    }


}
