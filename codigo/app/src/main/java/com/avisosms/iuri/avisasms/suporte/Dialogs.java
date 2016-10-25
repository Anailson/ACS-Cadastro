package com.avisosms.iuri.avisasms.suporte;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.activity.Principal;
import com.avisosms.iuri.avisasms.adapters.AdapterAdicionarPacienteAgendamento;
import com.avisosms.iuri.avisasms.adapters.AdapterAdicionarPacienteConsulta;
import com.avisosms.iuri.avisasms.adapters.AdapterSelecionarMedico;
import com.avisosms.iuri.avisasms.compactcalendarview.CompactCalendarView;
import com.avisosms.iuri.avisasms.dataHandler.PacienteHandler;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 7/15/2016.
 */
public class Dialogs {

    public static void confirmarSaida(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        //builder.setTitle("Your Title");

        builder.setMessage("Deseja sair sem salvar?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        activity.finish();

                        Intent i = new Intent(activity, Principal.class);
                        i.putExtra("idNavTab", R.id.nav_atendente);
                        activity.startActivity(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void medicoListSelecionar(final Context context, final CompactCalendarView calendar, Date data) {// final AdapterListaLeis adapter

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(data);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.medico_list_check)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_medico));

        ListView mListView = (ListView) dialog.findViewById(R.id.medico_listview_check);

        TextView txt = (TextView) dialog.findViewById(R.id.medico_emptyText);
        txt.setText(R.string.nenhum_medico_cadastrado);
        mListView.setEmptyView(txt);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Medico> medicos = realm.where(Medico.class).findAll();
        realm.close();

        AdapterSelecionarMedico arrayAdapter = new AdapterSelecionarMedico(context, R.id.medico_listview_check, medicos, data.getTime());
        mListView.setAdapter(arrayAdapter);


        //Button cancelar
        Button btnOk = (Button) dialog.findViewById(R.id.categoria_list_btn_cancelar);
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
        Button btnAdicionarMedico = (Button) dialog.findViewById(R.id.categoria_list_btn_adicionar);

        btnAdicionarMedico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //fragmentManager.beginTransaction().replace(R.id.content_frame, new Agenda()).commit();
            }
        });

    }

    public static void listarPacientes(final Context context, final Medico medico, final Date data) {// final AdapterListaLeis adapter

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(data);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.paciente_agendamento_list)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));

        Realm realm = Realm.getDefaultInstance();
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

        final AdapterAdicionarPacienteAgendamento arrayAdapter = new AdapterAdicionarPacienteAgendamento(context, R.id.paciente_listview_add,  consulta.getPacientes());
        mListView.setAdapter(arrayAdapter);

        realm.close();

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

                adicionarEditarPaciente(v.getContext(), arrayAdapter, medico, data,-1);
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

    /**
     * @param idPaciente = -1 novo, >-1 existente
     */
    public static void adicionarEditarPaciente(final Context context, final ArrayAdapter adapter,
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

            Realm realm = Realm.getDefaultInstance();
            Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();
            realm.close();

            editNome.setText(paciente.getNome());
            String[] telefones = paciente.getTelefone().split("/");
            editTelefone.setText(telefones[0]);
            editTelefone2.setText(telefones[1]);
            chkPago.setChecked(paciente.isPago());

        }

        Button btnSalvar = (Button) dialog.findViewById(R.id.paciente_add_btn_salvar);
        Button btnCancelar = (Button) dialog.findViewById(R.id.paciente_add_btn_cancelar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();
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

                realm.close();

                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();

                Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();

                realm.beginTransaction();
                paciente.deleteFromRealm();
                realm.commitTransaction();

                realm.close();

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

    public static void listarPacientesAddConsulta(final Context context, final Consulta consulta) {// final AdapterListaLeis adapter

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(consulta.getDataDoAtendimentoEmMilissegundo());

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.paciente_consulta_list)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));

        final Medico medico = consulta.getMedico();

        ListView mListView = (ListView) dialog.findViewById(R.id.paciente_listview_add);

        ((TextView) dialog.findViewById(R.id.paciente_consulta_list_medico_nome)).setText(medico.getNome());
        ((View) dialog.findViewById(R.id.paciente_consulta_list_medico_cor)).setBackgroundColor(medico.getCorIndicativa());

        TextView txt = (TextView) dialog.findViewById(R.id.paciente_emptyText);
        txt.setText(R.string.nenhum_paciente_cadastrado);
        mListView.setEmptyView(txt);

        //Obtem todos os pacientes que ainda não possuem um ordem definida, ou seja, ainda não chegaram
        List<Paciente> pacientes =  consulta.getPacientes().where().lessThanOrEqualTo("ordem", 0).findAll();

        final AdapterAdicionarPacienteConsulta arrayAdapter = new AdapterAdicionarPacienteConsulta(context, R.id.paciente_listview_add, pacientes );
        mListView.setAdapter(arrayAdapter);


        //Button cancelar
        Button btnOk = (Button) dialog.findViewById(R.id.paciente_consulta_list_btn_ok);
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
        Button btnAdicionarPaciente = (Button) dialog.findViewById(R.id.paciente_consulta_list_btn_adicionar);

        btnAdicionarPaciente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Date data =  new Date();
                data.setTime(consulta.getDataDoAtendimentoEmMilissegundo());

                adicionarEditarPaciente(v.getContext(), arrayAdapter, medico, data,-1);

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Date data =  new Date();
                data.setTime(consulta.getDataDoAtendimentoEmMilissegundo());

                Paciente p = (Paciente) parent.getItemAtPosition(position);
                adicionarEditarPaciente(view.getContext(), arrayAdapter, medico, data, p.getId());

            }
        });

    }

}
