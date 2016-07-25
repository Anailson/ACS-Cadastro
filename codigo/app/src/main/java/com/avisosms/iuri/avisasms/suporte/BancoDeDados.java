package com.avisosms.iuri.avisasms.suporte;

import android.content.Context;

import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by iuri on 6/7/2016.
 */
public class BancoDeDados {

    public static void ConfigurarBanco(Context context) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
    }


    public static void AdicionarTesteDados() {

        Realm realm = Realm.getDefaultInstance();

        //remover realm objects
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();

        //Pacientes
        for (int i = 0; i < 600; i++) {

            realm.beginTransaction();


            int nextkey = 1;

            if (realm.where(Paciente.class).max("id") != null)
                nextkey = realm.where(Paciente.class).max("id").intValue() + 1;

            Paciente paciente = new Paciente("Paciente ordem> " + i + " key> " + nextkey, " Telefone " + i, i);
            if(i>10 && i<60)
                paciente.setAtendido(true);

            paciente.setId(nextkey);

            realm.copyToRealm(paciente);

            realm.commitTransaction();

        }

        ///Adicionar Médicos e o agendamento dos pacientess
        for (int i = 0; i < 5; i++) {
            realm.beginTransaction();


            int nextkey = 1;


            if (realm.where(Medico.class).max("id") != null)
                nextkey = realm.where(Medico.class).max("id").intValue() + 1;

            Medico medico = new Medico("Médico " + nextkey  , "Cirurgião", "Telefone " + i);

            medico.setId(nextkey);
            realm.copyToRealm(medico);

            RealmResults<Paciente> pacientes = realm.where(Paciente.class).between("id", i*20, (i*20) + 20).findAll();

            Calendar calendar = Funcoes.dataHoje();

            //Adicionar Consulta
            Consulta consulta = new Consulta(calendar.getTimeInMillis(), medico);

            if (realm.where(Consulta.class).max("id") != null)
                nextkey = realm.where(Consulta.class).max("id").intValue() + 1;

            consulta.setId(nextkey);
            RealmList<Paciente> list = new RealmList();
            list.addAll(pacientes);
            consulta.setPacientes(list);

            realm.copyToRealm(consulta);

            realm.commitTransaction();
        }

        realm.close();
    }

}
