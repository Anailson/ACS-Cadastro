package tcc.acs_cadastro_mobile.interfaces;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public abstract class TextSearchChanged<E> implements TextWatcher {

    private ListView listView;

    public TextSearchChanged(ListView listView) {
        this.listView = listView;
    }

    public void setListView(ListView listView) {
        if (listView != null){
            this.listView = listView;
        }
    }

    protected abstract List<E> searchByText(String search);

    protected abstract List<E> searchByNumber(String search);

    protected abstract ArrayAdapter<E> updateListView(List<E> list);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        List<E> list;
        if (s.toString().matches("[0-9]+")) {
            list = searchByNumber(s.toString());
        } else {
            list = searchByText(s.toString());
        }
        if(list == null){
            list = new ArrayList<>();
        }
        listView.setAdapter(updateListView(list));
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
