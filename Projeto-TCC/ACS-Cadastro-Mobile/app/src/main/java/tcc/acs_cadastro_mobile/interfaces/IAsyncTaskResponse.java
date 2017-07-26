package tcc.acs_cadastro_mobile.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

import tcc.acs_cadastro_mobile.httpRequest.WebServiceConnection;

public interface IAsyncTaskResponse {


    void get(WebServiceConnection.Request request, JSONObject jsonObject);

    void getAll(WebServiceConnection.Request request, JSONArray jsonArray);

    void insert(WebServiceConnection.Request request, int id);

    void insertAll(WebServiceConnection.Request request, int total);

    void update(WebServiceConnection.Request request, JSONObject jsonObject);

    void delete(WebServiceConnection.Request request, JSONObject jsonObject);


    interface Get {
        void get(WebServiceConnection.Request request, JSONObject jsonObject);
    }

    interface GetAll {
        void getAll(WebServiceConnection.Request request, JSONArray jsonArray);
    }

    interface Insert {
        void insert(WebServiceConnection.Request request, int id);
    }

    interface InsertAll {
        void insertAll(WebServiceConnection.Request request, int rows);
    }

    interface Update {
        void update(WebServiceConnection.Request request, JSONObject jsonObject);
    }

    interface Delete {
        void delete(WebServiceConnection.Request request, JSONObject jsonObject);
    }

    class Responses implements IAsyncTaskResponse {

        public Get get;
        public GetAll getAll;
        public Insert insert;
        public InsertAll insertAll;
        public Update update;
        public Delete delete;

        public Responses() {
            get = new Get() {
                @Override
                public void get(WebServiceConnection.Request request, JSONObject jsonObject) {
                }
            };

            getAll = new GetAll() {
                @Override
                public void getAll(WebServiceConnection.Request request, JSONArray jsonArray) {
                }
            };

            insert = new Insert() {
                @Override
                public void insert(WebServiceConnection.Request request, int id) {
                }
            };

            update = new Update() {
                @Override
                public void update(WebServiceConnection.Request request, JSONObject jsonObject) {
                }
            };

            delete = new Delete() {
                @Override
                public void delete(WebServiceConnection.Request request, JSONObject jsonObject) {
                }
            };
        }

        @Override
        public void get(WebServiceConnection.Request request, JSONObject jsonObject) {
            get.get(request, jsonObject);
        }

        @Override
        public void getAll(WebServiceConnection.Request request, JSONArray jsonArray) {
            getAll.getAll(request, jsonArray);
        }

        @Override
        public void insert(WebServiceConnection.Request request, int id) {
            insert.insert(request, id);
        }

        @Override
        public void insertAll(WebServiceConnection.Request request, int total) {
            insertAll.insertAll(request, total);
        }

        @Override
        public void update(WebServiceConnection.Request request, JSONObject jsonObject) {
            update.update(request, jsonObject);
        }

        @Override
        public void delete(WebServiceConnection.Request request, JSONObject jsonObject) {
            delete.delete(request, jsonObject);
        }
    }
}