package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.StepsController;
import tcc.acs_cadastro_mobile.models.ResidenceModel;

public class ResidenceListAdapter extends ArrayAdapter<ResidenceModel> {

    private Context context;
    private List<ResidenceModel> residences;

    public ResidenceListAdapter(Context context, List<ResidenceModel> residences) {
        super(context, R.layout.item_list_residence, residences);

        this.context = context;
        this.residences = residences;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layout.inflate(R.layout.item_list_residence, parent, false);

        TextView txtAddress = (TextView) convertView.findViewById(R.id.txt_rsd_address);
        TextView txtCep = (TextView) convertView.findViewById(R.id.txt_rsd_cep);
        TextView txtHomePhone = (TextView) convertView.findViewById(R.id.txt_rsd_home_phone);

        ResidenceModel residence = residences.get(position);

        txtAddress.setText(residence.getCompleteAddress());
        txtCep.setText(getText(R.string.txt_rsd_cep, residence.getCep()));
        txtHomePhone.setText(getText(R.string.txt_rsd_home_phone, residence.getHomePhone()));

        convertView.setOnClickListener(getListener(position));
        return convertView;
    }

    private View.OnClickListener getListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = residences.get(position) + " " + context.getString(R.string.msg_edit_citizen);
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
