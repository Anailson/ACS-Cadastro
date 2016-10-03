package com.avisosms.iuri.avisasms.suporte;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.activity.Principal;
import com.avisosms.iuri.avisasms.adapters.AdapterSelecionarMedico;
import com.avisosms.iuri.avisasms.objetos.Medico;

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

    public static void medicoListSelecionar(final Context context, Date data) {// final AdapterListaLeis adapter

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(data);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.medico_list_check)
                .create();

        dialog.show();

        ListView mListView = (ListView) dialog.findViewById(R.id.medico_listview_check);

        TextView txt = (TextView) dialog.findViewById(R.id.medico_emptyText);
        txt.setText(R.string.nenhum_medico_cadastrado);
        mListView.setEmptyView(txt);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Medico> medicos = realm.where(Medico.class).findAll();
        realm.close();

        AdapterSelecionarMedico arrayAdapter = new AdapterSelecionarMedico(context, R.id.medico_listview_check, medicos,  data.getTime());
        mListView.setAdapter(arrayAdapter);

        //Button cancelar
        Button btnOk = (Button) dialog.findViewById(R.id.categoria_list_btn_cancelar);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Button para adicionar um medico para o dia
        Button btnAdicionarCategoria = (Button) dialog.findViewById(R.id.categoria_list_btn_adicionar);

        btnAdicionarCategoria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

     }
}
