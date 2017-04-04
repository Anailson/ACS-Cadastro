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
import tcc.acs_cadastro_mobile.models.CitizenModel;

public class CitizenListAdapter extends ArrayAdapter<CitizenModel> {

    private Context context;
    private List<CitizenModel> citizens;

    public CitizenListAdapter(Context context, List<CitizenModel> citizens) {
        super(context, R.layout.list_citizens, citizens);

        this.context = context;
        this.citizens = citizens;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layout.inflate(R.layout.list_citizens, parent, false);

        TextView txtName = (TextView) convertView.findViewById(R.id.txt_ctz_name);
        TextView txtSusNum = (TextView) convertView.findViewById(R.id.txt_ctz_sus_num);
        TextView txtAddress = (TextView) convertView.findViewById(R.id.txt_ctz_address);
        TextView txtPhone = (TextView) convertView.findViewById(R.id.txt_ctz_phone);

        CitizenModel citizen = citizens.get(position);

        txtName.setText(citizen.getName());
        txtSusNum.setText(citizen.getSusNum());
        txtAddress.setText(citizen.getAddress());
        txtPhone.setText(citizen.getPhone());

        convertView.setOnClickListener(getListener(position));
        return convertView;
    }

    private View.OnClickListener getListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = citizens.get(position) + " " + context.getString(R.string.msg_edit_citizen);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        };
    }

}
