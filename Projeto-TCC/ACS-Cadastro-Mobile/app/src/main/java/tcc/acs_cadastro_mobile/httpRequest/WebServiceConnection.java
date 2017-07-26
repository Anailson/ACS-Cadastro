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

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskResponse;

public class WebServiceConnection {

    //private final String HOST = "http://10.0.0.7/";
    private final String HOST = "http://10.11.162.4/";
    private final String ROOT = HOST + "ACS-Cadastro-Web/webservice/";
    private final String F_GET = "?f=get";

    private Context context;
    private IAsyncTaskResponse callback;

    WebServiceConnection(Context context, IAsyncTaskResponse callback) {
        this.context = context;
        this.callback = callback;
    }

    void makeRequest(String path){
        makeRequest(Method.GET_ALL, path, -1, null, null);
    }

    void makeRequest(String path, long param) {
        makeRequest(Method.GET, path, param, null, null);
    }

    void makeRequest(String path, JSONObject param) {
        makeRequest(Method.POST, path, -1, param, null);
    }

    void makeRequest(String path, JSONArray param) {
        makeRequest(Method.POST_ALL, path, -1, null, param);
    }

    private void makeRequest(Method method, String path, long l, JSONObject object, JSONArray array) {
        MakeConnection connection = new MakeConnection(path, l, object, array);
        connection.setCallback(callback);
        connection.execute(method);
    }

    public enum Status {
        OK, READ_DATA_OK, ERROR_READ_DATA,
        WRITE_DATA_OK, ERROR_WRITE_DATA,
        OK_CONNECTION, CONNECTION_ERROR,
        JSON_ERROR, GENERAL_ERROR
    }

    enum Method {
        GET, GET_ALL, POST, POST_ALL, PUT, DELETE
    }

    private class MakeConnection extends AsyncTask<Method, Void, Request> {
        private String path;
        private long param;
        private JSONObject json;
        private JSONArray array;
        private ProgressDialog dialog;
        private IAsyncTaskResponse callback;

        private MakeConnection(String path, long l, JSONObject object, JSONArray array) {
            this.path = path;
            this.callback = null;
            this.json = object;
            this.array = array;
            this.param = l;
        }

        private void setCallback(IAsyncTaskResponse callback) {
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            //dialog.setTitle("Buscando");
            dialog.setMessage("Acessando dados do sistema...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Request doInBackground(Method... methods) {
            Method method = methods[0];
            switch (method) {
                case GET: return get(method, path, param);
                case GET_ALL: return getAll(method, path);
                case POST: return post(method, path, json);
                case POST_ALL: return postAll(method, path, array);
                case PUT: return put(method);
                case DELETE: return delete(method);
            }
            return new Request(method);
        }

        @Override
        protected void onPostExecute(Request request) {
            super.onPostExecute(request);

            switch (request.getMethod()) {
                case GET: callback.get(request, request.getObject()); break;
                case GET_ALL: callback.getAll(request, request.getArray()); break;
                case PUT: callback.update(request, request.getObject()); break;
                case DELETE: callback.delete(request, request.getObject()); break;
                case POST:
                    try {
                        callback.insert(request, request.getObject().getInt("id"));
                    } catch (JSONException e) {
                        callback.insert(request, -1);
                    }
                    break;
                case POST_ALL:
                    try {
                        callback.insertAll(request, request.getObject().getInt("rows"));
                    } catch (JSONException e) {
                        callback.insertAll(request, -1);
                    }
                break;
            }
            dialog.dismiss();
        }

        private Request get(Method method, String path, long param) {
            if (param <= 0) {
                throw new IllegalArgumentException("You must inserts long param > 0 to GET http-method on makeRequest");
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
                        Log.e("httpMethod.get", WebServiceConnection.Status.JSON_ERROR + " " + e.getMessage());
                        request.setStatus(WebServiceConnection.Status.JSON_ERROR);
                    }
                }
                connection.disconnect();
            }
            return request;
        }

        private Request getAll(Method method, String path) {
            path = ROOT + path + F_GET;
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
                            Log.e("httpMethod.postAll", WebServiceConnection.Status.JSON_ERROR.name() + " " + e.getMessage());
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
                            request.setObject(new JSONObject(data));
                            request.setStatus(WebServiceConnection.Status.OK);

                        } catch (JSONException e) {
                            request.setStatus(WebServiceConnection.Status.JSON_ERROR);
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
            boolean outPut = method.equals(Method.POST.name());

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
                Log.e("CONNECTION_ERROR", "URL: " + path + " " + e.getMessage());
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

    public class Request {

        private Status status;
        private Method method;
        private JSONObject object;
        private JSONArray array;

        Request(Method method) {
            this.status = Status.GENERAL_ERROR;
            this.method = method;
            this.object = null;
            this.array = null;
        }

        Method getMethod() {
            return method;
        }

        public Status getStatus() {
            return status;
        }

        void setStatus(Status status) {
            this.status = status;
        }

        private JSONObject getObject() {
            return object;
        }

        private void setObject(JSONObject object) {
            this.array = null;
            this.object = object;
        }

        private JSONArray getArray() {
            return array;
        }

        private void setArray(JSONArray array) {
            this.object = null;
            this.array = array;
        }
    }
}