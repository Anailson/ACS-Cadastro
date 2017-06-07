package tcc.acs_cadastro_mobile.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CitizenListController;

public class CitizenListFragment extends Fragment {

    private ListView lvwCitizens;
    private CitizenListController controller;

    public CitizenListFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.menu_citizen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citizen, container, false);
        EditText edtSearch = (EditText) view.findViewById(R.id.edt_search_citizen);
        FloatingActionButton btnAddCitizen = (FloatingActionButton) view.findViewById(R.id.fab_add_citizen);
        lvwCitizens = (ListView) view.findViewById(R.id.lvw_citizens);

        controller = new CitizenListController(getContext());

        edtSearch.addTextChangedListener(controller.getSearchTextChanged(lvwCitizens));
        btnAddCitizen.setOnClickListener(controller.getOnClickListener());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        lvwCitizens.setAdapter(controller.getAdapter());
    }
}
