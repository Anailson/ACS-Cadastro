package tcc.acs_cadastro_mobile.interfaces;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import java.util.List;

public interface ITextSearchChanged<E>{

    List<E> searchByName(String search);

    List<E> searchByNumber(String search);

    ArrayAdapter<E> updateListView(Context context, List<E> list);
}