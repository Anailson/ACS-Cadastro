package tcc.acs_cadastro_mobile.httpRequest;

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskRequest;

public class AccompanyHttpRequest implements IAsyncTaskRequest {

    @Override
    public void onRequest(WebServiceConnection.Request request) {

        if(request.getStatus() == WebServiceConnection.Status.OK){

        }
    }
}
