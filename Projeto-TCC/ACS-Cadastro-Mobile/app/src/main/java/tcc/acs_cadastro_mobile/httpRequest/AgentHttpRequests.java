package tcc.acs_cadastro_mobile.httpRequest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import tcc.acs_cadastro_mobile.models.AgentModel;

public class AgentHttpRequests {

    public static AgentModel get(long numSus) {

        try {

            WebServiceRequest.Request request = new WebServiceRequest(WebServiceRequest.Table.AGENT).get(numSus);

            if (request.getStatus() == WebServiceRequest.Status.OK) {

                return AgentModel.get(request.getObject());

            } else if (request.getStatus() == WebServiceRequest.Status.CONNECTION_ERROR){
                Log.e("CONNECTION_ERROR", WebServiceRequest.Status.CONNECTION_ERROR.name());
            } else if (request.getStatus() == WebServiceRequest.Status.JSON_ERROR){
                Log.e("JSON_ERROR", WebServiceRequest.Status.JSON_ERROR.name());
            }

        } catch (JSONException e) {
            Log.e("get(" + numSus + ") ", e.getMessage());
        }
        return null;
    }
}
