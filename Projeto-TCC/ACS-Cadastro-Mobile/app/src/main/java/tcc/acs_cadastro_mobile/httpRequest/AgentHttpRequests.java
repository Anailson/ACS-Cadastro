package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskRequest;
import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.AgentModel;

public class AgentHttpRequests implements IAsyncTaskRequest {

    private final String PATH = "AgentsWebService.php";

    private IAsyncTaskResponse<AgentModel> response;
    private Context context;
    private WebServiceConnection webService;

    public AgentHttpRequests(IAsyncTaskResponse<AgentModel> response, Context context) {
        this.response = response;
        this.context = context;
        this.webService = new WebServiceConnection(context, this);
    }

    @Override
    public void onRequest(WebServiceConnection.Request request) {
        if (request.getStatus() == WebServiceConnection.Status.OK) {

            if (request.getStatus() == WebServiceConnection.Status.OK) {

                if (request.getMethod() == WebServiceConnection.Method.GET) {
                    response.get(getAsJson(request.getJsonObject()));
                } else if (request.getMethod() == WebServiceConnection.Method.GET_ALL) {
                    response.getAll(getAsJsonArray(request.getJsonArray()));
                } else if (request.getMethod() == WebServiceConnection.Method.POST) {
                    response.insert(getId(request.getJsonObject()));
                } else if (request.getMethod() == WebServiceConnection.Method.PUT) {
                    response.update(getAsJson(request.getJsonObject()));
                } else if (request.getMethod() == WebServiceConnection.Method.DELETE) {
                    response.delete(getAsJson(request.getJsonObject()));
                }
            }

        } else if (request.getStatus() == WebServiceConnection.Status.JSON_ERROR) {

            Toast.makeText(context, "O numero do SUS é invalido", Toast.LENGTH_LONG).show();

        } else if (request.getStatus() == WebServiceConnection.Status.CONNECTION_ERROR) {

            Toast.makeText(context, "Erro na conexão com o servidor.", Toast.LENGTH_LONG).show();
        }
    }

    public void get(long l) {
        webService.get(PATH, l);
    }

    public void insert(AgentModel agent) {
        webService.insert(PATH, agent.asJson());
    }



    private AgentModel getAsJson(JSONObject json) {
        try {
            return new AgentModel(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<AgentModel> getAsJsonArray(JSONArray array) {
        ArrayList<AgentModel> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            try {
                list.add(getAsJson(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private int getId(JSONObject json) {
        try {
            return json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }
}