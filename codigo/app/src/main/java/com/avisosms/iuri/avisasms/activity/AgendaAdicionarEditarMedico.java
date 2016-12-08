package com.avisosms.iuri.avisasms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.adapters.AdapterSelecionarMedico;
import com.avisosms.iuri.avisasms.objetos.Medico;

import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 07/12/2016.
 */

public class AgendaAdicionarEditarMedico extends Activity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medico_list_check);
        setFinishOnTouchOutside(false);

        realm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        long dataSelecionada = intent.getLongExtra("dataSelecionada", 0);
        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(dataSelecionada);


        //setIcon(ContextCompat.getDrawable(context, R.drawable.icon_medico));

        ListView mListView = (ListView) findViewById(R.id.medico_listview_check);

        TextView txt = (TextView) findViewById(R.id.medico_emptyText);
        txt.setText(R.string.nenhum_medico_cadastrado);
        mListView.setEmptyView(txt);


        RealmResults<Medico> medicos = realm.where(Medico.class).findAll();


        AdapterSelecionarMedico arrayAdapter = new AdapterSelecionarMedico(this, R.id.medico_listview_check, medicos, dataSelecionada, null);
        mListView.setAdapter(arrayAdapter);

        //Button cancelar
        Button btnOk = (Button) findViewById(R.id.categoria_list_btn_cancelar);
        assert btnOk != null;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               /* FragmentActivity f = new FragmentActivity();
                f.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Agenda()).commit();*/

                //android.support.v4.app.FragmentManager fragmentManager = fragmentManager;
                //  fragmentManager.beginTransaction().replace(R.id.content_frame, new Agenda()).commit();

            }
        });

        //Button para adicionar um medico para o dia
        Button btnAdicionarMedico = (Button) findViewById(R.id.categoria_list_btn_adicionar);

        btnAdicionarMedico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //fragmentManager.beginTransaction().replace(R.id.content_frame, new Agenda()).commit();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
