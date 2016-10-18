package com.avisosms.iuri.avisasms.suporte;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.activity.Principal;
import com.avisosms.iuri.avisasms.adapters.AdapterAdicionarPaciente;
import com.avisosms.iuri.avisasms.adapters.AdapterSelecionarMedico;
import com.avisosms.iuri.avisasms.compactcalendarview.CompactCalendarView;
import com.avisosms.iuri.avisasms.dataHandler.PacienteHandler;
import com.avisosms.iuri.avisasms.fragments.Agenda;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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



        ListView mListView = (ListView) dialog.findViewById(R.id.paciente_listview_add);


        ((TextView) dialog.findViewById(R.id.paciente_list_medico_nome)).setText(medico.getNome());
        ((View) dialog.findViewById(R.id.paciente_list_medico_cor)).setBackgroundColor(medico.getCorIndicativa());

        TextView txt = (TextView) dialog.findViewById(R.id.paciente_emptyText);
        txt.setText(R.string.nenhum_paciente_cadastrado);
        mListView.setEmptyView(txt);

        final AdapterAdicionarPaciente arrayAdapter = new AdapterAdicionarPaciente(context, R.id.paciente_listview_add, consulta.getPacientes());
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
                adicionarEditarPaciente(v.getContext(), arrayAdapter, medico, data );

            }
        });

    }

    public static void adicionarEditarPaciente(final Context context, final ArrayAdapter adapter, final Medico medico, final Date data){


        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Adicionar paciente")
                .setView(R.layout.paciente_add_editar_layout)
                .create();

        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        //dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon_paciente);
        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));

        //final Dialog dialog = new Dialog(context);

        //dialog.setContentView(R.layout.paciente_add_editar_layout);//carregando o layout do dialog do xml

        //dialog.setTitle("Adicionar paciente");

        final EditText nome = (EditText) dialog.findViewById(R.id.paciente_add_nome);
        final EditText telefone = (EditText) dialog.findViewById(R.id.paciente_add_telefone);
        final EditText telefone2 = (EditText) dialog.findViewById(R.id.paciente_add_telefone2);

        final CheckBox chkPago = (CheckBox) dialog.findViewById(R.id.paciente_add_chk_pago);

        Button btn_ok = (Button) dialog.findViewById(R.id.paciente_add_btn_salvar);
        Button btn_cancelar = (Button) dialog.findViewById(R.id.paciente_add_btn_cancelar);




        btn_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();

                Consulta consulta = realm.where(Consulta.class)
                        .equalTo("dataDoAtendimentoEmMilissegundo", data.getTime())
                        .equalTo("medico.id", medico.getId())
                        .findFirst();

                Paciente paciente = new Paciente(nome.getText().toString(), telefone.getText() + "/" + telefone2.getText(), consulta.getPacientes().size()+1, consulta.getId(), chkPago.isChecked() );

                new PacienteHandler().newPaciente(paciente, realm);

                realm.beginTransaction();
                consulta.getPacientes().add(paciente);
                realm.commitTransaction();

                realm.close();

                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

}
