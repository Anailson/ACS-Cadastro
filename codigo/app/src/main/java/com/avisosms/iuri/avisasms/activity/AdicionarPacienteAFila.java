package com.avisosms.iuri.avisasms.activity;

import android.app.Activity;
import android.os.Bundle;

import com.avisosms.iuri.avisasms.R;

import io.realm.Realm;

/**
 * Created by iuri on 08/12/2016.
 */

public class AdicionarPacienteAFila extends Activity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paciente_consulta_list);
        setFinishOnTouchOutside(false);

        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
