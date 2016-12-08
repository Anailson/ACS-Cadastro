package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.dataHandler.PacienteHandler;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

/**
 * Created by iuri on 6/8/2016.
 */
public class AdapterListaDeMedicosAddPaciente extends ArrayAdapter<Medico> {

    Context mContext;
    Date date;
    Realm realm;

    public AdapterListaDeMedicosAddPaciente(Context view,Realm realm, List<Medico> medicos, Date date) {
        super(view, R.layout.medico_layout, medicos);
        this.mContext = view;
        this.date = date;
        this.realm = realm;
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
                listarPacientes(v.getContext(), m, date);
            }
        });

        return row;
    }

    //TODO Simlicar Dialog
    public void listarPacientes(final Context context, final Medico medico, final Date data) {// final AdapterListaLeis adapter

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(data);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.paciente_agendamento_list)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));


        Consulta consulta = realm.where(Consulta.class)
                .equalTo("dataDoAtendimentoEmMilissegundo", data.getTime())
                .equalTo("medico.id", medico.getId())
                .findFirst();

        Log.e("Log", "dataDoAtendimentoEmMilissegundo" + data.getTime() + " medico.id  " + medico.getId());

        ListView mListView = (ListView) dialog.findViewById(R.id.paciente_listview_add);


        ((TextView) dialog.findViewById(R.id.paciente_list_medico_nome)).setText(medico.getNome());
        ((View) dialog.findViewById(R.id.paciente_list_medico_cor)).setBackgroundColor(medico.getCorIndicativa());

        TextView txt = (TextView) dialog.findViewById(R.id.paciente_emptyText);
        txt.setText(R.string.nenhum_paciente_cadastrado);
        mListView.setEmptyView(txt);

        List<Paciente> pacientes = consulta.getPacientes();

        final AdapterAdicionarPacienteAgendamento arrayAdapter = new AdapterAdicionarPacienteAgendamento(context, R.id.paciente_listview_add, pacientes);
        mListView.setAdapter(arrayAdapter);




        //Button cancelar
        Button btnOk = (Button) dialog.findViewById(R.id.paciente_list_btn_ok);
        assert btnOk != null;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //android.support.v4.app.FragmentManager fragmentManager = fragmentManager;
                //  fragmentManager.beginTransaction().replace(R.id.content_frame, new Agenda()).commit();

            }
        });

        //Button para adicionar um medico para o dia
        Button btnAdicionarPaciente = (Button) dialog.findViewById(R.id.paciente_list_btn_adicionar);

        btnAdicionarPaciente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                adicionarEditarPaciente(v.getContext(), arrayAdapter, medico, data, -1);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Paciente p = (Paciente) parent.getItemAtPosition(position);
                adicionarEditarPaciente(view.getContext(), arrayAdapter, medico, data, p.getId());

            }
        });

    }
//TODO Simlicar Dialog Repetido
    /**
     * @param idPaciente = -1 novo, >-1 existente
     */
    public void adicionarEditarPaciente(final Context context, final android.widget.ArrayAdapter adapter,
                                               final Medico medico, final Date data, final long idPaciente) {


        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Adicionar paciente")
                .setView(R.layout.paciente_add_editar_layout)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));


        ImageButton btnExcluir = (ImageButton) dialog.findViewById(R.id.paciente_excluir);
        final EditText editNome = (EditText) dialog.findViewById(R.id.paciente_add_nome);
        final EditText editTelefone = (EditText) dialog.findViewById(R.id.paciente_add_telefone);
        final EditText editTelefone2 = (EditText) dialog.findViewById(R.id.paciente_add_telefone2);

        final CheckBox chkPago = (CheckBox) dialog.findViewById(R.id.paciente_add_chk_pago);
        if (idPaciente == -1) {

            dialog.setTitle("Adicionar paciente");
            btnExcluir.setVisibility(View.GONE);

        } else {

            dialog.setTitle("Editar/Excluir paciente");
            btnExcluir.setVisibility(View.VISIBLE);


            Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();


            editNome.setText(paciente.getNome());
            String[] telefones = paciente.getTelefone().split("/");
            try {
                editTelefone.setText(telefones[0]);
                editTelefone2.setText(telefones[1]);
            }catch (Exception e){}//tratar excessão
            chkPago.setChecked(paciente.isPago());

        }

        Button btnSalvar = (Button) dialog.findViewById(R.id.paciente_add_btn_salvar);
        Button btnCancelar = (Button) dialog.findViewById(R.id.paciente_add_btn_cancelar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (idPaciente == -1) {


                    Consulta consulta = realm.where(Consulta.class)
                            .equalTo("dataDoAtendimentoEmMilissegundo", data.getTime())
                            .equalTo("medico.id", medico.getId())
                            .findFirst();

                    Paciente paciente = new Paciente(editNome.getText().toString(), editTelefone.getText() + "/" + editTelefone2.getText(), 0, consulta.getId(), chkPago.isChecked());

                    new PacienteHandler().newPaciente(paciente, realm);

                    realm.beginTransaction();
                    consulta.getPacientes().add(paciente);
                    realm.commitTransaction();


                } else {

                    Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();
                    realm.beginTransaction();
                    paciente.setNome(editNome.getText().toString());
                    paciente.setTelefone(editTelefone.getText() + "/" + editTelefone2.getText());
                    paciente.setPago(chkPago.isChecked());
                    realm.commitTransaction();

                    new PacienteHandler().editPaciente(paciente, realm);

                }

                adapter.notifyDataSetChanged();


                dialog.dismiss();

            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();

                realm.beginTransaction();
                paciente.deleteFromRealm();
                realm.commitTransaction();


                adapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}
