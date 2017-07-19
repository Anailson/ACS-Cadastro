package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

import tcc.acs_cadastro_mobile.httpRequest.AgentHttpRequests;
import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.persistence.AgentPersistence;
import tcc.acs_cadastro_mobile.views.MainActivity;

public class LoginController implements IAsyncTaskResponse<AgentModel>{

    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    public void validateData(String text) {
        if (text.isEmpty()) {

            Toast.makeText(context, "O numero do SUS deve ser informado", Toast.LENGTH_LONG).show();

        } else if (text.matches("[0]+")) {

            AgentPersistence.save(new AgentModel());
            ((Activity) context).finish();
            context.startActivity(new Intent(context, MainActivity.class));

        } else {
            AgentHttpRequests httpRequests = new AgentHttpRequests(this, context);
            httpRequests.get(Long.parseLong(text));
        }
    }

    @Override
    public void get(AgentModel agentModel) {
        AgentPersistence.save(agentModel);
        ((Activity) context).finish();
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void getAll(ArrayList<AgentModel> list) {

    }

    @Override
    public void insert(int id) {

    }

    @Override
    public void update(AgentModel object) {

    }

    @Override
    public void delete(AgentModel object) {

    }
}
