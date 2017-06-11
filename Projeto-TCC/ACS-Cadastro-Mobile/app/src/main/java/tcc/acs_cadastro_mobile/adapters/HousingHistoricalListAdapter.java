package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;


public class HousingHistoricalListAdapter extends ArrayAdapter<HousingHistoricalModel> {

    private Context context;
    private List<HousingHistoricalModel> historicals;

    public HousingHistoricalListAdapter(Context context, RealmList<HousingHistoricalModel> historical) {
        super(context, R.layout.item_list_new_resp, historical);

        this.context = context;
        this.historicals = historical;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layout.inflate(R.layout.item_list_citizen, parent, false);

        TextView familyRecord = (TextView) convertView.findViewById(R.id.edt_new_family_record);
        TextView numSusResp = (TextView) convertView.findViewById(R.id.edt_new_num_sus_resp);
        TextView birthDate = (TextView) convertView.findViewById(R.id.edt_new_birth_date_resp);
        TextView familyIncome = (TextView) convertView.findViewById(R.id.edt_new_family_income);
        TextView nMembers = (TextView) convertView.findViewById(R.id.edt_new_n_members);
        TextView livesSince = (TextView) convertView.findViewById(R.id.edt_new_lives_since);
        CheckBox moved = (CheckBox) convertView.findViewById(R.id.chb_new_moved);

        HousingHistoricalModel historical = historicals.get(position);

        familyRecord.setText(String.valueOf(historical.getFamilyRecord()));
        numSusResp.setText(String.valueOf(historical.getNumSus()));
        birthDate.setText(historical.getBirthDate());
        familyIncome.setText(String.format("%s", historical.getFamilyIncome()));
        nMembers.setText(historical.getnMembers());
        livesSince.setText(historical.getLivesSince());
        moved.setChecked(historical.isMoved());

        return convertView;
    }
}
