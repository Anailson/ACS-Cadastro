package com.avisosms.iuri.avisasms.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;

import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 03/10/2016.
 */
public class AdapterSelecionarMedico extends ArrayAdapter<Medico> {

    private Context context;
    private List<Medico> medicos;
    private long timeMillisseconds;

    public AdapterSelecionarMedico(Context context, int view, List<Medico> objects, long timeMillisseconds) {
        super(context, view, objects);
        medicos = objects;
        this.context = context;
        this.timeMillisseconds = timeMillisseconds;
    }

    @Override
    public int getCount() {
        return medicos.size();
    }

    ViewHolder holder = null;

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final Medico medico = getItem(position);

        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.medico_listview_item_check, null);
            holder = new ViewHolder();
            holder.corIndicativa = (View) row.findViewById(R.id.list_item_medico_check_cor);
            holder.textNome = (TextView) row.findViewById(R.id.list_item_medico_check_nome);
            holder.textEspecialidae = (TextView) row.findViewById(R.id.list_item_medico_check_especialidade);
            holder.categoriaCheckBox = (CheckBox) row.findViewById(R.id.chk_categoria);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textNome.setText(medico.getNome());

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Consulta> consultas = realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", timeMillisseconds).findAll();

        //Marcar os medicos já selecionados para o dia

        holder.corIndicativa.setBackgroundColor(medico.getCorIndicativa());

        if (consultas.contains(medico)) {
            holder.categoriaCheckBox.setChecked(true);
        } else {
            holder.categoriaCheckBox.setChecked(false);
        }

        holder.textNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        holder.textEspecialidae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        holder.categoriaCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return row;
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    static class ViewHolder {
        TextView textNome;
        TextView textEspecialidae;
        View corIndicativa;
        CheckBox categoriaCheckBox;


    }
}



