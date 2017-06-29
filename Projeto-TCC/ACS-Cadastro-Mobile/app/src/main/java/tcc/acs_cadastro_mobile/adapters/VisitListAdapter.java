package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.VisitModel;

public class VisitListAdapter extends ArrayAdapter<VisitModel> {

    private Context context;
    private List<VisitModel> visits;

    public VisitListAdapter(@NonNull Context context, @NonNull List<VisitModel> visits) {
        super(context, R.layout.item_list_visit, visits);
        this.context = context;
        this.visits = visits;
    }
}
