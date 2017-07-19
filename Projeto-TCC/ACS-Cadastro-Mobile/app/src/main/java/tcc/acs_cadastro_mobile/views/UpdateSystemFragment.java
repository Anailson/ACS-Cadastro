package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.httpRequest.AgentHttpRequests;
import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.AgentModel;

public class UpdateSystemFragment extends Fragment implements IAsyncTaskResponse<AgentModel> {

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


        AgentHttpRequests httpRequests = new AgentHttpRequests(this, getContext());
        httpRequests.insert(new AgentModel("Francisca da Silva", 667567, 1231, 890));

        return inflater.inflate(R.layout.fragment_update_sistem, container, false);
    }

    @Override
    public void get(AgentModel agentModel) {}

    @Override
    public void getAll(ArrayList<AgentModel> list) {}

    @Override
    public void insert(int id) {
        Toast.makeText(getContext(), "ID: " + id, Toast.LENGTH_LONG).show();
    }

    @Override
    public void update(AgentModel object) {}

    @Override
    public void delete(AgentModel object) {}
}
