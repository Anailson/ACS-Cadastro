package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

import tcc.acs_cadastro_mobile.httpRequest.AgentHttpRequests;
import tcc.acs_cadastro_mobile.httpRequest.WebServiceConnection;
import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.views.MainActivity;

public class LoginController implements IAsyncTaskResponse.Get {

    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    public void validateData(String text) {
        if (text.isEmpty()) {

            Toast.makeText(context, "O numero do SUS deve ser informado", Toast.LENGTH_LONG).show();

        } else if (text.matches("[0]+")) {

            AcsRecordPersistence.startDatabase(context, new AgentModel());
            ((Activity) context).finish();
            context.startActivity(new Intent(context, MainActivity.class));

        } else {

            AgentHttpRequests httpRequests = new AgentHttpRequests(context);
            httpRequests.get(Long.parseLong(text), this);
        }
    }

    @Override
    public void get(WebServiceConnection.Request request, JSONObject jsonObject) {

        if (request.getStatus() == WebServiceConnection.Status.OK){
            AcsRecordPersistence.startDatabase(context, new AgentModel(jsonObject));
            ((Activity) context).finish();
            context.startActivity(new Intent(context, MainActivity.class));
        }else {

        }
    }
}
