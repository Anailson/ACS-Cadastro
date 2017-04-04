package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.util.List;

/**
 * Created by iuri on 03/10/2016.
 */
public class AdapterAdicionarPacienteAgendamento extends ArrayAdapter<Paciente> {

    private Context context;
    // private List<Paciente> pacientes;


    public AdapterAdicionarPacienteAgendamento(Context context, int view, List<Paciente> pacientes) {
        super(context, view, pacientes);
        this.context = context;

        //     this.pacientes = pacientes;

    }

    ViewHolder holder = null;

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Paciente paciente = getItem(position);

        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.paciente_agendamento_add_list_item, null);
            holder = new ViewHolder();

            holder.textNome = (TextView) row.findViewById(R.id.list_item_paciente_nome);
            holder.textTelefone = (TextView) row.findViewById(R.id.list_item_paciente_telefone);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textNome.setText(paciente.getNome());
        holder.textTelefone.setText(paciente.getTelefone());

        return row;
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    static class ViewHolder {
        TextView textNome;
        TextView textTelefone;

    }

}



