package tcc.acs_cadastro_mobile.interfaces;


import tcc.acs_cadastro_mobile.httpRequest.WebServiceConnection;

public interface IAsyncTaskRequest {

    void onRequest(WebServiceConnection.Request request);
}
