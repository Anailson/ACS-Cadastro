package tcc.acs_cadastro_mobile.interfaces;

import android.widget.ArrayAdapter;

import java.util.List;

public interface ITextSearchListener<E>{

    List<ISearcher> searchByText(String search);

    List<ISearcher> searchByNumber(String search);

    ArrayAdapter<E> updateListView(List<ISearcher> list);
}