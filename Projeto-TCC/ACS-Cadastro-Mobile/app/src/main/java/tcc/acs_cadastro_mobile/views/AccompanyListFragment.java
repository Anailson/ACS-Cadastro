package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.AccompanyListController;
import tcc.acs_cadastro_mobile.customViews.SearchingEditText;

public class AccompanyListFragment extends Fragment {

    private AccompanyListController controller;
    private ListView lvwCitizens;

    public AccompanyListFragment (){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.menu_accompany);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_accompany, container, false);
        controller = new AccompanyListController(getContext());

        SearchingEditText edtSearch = (SearchingEditText) view.findViewById(R.id.edt_search_accompany);
        FloatingActionButton btnAddAccompany = (FloatingActionButton) view.findViewById(R.id.fab_add_accompany);
        lvwCitizens = (ListView) view.findViewById(R.id.lvw_accompanies);

        edtSearch.setSearchListener(lvwCitizens, controller.getSearchListener());
        btnAddAccompany.setOnClickListener(controller.getClickListener());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lvwCitizens.setAdapter(controller.getAdapter());
    }
}