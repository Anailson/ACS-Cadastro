package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.CitizenModel;

public class CitizenHttpRequest {

    private final String PATH = "CitizenWebService.php";
    private IAsyncTaskResponse.Responses response;
    private WebServiceConnection webService;

    public CitizenHttpRequest(Context context) {
        this.response = new IAsyncTaskResponse.Responses();
        this.webService = new WebServiceConnection(context, response);
    }

    public void get(long value, IAsyncTaskResponse.Get get){
        response.get = get;
        webService.get(PATH, value);
    }

    public void insert(CitizenModel citizen, IAsyncTaskResponse.Insert insert){
        response.insert = insert;
        webService.post(PATH, citizen.asJson());
    }

    public void insertAll(List<CitizenModel> list, IAsyncTaskResponse.InsertAll insertAll){
        response.insertAll = insertAll;
        webService.postAll(PATH, convertFrom(list));
    }

    private JSONArray convertFrom(List<CitizenModel> list) {
        JSONArray array = new JSONArray();
        for (CitizenModel citizen : list) {
            array.put(citizen.asJson());
        }
        return array;
    }

}
