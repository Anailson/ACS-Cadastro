package com.avisosms.iuri.avisasms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.fragments.MedicoAdicionarEditar;
import com.avisosms.iuri.avisasms.objetos.Medico;

import java.util.List;

/**
 * Created by iuri on 6/8/2016.
 */
public class AdapterListaAgendaDeMedicos extends ArrayAdapter<Medico> {

    Activity mContext;

    public AdapterListaAgendaDeMedicos(Context context, List<Medico> medicos) {
        super(context, R.layout.agenda, medicos);
        this.mContext = (Activity) context;
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
        ImageView imgEditar;
        View view;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Medico m = getItem(position);
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {

            holder = new ViewHolder();
            row = LayoutInflater.from(mContext).inflate(R.layout.agenda_medico_listview_item, parent, false);

            holder.textNome = (TextView) row.findViewById(R.id.list_medico_nome);
            holder.textEspecialidade = (TextView) row.findViewById(R.id.list_medico_especialidade);
            holder.textTelefone = (TextView) row.findViewById(R.id.list_medico_telefone);
            holder.view = (View) row.findViewById(R.id.list_medico_cor);
            holder.imgEditar = (ImageView) row.findViewById(R.id.imgEditar);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textNome.setText(m.getNome());
        holder.textEspecialidade.setText(m.getEspecialidade());
        holder.textTelefone.setText(m.getTelefone());

        holder.view.setBackgroundColor(m.getCorIndicativa());

        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), MedicoAdicionarEditar.class);
                i.putExtra("editar", true);
                i.putExtra("idMedico", getItem(position).getId());
                v.getContext().startActivity(i);

                mContext.finish();
            }
        });


        return row;
    }

}
