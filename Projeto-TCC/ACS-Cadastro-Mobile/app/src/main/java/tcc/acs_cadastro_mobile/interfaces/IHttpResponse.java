package tcc.acs_cadastro_mobile.interfaces;

import java.util.List;

public interface IHttpResponse<E> {

    void get(E object);

    void getAll(List<E> list);

    void insert(int id);

    void insertAll(int[] indexes);

    void update(E object);

    void delete(E object);

    interface Get<E>{
        void get(E jsonObject);
    }

    interface GetAll<E> {
        void getAll(List<E> list);
    }

    interface Insert {
        void insert(int id);
    }

    interface InsertAll {
        void insertAll(int[] indexes);
    }

    interface Update<E> {
        void update(E object);
    }

    interface Delete<E> {
        void delete(E object);
    }

    class Responses <E> implements IHttpResponse<E> {

        public Get<E> get;
        public GetAll<E> getAll;
        public InsertAll insertAll;
        public Insert insert;
        public Update<E> update;
        public Delete<E> delete;

        public Responses() {
            getAll = new GetAll<E>() {

                @Override
                public void getAll(List<E> list) {
                }
            };

            insert = new Insert() {
                @Override
                public void insert(int id) {
                }
            };

            update = new Update<E>() {
                @Override
                public void update(E object) {
                }
            };

            delete = new Delete<E>() {
                @Override
                public void delete(E object) {
                }
            };
        }

        @Override
        public void get(E object) {
            get.get(object);
        }

        @Override
        public void getAll(List<E> list) {
            getAll.getAll(list);
        }

        @Override
        public void insert(int id) {
            insert.insert(id);
        }

        @Override
        public void insertAll(int[] indexes) {
            insertAll.insertAll(indexes);
        }

        @Override
        public void update(E object) {
            update.update(object);
        }

        @Override
        public void delete(E object) {
            delete.delete(object);
        }
    }
}