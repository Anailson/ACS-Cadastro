package tcc.acs_cadastro_mobile.interfaces;

import java.util.ArrayList;

public interface IAsyncTaskResponse<E> {

    void get(E e);

    void getAll(ArrayList<E> list);

    void insert(int id);

    void update(E object);

    void delete(E object);
}
