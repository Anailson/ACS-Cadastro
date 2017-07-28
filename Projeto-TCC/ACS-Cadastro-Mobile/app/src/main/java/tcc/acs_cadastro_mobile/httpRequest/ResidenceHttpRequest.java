package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.ResidenceModel;


public class ResidenceHttpRequest {

    private final String PATH = "ResidenceWebService.php";
    private IAsyncTaskResponse.Responses response;
    private WebServiceConnection webService;

    public ResidenceHttpRequest(Context context) {
        response = new IAsyncTaskResponse.Responses();
        webService = new WebServiceConnection(context, response);
    }

    public void insert(ResidenceModel residence, IAsyncTaskResponse.Insert insert) {
        response.insert = insert;
        webService.post(PATH, residence.asJson());
    }
}
