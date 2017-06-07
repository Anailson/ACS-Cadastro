package tcc.acs_cadastro_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.ResidenceModel;

public class ResidenceListAdapter extends ArrayAdapter<ResidenceModel> {

    private Context context;
    private List<ResidenceModel> residences;

    public ResidenceListAdapter(Context context, List<ResidenceModel> residences) {
        super(context, R.layout.item_list_residence, residences);

        this.context = context;
        this.residences = residences;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layout.inflate(R.layout.item_list_residence, parent, false);

        TextView txtAddress = (TextView) convertView.findViewById(R.id.txt_rsd_address);
        TextView txtCep = (TextView) convertView.findViewById(R.id.txt_rsd_cep);
        TextView txtHomePhone = (TextView) convertView.findViewById(R.id.txt_rsd_home_phone);
        TextView txtReferencePhone = (TextView) convertView.findViewById(R.id.txt_rsd_reference_phone);

        ResidenceModel residence = residences.get(position);

        txtAddress.setText(residence.getAddress());
        txtCep.setText(residence.getCep());
        txtHomePhone.setText(residence.getHomePhone());
        txtReferencePhone.setText(residence.getReferencePhone());

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
}
