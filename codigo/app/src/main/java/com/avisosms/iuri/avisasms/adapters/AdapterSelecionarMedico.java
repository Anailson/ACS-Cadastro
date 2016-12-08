package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.dataHandler.ConsultaHandler;
import com.avisosms.iuri.avisasms.fragments.Agenda;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;

import java.util.Date;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by iuri on 03/10/2016.
 */
public class AdapterSelecionarMedico extends RealmBaseAdapter<Medico> implements ListAdapter {

    private Context context;
    private List<Medico> medicos;
    private long timeMillisseconds;
       AdapterListaDeMedicosAddPaciente adapter;
    // private RealmResults<Consulta> consultas;//identificar os médicos da consulta



    public AdapterSelecionarMedico(Context context, int view, OrderedRealmCollection<Medico> objects, long timeMillisseconds, AdapterListaDeMedicosAddPaciente adapter) {
        super(context, objects);
        medicos = objects;
        this.context = context;
        this.timeMillisseconds = timeMillisseconds;
        this.adapter =  adapter;


    }

    /*@Override
    public int getCount() {
        return medicos.size();
    }*/

    ViewHolder holder = null;

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final Medico medico = getItem(position);

        View row = convertView;
       /* if (row == null) {*/
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.medico_listview_item_check, null);
        holder = new ViewHolder();
        holder.corIndicativa = (View) row.findViewById(R.id.list_item_medico_check_cor);
        holder.textNome = (TextView) row.findViewById(R.id.list_item_medico_check_nome);
        holder.textEspecialidade = (TextView) row.findViewById(R.id.list_item_medico_check_especialidade);
        holder.textTelefone = (TextView) row.findViewById(R.id.list_item_medico_telefone);
        holder.selecionarMedico = (Button) row.findViewById(R.id.selecionar_medico_btn_add);


         /*   row.setTag(holder);*/
        /*} else {
            holder = (ViewHolder) convertView.getTag();
        }*/

        holder.textNome.setText(medico.getNome());
        holder.textTelefone.setText(medico.getTelefone());
        holder.textEspecialidade.setText(medico.getEspecialidade());


        //Marcar os medicos já selecionados para o dia

        holder.corIndicativa.setBackgroundColor(medico.getCorIndicativa());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Consulta> consultas = realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", timeMillisseconds).findAll();

        for (Consulta consulta : consultas) {

            if (consulta.getMedico().getId() == medico.getId()) {
                holder.selecionarMedico.setBackgroundColor(ContextCompat.getColor(context, R.color.cor_vermelho));
                holder.selecionarMedico.setText(R.string.remover_medico);
                break;
            }
        }
      /*  if (consultas.contains(medico)) {

        } else {
            holder.selecionarMedico.setBackgroundColor(ContextCompat.getColor(context, R.color.cor_azul));
            holder.selecionarMedico.setText(R.string.adicionar_medico_ln);
        }*/

        realm.close();

        holder.selecionarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                RealmResults<Consulta> consultas = realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", timeMillisseconds).findAll();

                boolean existe = false;
                for (Consulta consulta : consultas) {
                    if (consulta.getMedico().getId() == medico.getId()) {
                        existe = true;

                        realm.beginTransaction();
                        consulta.deleteFromRealm();
                        realm.commitTransaction();

                        v.setBackgroundColor(ContextCompat.getColor(context, R.color.cor_azul));
                        ((Button) v).setText(R.string.adicionar_medico_ln);
                        Toast.makeText(v.getContext(), "Medico removido", Toast.LENGTH_SHORT).show();
                        break;

                    }

                }

                /*Medico medico = getItem(position);*/
                if (!existe) {

                    ConsultaHandler.newConsulta(medico, timeMillisseconds, realm);
                    v.setBackgroundColor(ContextCompat.getColor(context, R.color.cor_vermelho));
                    ((Button) v).setText(R.string.remover_medico);
                }

               /* adapter.notifyDataSetInvalidated();
                adapter.notifyDataSetInvalidated();*/

                notifyDataSetChanged();

                Agenda.atualizarListaMedicoDoDia(context, realm, new Date(timeMillisseconds));

                realm.close();





            }
        });

        holder.textNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        holder.textEspecialidade.setOnClickListener(new View.OnClickListener() {
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
        TextView textEspecialidade;
        TextView textTelefone;
        View corIndicativa;
        Button selecionarMedico;


    }
}



