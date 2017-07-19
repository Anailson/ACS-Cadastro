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

import tcc.acs_cadastro_mobile.interfaces.IAsyncTaskRequest;

public class WebServiceConnection {

    private final String HOST = "http://10.11.162.4/";
    private final String ROOT = HOST + "ACS-Cadastro-Web/webservice/";
    private final String F_GET = "?f=get";

    private Context context;
    private IAsyncTaskRequest callback;

    WebServiceConnection(Context context, IAsyncTaskRequest callback) {

        this.context = context;
        this.callback = callback;
    }

    public void get(String path, long value) {

        String url = ROOT + path + F_GET + "&d=" + value;
        MakeConnection connection = new MakeConnection(url, Method.GET, null);
        connection.setCallback(callback);
        connection.execute();
    }

    public void getAll(String path) {
        String url = ROOT + path + F_GET;
        MakeConnection connection = new MakeConnection(url, Method.GET, null);
        connection.setCallback(callback);
        connection.execute();
    }

    public void insert(String path, JSONObject json) {
        String url = ROOT + path;
        MakeConnection connection = new MakeConnection(url, Method.POST, json);
        connection.setCallback(callback);
        connection.execute();
    }
/*
    public enum Table {
        AGENT("AgentsWebService.php"), ACCOMPANY("AccompanyWebService.php"), CITIZEN("CitizenWebService.php");

        private String value;

        Table(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
*/
    enum Status {
        OK, READ_DATA_OK, ERROR_READ_DATA,
        WRITE_DATA_OK, ERROR_WRITE_DATA,
        OK_CONNECTION, CONNECTION_ERROR,
        JSON_ERROR, GENERAL_ERROR
    }

    enum Method {
        GET, GET_ALL, POST, PUT, DELETE
    }

    private class MakeConnection extends AsyncTask<Void, Void, Request> {
        private String path;
        private Method method;
        private JSONObject json;
        private ProgressDialog dialog;
        private IAsyncTaskRequest callBack;

        private MakeConnection(String path, Method method, JSONObject json) {
            this.path = path;
            this.method = method;
            this.json = json;
            this.callBack = null;
        }

        private void setCallback(IAsyncTaskRequest callback) {
            this.callBack = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle("Buscando");
            dialog.setMessage("Buscando dados no sistema...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Request doInBackground(Void... voids) {

            if (method == Method.GET) {

                return get(path);

            } else if (method == Method.GET_ALL) {

                return getAll(path);

            } else if (method == Method.POST) {

                return post(path, json);

            } else if (method == Method.PUT) {

                return put();

            } else if (method == Method.DELETE) {

                return delete();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Request request) {
            super.onPostExecute(request);

            if (request == null) {
                request = new Request(method);
            }

            callBack.onRequest(request);
            dialog.dismiss();
        }

        private Request get(String path) {
            Request request = new Request(method);
            HttpURLConnection connection = connect(request, path, method.name());

            if (connection != null) {

                String json = readData(request, connection);
                if (request.getStatus() == WebServiceConnection.Status.READ_DATA_OK) {
                    request.setStatus(WebServiceConnection.Status.OK);
                    try {
                        request.setObject(new JSONObject(json));
                    } catch (JSONException e) {
                        Log.e("json", json);
                        Log.e("httpMethod.get", WebServiceConnection.Status.JSON_ERROR + " " + e.getMessage());
                        request.setStatus(WebServiceConnection.Status.JSON_ERROR);
                    }
                }
                connection.disconnect();
            }
            return request;
        }

        private Request getAll(String path) {
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
                        Log.e("json", json);
                        Log.e("httpMethod.getAll", WebServiceConnection.Status.JSON_ERROR.name() + " " + e.getMessage());
                    }
                }
                connection.disconnect();
            }
            return request;
        }

        private Request post(String path, JSONObject json) {
            Request request = new Request(method);
            HttpURLConnection connection = connect(request, path, Method.POST.name());
            if (connection != null) {

                writeData(request, connection, json);
                if (request.getStatus() == WebServiceConnection.Status.WRITE_DATA_OK) {

                    String data = readData(request, connection);
                    if (request.getStatus() == WebServiceConnection.Status.READ_DATA_OK) {

                        request.setStatus(WebServiceConnection.Status.OK);
                        try {
                            request.setObject(new JSONObject(data));

                        } catch (JSONException e) {
                            request.setStatus(WebServiceConnection.Status.JSON_ERROR);
                            Log.e("json", "[" + json + "]");
                            Log.e("httpMethod.post", WebServiceConnection.Status.JSON_ERROR.name() + " " + e.getMessage());
                        }
                    }
                }
                connection.disconnect();
            }
            return request;
        }

        private Request put() {
            return null;
        }

        private Request delete() {
            return null;
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
                Log.e("readData", builder.toString());
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

        private void writeData(Request request, HttpURLConnection connection, JSONObject json) {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                Log.e("writeData", "JSON=" + json.toString());
                writer.write("JSON=" + json.toString());
                writer.flush();
                writer.close();
                request.setStatus(WebServiceConnection.Status.WRITE_DATA_OK);
            } catch (IOException e) {
                request.setStatus(WebServiceConnection.Status.ERROR_WRITE_DATA);
                Log.e("writeData", WebServiceConnection.Status.ERROR_WRITE_DATA.name() + " " + e.getMessage());
            }
        }
    }

    public class Request {

        private JSONArray jArray;
        private JSONObject jObject;
        private Status status;
        private Method method;

        Request(Method method) {
            this.status = Status.GENERAL_ERROR;
            this.method = method;
            this.jArray = null;
            this.jObject = null;
        }

        private void setArray(JSONArray json) {
            jObject = null;
            jArray = json;
        }

        private void setObject(JSONObject object) {
            jArray = null;
            jObject = object;
        }

        Method getMethod() {
            return method;
        }

        Status getStatus() {
            return status;
        }

        void setStatus(Status status) {
            this.status = status;
        }

        JSONObject getJsonObject() {
            return jObject;
        }
        JSONArray getJsonArray() {
            return jArray;
        }
    }
}