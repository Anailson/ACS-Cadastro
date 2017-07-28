package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.VisitModel;

public class VisitHttpRequest {

    private final String PATH = "VisitWebService.php";
    private IAsyncTaskResponse.Responses response;
    private WebServiceConnection webService;

    public VisitHttpRequest(Context context) {
        response = new IAsyncTaskResponse.Responses();
        webService = new WebServiceConnection(context, response);
    }

    public void insert(VisitModel visit, IAsyncTaskResponse.Insert insert){
        response.insert = insert;
        webService.post(PATH, visit.asJson());
    }
}
