package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.models.AccompanyModel;

public class AccompanyListAdapter  extends ArrayAdapter<AccompanyModel> {

    private Context context;
    private List<AccompanyModel> accompanies;

    public AccompanyListAdapter(Context context, List<AccompanyModel> accompanies) {
        super(context, R.layout.item_list_accompany, accompanies);
        this.context = context;
         this.accompanies = accompanies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layout.inflate(R.layout.item_list_accompany, parent, false);

        TextView txtRecord = (TextView) convertView.findViewById(R.id.txt_acc_record);
        TextView txtSusNum = (TextView) convertView.findViewById(R.id.txt_acc_sus_num);
        TextView txtName = (TextView) convertView.findViewById(R.id.txt_acc_name);

        AccompanyModel accompany = accompanies.get(position);

        txtRecord.setText(getText(R.string.txt_acc_record, accompany.getRecord()));
        txtSusNum.setText(getText(R.string.txt_ctz_num_sus, accompany.getNumSus()));
        txtName.setText(accompany.getName());

        return convertView;
    }

    private String getText(int resource, long l){
        return getText(resource, StepsController.getEmptyOrValue(l));
    }

    private String getText(int resource, String text){
        return String.format("%s: %s", context.getString(resource), text);
    }
}
