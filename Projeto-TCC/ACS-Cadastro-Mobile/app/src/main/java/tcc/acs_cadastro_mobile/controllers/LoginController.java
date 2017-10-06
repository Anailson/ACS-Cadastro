package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.httpRequest.AccompanyHttpRequest;
import tcc.acs_cadastro_mobile.httpRequest.AgentHttpRequests;
import tcc.acs_cadastro_mobile.httpRequest.CitizenHttpRequest;
import tcc.acs_cadastro_mobile.interfaces.IHttpResponse;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.views.MainActivity;

public class LoginController {

    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    public View.OnClickListener getOnClickListener() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtNumSus = (EditText) ((Activity) context).findViewById(R.id.edt_agt_sus);
                validateData(edtNumSus.getText().toString());
            }
        };
    }

    public void startApp() {
        Activity activity = (Activity) context;
        AcsRecordPersistence.startDatabase(context);
        activity.startActivity(new Intent(context, MainActivity.class));
        activity.finish();
    }

    public boolean needsLogin(){
        return !AcsRecordPersistence.isRememberLogin(context);
    }

    private void validateData(final String text) {
        if (text.isEmpty()) {

            Toast.makeText(context, "O numero do SUS deve ser informado", Toast.LENGTH_LONG).show();

        } else if (text.matches("[0]+")) {

            startApp(new AgentModel());

        } else {

            validateLogin(Long.parseLong(text));
        }
    }

    private void validateLogin(final long numSus) {

        AgentHttpRequests httpRequests = new AgentHttpRequests(context);
        httpRequests.get(numSus, new IHttpResponse.Get<AgentModel>() {
            @Override
            public void get(AgentModel agent) {

                startApp(agent);
            }
        });
        httpRequests.start();
    }

    private void startApp(AgentModel agent) {

        Activity activity = (Activity) context;
        CheckBox chbRemember = (CheckBox) activity.findViewById(R.id.chb_agt_remember);
        AcsRecordPersistence.setRememberLogin(context, chbRemember.isChecked());
        AcsRecordPersistence.startDatabase(context, agent);
        context.startActivity(new Intent(context, MainActivity.class));
        activity.finish();
    }
}