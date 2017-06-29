package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;


public class HousingHistoricalListAdapter extends ArrayAdapter<HousingHistoricalModel> {

    private Context context;
    private RealmList<HousingHistoricalModel> historical;

    public HousingHistoricalListAdapter(Context context, RealmList<HousingHistoricalModel> historical) {
        super(context, R.layout.item_list_new_resp, historical);

        this.context = context;
        this.historical = historical;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layout.inflate(R.layout.item_list_new_resp, parent, false);

        TextView txtFamilyRecord = (TextView) convertView.findViewById(R.id.txt_new_family_record);
        TextView txtNumSusResp = (TextView) convertView.findViewById(R.id.txt_new_num_sus_resp);
        TextView txtBirthDate = (TextView) convertView.findViewById(R.id.txt_new_birth_date_resp);

        HousingHistoricalModel historical = this.historical.get(position);

        txtFamilyRecord.setText(getText(R.string.txt_rsd_family_record, historical.getFamilyRecord()));
        txtNumSusResp.setText(getText(R.string.txt_rsd_resp_num_sus, historical.getNumSus()));
        txtBirthDate.setText(getText(R.string.txt_rsd_resp_birth_date, historical.getBirthDate()));

        return convertView;
    }

    public RealmList<HousingHistoricalModel> getHousingHistorical(){
        return this.historical;
    }

    private String getText(int resource, long l){
        String text = StepsController.getDefaultOrValue(l);
        return getText(resource, text);
    }

    private String getText(int resource, String text){
        return String.format("%s: %s",context.getString(resource), text);
    }
}
