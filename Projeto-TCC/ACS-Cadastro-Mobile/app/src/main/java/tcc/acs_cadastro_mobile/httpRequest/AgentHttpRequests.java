package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;

public class AgentHttpRequests {

    private final String PATH = "AgentsWebService.php";

    private IAsyncTaskResponse.Responses response;
    private WebServiceConnection webService;

    public AgentHttpRequests(Context context) {

        this.response = new IAsyncTaskResponse.Responses();
        this.webService = new WebServiceConnection(context, response);
    }

    public void get(long value, IAsyncTaskResponse.Get get){
        this.response.get = get;
        webService.get(PATH, value);
    }/*

    public void getAll(IAsyncTaskResponse.GetAll getAll){
        this.response.getAll = getAll;
        webService.post(WebServiceConnection.Method.GET_ALL, PATH);
    }

    public void insert(AgentModel agent, IAsyncTaskResponse.Insert insert){
        this.response.insert = insert;
        webService.post(WebServiceConnection.Method.POST, PATH, agent.asJson());
    }

    public void insert(ArrayList<AgentModel> agents, IAsyncTaskResponse.Insert insert){
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
            array.put(agent.asJson());
        }
        return array;
    }*/
}