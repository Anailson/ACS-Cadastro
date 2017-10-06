package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;

import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.interfaces.IHttpResponse;
import tcc.acs_cadastro_mobile.models.AgentModel;

public class AgentHttpRequests  implements WebServiceConnection.ConnectionResult{

    final String PATH = "AgentsWebService.php";
    private Context context;
    private WebServiceConnection webService;
    private IHttpResponse.Get<AgentModel> get;

    public AgentHttpRequests(Context context) {
        this.context = context;
        this.webService = new WebServiceConnection(context, this);
    }

    public void start(){
        webService.start();
    }


    public void get(long value, IHttpResponse.Get<AgentModel> get){
        this.get = get;
        webService.setProgressDialog("Login", "Realizando login...");
        webService.get(PATH, value);
    }

    @Override
    public void onSuccess(WebServiceConnection.Request request) {
        get.get(new AgentModel(request.getObject()));
    }

    @Override
    public void onConnectionError(WebServiceConnection.Request request) {
        new DefaultAlert(context).setMessage("Não foi possível acessar o sistema").show();
    }

    @Override
    public void onInvalidValueError(WebServiceConnection.Request request) {
        new DefaultAlert(context).setMessage("Número do SUS inválido").show();
    }

    @Override
    public void onGeneralError(WebServiceConnection.Request request) {}

    /*

    public void getAll(IHttpResponse.GetAll getAll){
        this.response.getAll = getAll;
        webService.post(WebServiceConnection.Method.GET_ALL, PATH);
    }

    public void insert(AgentModel agent, IHttpResponse.Insert insert){
        this.response.insert = insert;
        webService.post(WebServiceConnection.Method.POST, PATH, agent.parserFrom());
    }

    public void insert(ArrayList<AgentModel> agents, IHttpResponse.Insert insert){
        this.response.insert = insert;
        webService.post(WebServiceConnection.Method.POST, PATH, convertFrom(agents));
    }

    private AgentModel convertFrom(JSONObject json) {
        return new AgentModel(json);
    }

    private ArrayList<AgentModel> convertFrom(JSONArray array) {
        ArrayList<AgentModel> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                list.add(convertFrom(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private JSONArray convertFrom(ArrayList<AgentModel> list) {
        JSONArray array = new JSONArray();
        for (AgentModel agent: list) {
            array.put(agent.parserFrom());
        }
        return array;
    }*/
}