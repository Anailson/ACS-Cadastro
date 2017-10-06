package tcc.acs_cadastro_mobile.httpRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebServiceConnection{

    //private final String HOST = "http://10.0.0.4/";
    //private final String HOST = "http://10.11.162.4/";
    private final String HOST = "http://10.50.167.29";
    //private final String HOST = "http://192.168.42.159/";
    private final String ROOT = HOST + "ACS-Cadastro-Web/webservice/";
    private final String F_GET = "?f=get";
    private Context context;
    private ConnectionResult result;
    private String title, message;
    private List<MakeConnection> connections;

    public WebServiceConnection(Context context, ConnectionResult result) {
        this.context = context;
        this.result = result;
        this.connections = new ArrayList<>();
        this.title = this.message = "";
    }

    void get(String path, long param) {
        makeRequest(result, path, new Request(Method.GET, null, null, param));
    }

    void  getAll(String path) {
       makeRequest(result, path, new Request(Method.GET_ALL, null, null, -1));
    }

    void  getAll(String path, long param) {
        makeRequest(result, path, new Request(Method.GET_ALL, null, null, param));
    }

    void post(String path, JSONObject param) {
        makeRequest(result, path, new Request(Method.POST, param, null, -1));
    }

    void postAll(String path, JSONArray param) {
        makeRequest(result, path, new Request(Method.POST_ALL, null, param, -1));
    }

    void start(){
        if(connections.size() > 0){
            connections.remove(0).execute();
        }
    }

    private void makeRequest(ConnectionResult result, String path, Request request) {

        MakeConnection connection = new MakeConnection(path, result, request);
        connection.setResponse(result);
        connection.setDialog(title, message);
        connections.add(connection);
        //connection.execute(request);
    }

    void setProgressDialog(int titleRes, int messageRes) {
        setProgressDialog(context.getString(titleRes), context.getString(messageRes));
    }

    void setProgressDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    enum Status {
        OK, READ_DATA_OK, ERROR_READ_DATA, WRITE_DATA_OK, ERROR_WRITE_DATA, OK_CONNECTION, CONNECTION_ERROR,
        JSON_ERROR, GENERAL_ERROR
    }

    enum Method {
        GET, GET_ALL, POST, POST_ALL, PUT, DELETE
    }

    interface ConnectionResult {

        void onSuccess(Request request);

        void onGeneralError(Request request);

        void onConnectionError(Request request);

        void onInvalidValueError(Request request);
    }

    private class MakeConnection extends AsyncTask<Void, Void, Request> {

        private String path;
        private ProgressDialog dialog;
        private ConnectionResult result;
        private Request request;
        private String title, message;

        private MakeConnection(String path, ConnectionResult result, Request request) {
            this.path = path;
            this.request = request;
            this.result = result;
            this.dialog = new ProgressDialog(context);
            this.title = this.message = "Aguarde...";
        }

        void setResponse(ConnectionResult result) {
            this.result = result;
        }

        void setDialog(String title, String message) {
            this.title  = title;
            this.message = message;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.show();
        }

        @Override
        protected Request doInBackground(Void... params) {

            Method method = request.getMethod();
            switch (method) {
                case GET: return get(method, path, request.getValue());
                case GET_ALL: return getAll(method, path, request.getValue());
                case POST: return post(method, path, request.getObject());
                case POST_ALL: return postAll(method, path, request.getArray());
                case PUT: return put(method);
                case DELETE: return  delete(method);
                default: return new Request();
            }
        }

        @Override
        protected void onPostExecute(Request request) {
            super.onPostExecute(request);
            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
                title = message = "Aguarde...";
            }

            if(result != null) {
                switch (request.getStatus()) {
                    case OK: result.onSuccess(request); break;
                    case CONNECTION_ERROR: result.onConnectionError(request); result.onGeneralError(request); break;
                    case JSON_ERROR: result.onInvalidValueError(request); result.onGeneralError(request); break;
                    default: result.onGeneralError(request); break;
                }
            }
            start();
        }

        private Request get(Method method, String path, long param) {
            if (param <= 0) {
                throw new IllegalArgumentException("You must inserts long param > 0 to GET http-method");
            }

            path = ROOT + path + F_GET + "&d=" + param;
            Request request = new Request(method);
            HttpURLConnection connection = connect(request, path, method.name());

            if (connection != null) {

                String json = readData(request, connection);
                if (request.getStatus() == WebServiceConnection.Status.READ_DATA_OK) {
                    request.setStatus(WebServiceConnection.Status.OK);
                    try {
                        request.setObject(new JSONObject(json));
                    } catch (JSONException e) {
                        Log.e("httpMethod.get", WebServiceConnection.Status.JSON_ERROR + ": " + json + " " + e.getMessage());
                        request.setStatus(WebServiceConnection.Status.JSON_ERROR);
                    }
                }
                connection.disconnect();
            }

            return request;
        }

        private Request getAll(Method method, String path, long value) {
            path = ROOT + path + F_GET;

            if(value > 0){
                path += "&a=" + value;
            }
            Request request = new Request(method);
            HttpURLConnection connection = connect(request, path, method.name());

            if (connection != null) {

                String json = readData(request, connection);
                if (request.getStatus() == WebServiceConnection.Status.READ_DATA_OK) {
                    request.setStatus(WebServiceConnection.Status.OK);
                    try {
                        request.setArray(new JSONArray(json));
                    } catch (JSONException e) {
                        request.setStatus(WebServiceConnection.Status.JSON_ERROR);
                        Log.e("JSON", json);
                        Log.e("httpMethod.getAll", WebServiceConnection.Status.JSON_ERROR.name() + " " + e.getMessage());
                    }
                }
                connection.disconnect();
            }
            return request;
        }

        private Request post(Method method, String path, JSONObject json) {

            path = ROOT + path;
            Request request = new Request(method);
            HttpURLConnection connection = connect(request, path, Method.POST.name());
            if (connection != null) {

                writeData(request, connection, json.toString());
                if (request.getStatus() == WebServiceConnection.Status.WRITE_DATA_OK) {

                    String data = readData(request, connection);
                    if (request.getStatus() == WebServiceConnection.Status.READ_DATA_OK) {

                        try {
                            request.setObject(new JSONObject(data));
                            request.setStatus(WebServiceConnection.Status.OK);

                        } catch (JSONException e) {
                            request.setStatus(WebServiceConnection.Status.JSON_ERROR);
                            Log.e("data", data);
                            Log.e("httpMethod.post", WebServiceConnection.Status.JSON_ERROR.name() + " " + e.getMessage());
                        }
                    }
                }
                connection.disconnect();
            }
            return request;
        }

        private Request postAll(Method method, String path, JSONArray array) {

            path = ROOT + path + "?o=" + true;
            Request request = new Request(method);
            HttpURLConnection connection = connect(request, path, Method.POST.name());

            if (connection != null) {

                writeData(request, connection, array.toString());
                if (request.getStatus() == WebServiceConnection.Status.WRITE_DATA_OK) {

                    String data = readData(request, connection);
                    if (request.getStatus() == WebServiceConnection.Status.READ_DATA_OK) {

                        try {

                            request.setArray(new JSONArray(data));
                            request.setStatus(WebServiceConnection.Status.OK);

                        } catch (JSONException e) {
                            request.setStatus(WebServiceConnection.Status.JSON_ERROR);
                            Log.e("JSON", data);
                            Log.e("httpMethod.postAll", WebServiceConnection.Status.JSON_ERROR.name() + " " + e.getMessage());
                        }
                    }
                }
                connection.disconnect();
            }
            return request;
        }

        private Request put(Method method) {
            return new Request(method);
        }

        private Request delete(Method method) {
            return new Request(method);
        }

        private HttpURLConnection connect(Request request, String path, String method) {

            method = method.equals(Method.POST_ALL.name()) ? Method.POST.name() : method;
            method = method.equals(Method.GET_ALL.name()) ? Method.GET.name() : method;

            boolean outPut = method.equals(Method.POST.name()) || method.equals(Method.POST_ALL.name());

            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
                connection.setDoInput(true);
                connection.setDoOutput(outPut);
                connection.connect();
                request.setStatus(WebServiceConnection.Status.OK_CONNECTION);
                return connection;

            } catch (IOException e) {
                Log.e("CONNECTION_ERROR", "URL: " + path + " [" + e.getMessage() + "]");
                request.setStatus(WebServiceConnection.Status.CONNECTION_ERROR);
            }
            return null;
        }

        private String readData(Request request, HttpURLConnection connection) {

            try {
                InputStream input = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder builder = new StringBuilder();
                String json;
                while ((json = reader.readLine()) != null) {
                    builder.append(json).append("\n");
                }

                reader.close();
                input.close();
                connection.disconnect();
                request.setStatus(WebServiceConnection.Status.READ_DATA_OK);
                return builder.toString().trim();

            } catch (IOException e) {
                Log.e("readData", WebServiceConnection.Status.ERROR_READ_DATA.name() + " " + e.getMessage());
                request.setStatus(WebServiceConnection.Status.ERROR_READ_DATA);
            }
            return "";
        }

        private void writeData(Request request, HttpURLConnection connection, String data) {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write("JSON=" + data);
                writer.flush();
                writer.close();
                request.setStatus(WebServiceConnection.Status.WRITE_DATA_OK);
            } catch (IOException e) {
                request.setStatus(WebServiceConnection.Status.ERROR_WRITE_DATA);
                Log.e("writeData.error", WebServiceConnection.Status.ERROR_WRITE_DATA.name() + " " + e.getMessage());
            }
        }
    }

    public static class Request {

        private Status status;
        private Method method;
        private JSONObject object;
        private JSONArray array;
        private long value;

        Request() {
            this(Method.GET);
        }

        Request(Method method) {
            this(method, new JSONObject(), new JSONArray(), -1);
        }

        private Request(Method method, JSONObject object, JSONArray array, long value){

            this.status = Status.GENERAL_ERROR;
            this.method = method;
            this.object = object;
            this.array = array;
            this.value = value;
        }

        public Status getStatus() {
            return status;
        }

        void setStatus(Status status) {
            this.status = status;
        }

        JSONObject getObject() {
            return object;
        }

        void setObject(JSONObject object) {
            this.object = object;
        }

        JSONArray getArray() {
            return array;
        }

        void setArray(JSONArray array) {
            this.array = array;
        }

        Method getMethod() {
            return method;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }
    }
}