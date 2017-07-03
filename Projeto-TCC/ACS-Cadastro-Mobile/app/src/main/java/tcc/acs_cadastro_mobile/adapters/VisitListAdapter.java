package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.VisitModel;

public class VisitListAdapter extends ArrayAdapter<VisitModel> {

    private Context context;
    private List<VisitModel> visits;

    public VisitListAdapter(@NonNull Context context, @NonNull List<VisitModel> visits) {
        super(context, R.layout.item_list_visit, visits);
        this.context = context;
        this.visits = visits;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layout.inflate(R.layout.item_list_visit, parent, false);

        TextView txtRecord = (TextView) convertView.findViewById(R.id.txt_vst_record);
        TextView txtSusNum = (TextView) convertView.findViewById(R.id.txt_vst_sus_num);
        TextView txtName = (TextView) convertView.findViewById(R.id.txt_vst_name);

        VisitModel visit = visits.get(position);

        txtRecord.setText(getText(R.string.txt_acc_record, visit.getRecord()));
        txtSusNum.setText(getText(R.string.txt_ctz_num_sus, visit.getNumSus()));
        txtName.setText(visit.getName());

        convertView.setOnClickListener(getListener(position));

        return convertView;
    }

    private View.OnClickListener getListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = visits.get(position) + " " + context.getString(R.string.msg_edit_citizen);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private String getText(int resource, long l){
        return getText(resource, StepsController.getDefaultOrValue(l));
    }

    private String getText(int resource, String text){
        return String.format("%s: %s", context.getString(resource), text);
    }
}
