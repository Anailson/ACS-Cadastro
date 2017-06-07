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
import android.widget.EditText;
import android.widget.ListView;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.ResidenceListController;


public class ResidenceListFragment extends Fragment {

    private ListView lvwResidences;
    private ResidenceListController controller;

    public ResidenceListFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.menu_residence);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_residence, container, false);
        EditText edtSearch = (EditText) view.findViewById(R.id.edt_search_residence);
        FloatingActionButton btnAddResidence = (FloatingActionButton) view.findViewById(R.id.fab_add_residence);
        lvwResidences = (ListView) view.findViewById(R.id.lvw_residence);

        controller = new ResidenceListController(getContext());

        edtSearch.addTextChangedListener(controller.getSearchTextChanged(lvwResidences));
        btnAddResidence.setOnClickListener(controller.getClickListener());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lvwResidences.setAdapter(controller.getAdapter());
    }
}
