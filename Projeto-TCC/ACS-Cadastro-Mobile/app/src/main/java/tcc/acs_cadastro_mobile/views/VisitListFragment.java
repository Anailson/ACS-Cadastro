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
import tcc.acs_cadastro_mobile.controllers.VisitListController;
import tcc.acs_cadastro_mobile.customViews.SearchingEditText;

public class VisitListFragment extends Fragment {

    private ListView lvwVisits;
    private VisitListController controller;

    public VisitListFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.menu_visit);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visit, container, false);
        controller = new VisitListController(getContext());

        lvwVisits = (ListView) view.findViewById(R.id.lvw_visits);
        SearchingEditText edtSearch = (SearchingEditText) view.findViewById(R.id.edt_search_visit);
        FloatingActionButton fabAddVisit = (FloatingActionButton) view.findViewById(R.id.fab_add_visit);

        edtSearch.setSearchListener(lvwVisits, controller.getSearchListener());
        fabAddVisit.setOnClickListener(controller.getClickListener());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lvwVisits.setAdapter(controller.getAdapter());
    }
}
