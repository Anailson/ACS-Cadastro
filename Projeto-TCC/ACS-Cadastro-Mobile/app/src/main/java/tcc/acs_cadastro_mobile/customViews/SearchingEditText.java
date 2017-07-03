package tcc.acs_cadastro_mobile.customViews;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.interfaces.ITextSearchListener;

public class SearchingEditText extends AppCompatEditText implements TextWatcher {

    private ITextSearchListener listener;
    private ListView listView;

    public SearchingEditText(Context context) {
        super(context);
        init();
    }

    public SearchingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchingEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setSearchListener(ListView listView, ITextSearchListener listener){
        this.listView = listView;
        if(listener != null){
            this.listener = listener;
        }
    }

    private void init(){
        setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_menu_search, 0);
        addTextChangedListener(this);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if(listener == null){
            listener = getListener();
        }
        List list;
        if (s.toString().matches("[0-9]+")) {
            list = listener.search(Integer.valueOf(s.toString()));
        } else {
            list = listener.search(s.toString());
        }

        ArrayAdapter adapter = getArrayAdapter(list);
        if(listView != null && adapter != null){
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}


    @Override
    public void afterTextChanged(Editable s) {}

    public ArrayAdapter getArrayAdapter(List list) {
        if(list == null) return listener.updateListView(new ArrayList<ISearcher>());

        List<ISearcher> founded = new ArrayList<>();
        for(Object obj : list){
            try {
                founded.add((ISearcher) obj);
            }catch (ClassCastException e){
                e.initCause(new Throwable("You must implements ISearcher interface on you object "
                        + obj.getClass().getName() + "."));
            }
        }
        return listener.updateListView(founded);
    }

    private ITextSearchListener getListener(){
        return new ITextSearchListener() {
            @Override
            public List<ISearcher> search(String search) {
                return null;
            }

            @Override
            public List<ISearcher> search(int search) {
                return null;
            }

            @Override
            public ArrayAdapter updateListView(List list) {
                return null;
            }
        };
    }
}
