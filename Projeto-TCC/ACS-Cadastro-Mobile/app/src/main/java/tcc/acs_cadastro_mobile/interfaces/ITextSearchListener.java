package tcc.acs_cadastro_mobile.interfaces;

import android.widget.ArrayAdapter;

import java.util.List;

public interface ITextSearchListener<E>{

    List<ISearcher> search(String search);

    List<ISearcher> search(int search);

    ArrayAdapter<E> updateListView(List<ISearcher> list);
}