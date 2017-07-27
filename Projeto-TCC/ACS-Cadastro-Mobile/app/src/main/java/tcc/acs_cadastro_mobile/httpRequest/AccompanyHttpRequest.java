package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;
import tcc.acs_cadastro_mobile.models.AccompanyModel;

public class AccompanyHttpRequest {

    private final String PATH = "AccompanyWebService.php";
    private IAsyncTaskResponse.Responses response;
    private WebServiceConnection webService;

    public AccompanyHttpRequest(Context context) {
        response = new IAsyncTaskResponse.Responses();
        webService = new WebServiceConnection(context, response);
    }

    public void insert(AccompanyModel accompany, IAsyncTaskResponse.Insert insert){
        response.insert = insert;
        webService.post(PATH, accompany.asJson());
    }
}
