package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import tcc.acs_cadastro_mobile.httpRequest.AgentHttpRequests;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.persistence.AgentPersistence;
import tcc.acs_cadastro_mobile.views.MainActivity;

public class LoginController {

    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    public void validateData(String numSus) {
        if (numSus.isEmpty()) {
            Toast.makeText(context, "O numero do SUS deve ser informado", Toast.LENGTH_LONG).show();
        } else if (numSus.matches("[0]+")) {
            AgentPersistence.save(new AgentModel());
            ((Activity) context).finish();
            context.startActivity(new Intent(context, MainActivity.class));
        } else {
            new GetConnection().execute(numSus);
        }
    }

    private class GetConnection extends AsyncTask<String, Void, AgentModel> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setMessage("Validando o número do SUS...");
            dialog.setTitle("Numero do SUS");
            dialog.show();
        }

        @Override
        protected AgentModel doInBackground(String... strings) {
            long numSus = Long.parseLong(strings[0]);
            return AgentHttpRequests.get(numSus);
        }

        @Override
        protected void onPostExecute(AgentModel agent) {

            dialog.dismiss();
            if(agent != null){

                AgentPersistence.save(agent);
                ((Activity) context).finish();
                context.startActivity(new Intent(context, MainActivity.class));
            }else{
                Toast.makeText(context, "O numero do SUS é invalido", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(agent);
        }
    }
}
