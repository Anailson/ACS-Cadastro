package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by iuri on 03/10/2016.
 */
public class AdapterAdicionarPacienteConsulta extends ArrayAdapter<Paciente> {

    private Context context;
    private long idConsulta;
    private Adapter adapter;
    // private List<Paciente> pacientes;

    public AdapterAdicionarPacienteConsulta(Context context, int view, List<Paciente> pacientes, long idConsulta) {
        super(context, view, pacientes);
        this.context = context;
        this.idConsulta = idConsulta;
        this.adapter = adapter;
        //     this.pacientes = pacientes;

    }

    ViewHolder holder = null;

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final Paciente paciente = getItem(position);

        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.paciente_consulta_add_list_item, null);
            holder = new ViewHolder();

            holder.textNome = (TextView) row.findViewById(R.id.list_consulta_item_paciente_nome);
            holder.textTelefone = (TextView) row.findViewById(R.id.list_consulta_item_paciente_telefone);
            holder.imgAdicionarNaConsulta = (ImageView) row.findViewById(R.id.img_add_consulta);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.textNome.setText(paciente.getNome());
        holder.textTelefone.setText(paciente.getTelefone());
        holder.imgAdicionarNaConsulta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Realm realm = Realm.getDefaultInstance();

                        RealmResults<Paciente> pacientes = realm.where(Consulta.class)
                                .equalTo("id", idConsulta)
                                .findFirst()
                                .getPacientes().where().greaterThan("ordem", 0).findAllSorted("ordem", Sort.ASCENDING);

                        int posicao = 1;

                        if (pacientes.size() > 0) {
                            posicao = pacientes.get(pacientes.size() - 1).getOrdem() + 1;
                        }

                        realm.beginTransaction();

                        paciente.setOrdem(posicao);

                        realm.commitTransaction();

                        realm.close();

                        notifyDataSetChanged();
//                        notifyDataSetInvalidated();

                        Toast.makeText(v.getContext(), "Adicionado Posicao = " + posicao, Toast.LENGTH_SHORT).show();

                    }
                }
        );


        return row;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    static class ViewHolder {
        TextView textNome;
        TextView textTelefone;
        ImageView imgAdicionarNaConsulta;

    }

}



