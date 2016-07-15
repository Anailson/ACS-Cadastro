package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Medico;

import java.util.List;

/**
 * Created by iuri on 6/8/2016.
 */
public class AdapterListaDeMedicos extends ArrayAdapter<Medico> {

    Context mContext;

    public AdapterListaDeMedicos(Context context, List<Medico> medicos) {
        super(context, R.layout.medico_layout, medicos);
        this.mContext = context;
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
        View view;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Medico m = getItem(position);
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
                    /*if (row == null) {*/ //Checkbox precisa ter uma lista exclusiva, http://webcache.googleusercontent.com/search?q=cache:http://stackoverflow.com/questions/16350670/listview-viewholder-checkbox-state&gws_rd=cr&ei=5sFXV_G6M4f4wASc5LjIDg
            // row = LayoutInflater.from(mContext).inflate(R.layout.dynamiclistview_list_row, parent, false);
            /*LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.dynamiclistview_list_row, null/*, parent, false);*/

            holder = new ViewHolder();
            row = LayoutInflater.from(mContext).inflate(R.layout.medico_listview_item, parent, false);

            holder.textNome = (TextView) row.findViewById(R.id.list_medico_nome);
            holder.textEspecialidade = (TextView) row.findViewById(R.id.list_medico_especialidade);
            holder.textTelefone = (TextView) row.findViewById(R.id.list_medico_telefone);
            holder.view = (View) row.findViewById(R.id.list_medico_cor);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textNome.setText(m.getNome());
        holder.textEspecialidade.setText(m.getEspecialidade());
        holder.textTelefone.setText(m.getTelefone());

        if (position == 4)
            holder.view.setBackgroundColor(row.getResources().getColor(R.color.cor_verde));
        else if (position == 2)
            holder.view.setBackgroundColor(row.getResources().getColor(R.color.cor_vermelho_escuro));
        else if (position == 3)
            holder.view.setBackgroundColor(row.getResources().getColor(R.color.cor_roxo_claro));
        else if (position == 1)
            holder.view.setBackgroundColor(row.getResources().getColor(R.color.cor_amarelo));
        else if (position == 5)
            holder.view.setBackgroundColor(row.getResources().getColor(R.color.cor_azul));

        //set on click

        /*((TextView) view.findViewById(R.id.list_row_draganddrop_textview_titulo)).setText("Paciente:" + getItem(position));
        ((TextView) view.findViewById(R.id.list_row_draganddrop_textview_subtitulo)).setText("Telefone: (79) 9 9670-2237");
*/
        return row;
    }

}
