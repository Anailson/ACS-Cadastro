package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
import com.avisosms.iuri.avisasms.suporte.Dialogs;

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
    private AdapterListaDePacientes adapterListaPaciente;
    // private List<Paciente> pacientes;

    public AdapterAdicionarPacienteConsulta(Context context, int view, List<Paciente> pacientes, long idConsulta, AdapterListaDePacientes adapterListaPaciente) {
        super(context, view, pacientes);
        this.context = context;
        this.idConsulta = idConsulta;
        this.adapterListaPaciente = adapterListaPaciente;
        //    this.pacientes = pacientes;


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

                        final Realm realm = Realm.getDefaultInstance();

                        Consulta consulta = realm.where(Consulta.class).equalTo("id", idConsulta)
                                .findFirst();
                        RealmResults<Paciente> pacientes = consulta
                                .getPacientes().where().greaterThan("ordem", 0).findAllSorted("ordem", Sort.ASCENDING);

                        int posicao = 1;

                        if (pacientes.size() > 0) {
                            posicao = pacientes.get(pacientes.size() - 1).getOrdem() + 1;
                        }

                        final int finalPosicao = posicao;
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:


                                        realm.beginTransaction();

                                        paciente.setOrdem(finalPosicao);

                                        realm.commitTransaction();


                                        //List<Paciente> pacients = realm.where(Consulta.class).equalTo("id", idConsulta)
                                        //       .findFirst().getPacientes().where().lessThanOrEqualTo("ordem", 0).findAll();

                                        //adpt.addAll(pacients);

                                        notifyDataSetChanged();
                                        //adpt.notifyDataSetInvalidated();

                                        Toast.makeText(context, "Adicionado Posicao --- = " + finalPosicao, Toast.LENGTH_SHORT).show();

                                        adapterListaPaciente.notifyDataSetChanged();

                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Deseja adicionar o paciente na posição " +posicao+ "?").setPositiveButton("Sim", dialogClickListener)
                                .setNegativeButton("Não", dialogClickListener).show();

                        realm.close();//fechar depois do notifyDataSetChanged();
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



