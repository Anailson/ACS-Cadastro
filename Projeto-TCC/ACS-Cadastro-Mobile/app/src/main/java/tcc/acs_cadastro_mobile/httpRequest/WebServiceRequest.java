package tcc.acs_cadastro_mobile.httpRequest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class WebServiceRequest {

    private final String HOST = "http://10.0.0.7/";
    private final String ROOT = HOST + "ACS-Cadastro-Web/webservice/";
    private final String F_GET = "?f=get";
    private final String VALUE = "&d=";

    private Request request;
    private Table table;

    WebServiceRequest(Table table) {
        this.table = table;
        this.request = new Request();
    }

    public Request get() {
        String path = ROOT + table.getValue() + F_GET;
        request.getAll(path, Method.GET);
        return request;
    }

    public Request get(long numSus) {

        String path = ROOT + table.getValue()  + F_GET + VALUE + numSus;
        request.get(path, Method.GET);
        return request;
    }

    public Request post() {
        request.post(ROOT, Method.POST);
        return request;
    }

    private void setStatus(Status status) {
        this.request.status = status;
    }

    private void setArray(JSONArray json) {
        request.jObject = null;
        request.jArray = json;
    }

    private void setObject(JSONObject object) {
        request.jArray = null;
        request.jObject = object;
    }


    enum Table {
        AGENT("AgentsWebService.php");

        private String value;

        Table(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum Status {
        OK, OK_CONNECTION, CONNECTION_ERROR, JSON_ERROR, GENERAL_ERROR
    }

    private enum Method {
        GET, POST, PUT, DELETE
    }

    class Request {

        private JSONArray jArray;
        private JSONObject jObject;
        private Status status;

        Request() {
            status = Status.GENERAL_ERROR;
            jArray = null;
            jObject = null;
        }

        Status getStatus() {
            return status;
        }

        JSONArray getArray() {
            return jArray;
        }

        JSONObject getObject() {
            return jObject;
        }

        private void get(String path, Method method) {

            HttpURLConnection connection = connect(path, method.name());

            try {
                if (connection != null) {

                    String json = getData(connection);
                    setStatus(Status.OK);
                    setObject(new JSONObject(json));
                }

            } catch (JSONException e) {
                Log.e("httpMethod.get", e.getMessage());
                setStatus(Status.JSON_ERROR);
            }
        }

        private void getAll(String path, Method method) {

            HttpURLConnection connection = connect(path, method.name());
            try {
                if (connection != null) {

                    String data = getData(connection);
                    setStatus(Status.OK);
                    setArray(new JSONArray(data));
                }

            } catch (JSONException e) {
                Log.e("httpMethod.getAll", e.getMessage());
                setStatus(Status.JSON_ERROR);
            }
        }

        private void post(String path, Method method) {
            connect(path, method.name());
        }

        private HttpURLConnection connect(String path, String method) {

            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
                connection.setDoInput(true);
                connection.setDoOutput(false);
                connection.connect();
                setStatus(Status.OK_CONNECTION);
                return connection;

            } catch (IOException e) {
                Log.e("connection", e.getMessage());
                setStatus(Status.CONNECTION_ERROR);
            }
            return null;
        }

        private String getData(HttpURLConnection connection) {

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
                return builder.toString().trim();

            } catch (IOException e) {
                Log.e("httpMethod.getAll", e.getMessage());
                setStatus(Status.CONNECTION_ERROR);
            }
            return "";
        }
    }
}