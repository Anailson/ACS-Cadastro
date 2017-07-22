package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.UpdateSystemController;

public class UpdateSystemFragment extends Fragment {

    public UpdateSystemFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(R.string.menu_update_system);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        UpdateSystemController controller = new UpdateSystemController(this);
        controller.updateSystem();
        return inflater.inflate(R.layout.fragment_update_sistem, container, false);
    }
}
