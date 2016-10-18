package com.avisosms.iuri.avisasms.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.suporte.Dialogs;

import java.util.Date;
import java.util.List;

/**
 * Created by iuri on 6/8/2016.
 */
public class AdapterListaDeMedicosAddPaciente extends ArrayAdapter<Medico> {

    Activity mContext;
    Date date;

    public AdapterListaDeMedicosAddPaciente(Context context, List<Medico> medicos, Date date) {
        super(context, R.layout.medico_layout, medicos);
        this.mContext = (Activity) context;
        this.date = date;
    }

    @Override
    public long getItemId(final int position) {
        return getItem(position).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    static class ViewHolder {
        TextView textNome;
        TextView textEspecialidade;
        TextView textTelefone;
        FloatingActionButton btnAdicionarPaciente;
        View view;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Medico m = getItem(position);
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
                    /*if (row == null) {*/ //Checkbox precisa ter uma lista exclusiva, http://webcache.googleusercontent.com/search?q=cache:http://stackoverflow.com/questions/16350670/listview-viewholder-checkbox-state&gws_rd=cr&ei=5sFXV_G6M4f4wASc5LjIDg
            // row = LayoutInflater.from(mContext).inflate(R.layout.dynamiclistview_list_row, parent, false);
            /*LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.dynamiclistview_list_row, null/*, parent, false);*/

            holder = new ViewHolder();
            row = LayoutInflater.from(mContext).inflate(R.layout.agenda_listview_item_add_paciente, parent, false);

            holder.textNome = (TextView) row.findViewById(R.id.list_medico_nome);
            holder.textEspecialidade = (TextView) row.findViewById(R.id.list_medico_especialidade);
            holder.textTelefone = (TextView) row.findViewById(R.id.list_medico_telefone);
            holder.view = (View) row.findViewById(R.id.list_medico_cor);
            holder.btnAdicionarPaciente = (FloatingActionButton) row.findViewById(R.id.list_medico_especialidade_add_paciente);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textNome.setText(m.getNome());
        holder.textEspecialidade.setText(m.getEspecialidade());
        holder.textTelefone.setText(m.getTelefone());

        holder.view.setBackgroundColor(m.getCorIndicativa());

        holder.btnAdicionarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Add pacientes", Toast.LENGTH_SHORT).show();
                Dialogs.listarPacientes(v.getContext(), m, date);
            }
        });

        return row;
    }

}
