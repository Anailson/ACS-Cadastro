package com.avisosms.iuri.avisasms.suporte;

import android.content.Context;
import android.content.Intent;

import com.avisosms.iuri.avisasms.dataHandler.MedicoHandler;
import com.avisosms.iuri.avisasms.dataHandler.PacienteHandler;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by iuri on 6/7/2016.
 */
public class BancoDeDados {

    public static void ConfigurarBanco(Context context) {

        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm.deleteRealm(config);

    }


    public static void AdicionarTesteDados() {


        Realm realm = Realm.getDefaultInstance();

        //remover realm objects
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();

        //Pacientes
        /*for (int i = 0; i < 600; i++) {

           Paciente paciente = new Paciente("Paciente ordem> " + i + " key> ", " Telefone " + i, i);

            if(i > 10 && i < 60)
                paciente.setAtendido(true);

            new PacienteHandler().newPaciente(paciente);

        }*/

        ///Adicionar Médicos e o agendamento dos pacientess
        for (int i = 0; i < 5; i++) {


            Medico medico = new Medico("Médico ", "Cirurgião", "Telefone " + i);
            if (i == 1)
                medico.setCorIndicativa(-16307805);
            else if (i == 2)
                medico.setCorIndicativa(-16307806);
            else if (i == 3)
                medico.setCorIndicativa(-13730510);
            else if (i == 3)
                medico.setCorIndicativa(-3536986);
            else if (i == 4)
                medico.setCorIndicativa(-67840);
            else
                medico.setCorIndicativa(-6543440);

            new MedicoHandler().add(medico, realm);

            Calendar calendar = Funcoes.dataHoje();
            if (i != 4)
                calendar.set(Calendar.DAY_OF_MONTH, i + 1);
            //Adicionar Consulta
            Consulta consulta = new Consulta();

            int nextkey = 1;
            if (realm.where(Consulta.class).max("id") != null)
                nextkey = realm.where(Consulta.class).max("id").intValue() + 1;

            consulta.setDataDoAtendimentoEmMilissegundo(calendar.getTimeInMillis());
            Medico medico1 = realm.where(Medico.class).equalTo("id", (i + 1)).findFirst();
            consulta.setMedico(medico1);
            consulta.setId(nextkey);
            RealmList<Paciente> list = new RealmList();

            //Pacientes
            for (int y = 0; y < 10; y++) {

                Paciente paciente = listPacientes.get(y);
                paciente.setNome(paciente.getNome());

                paciente = new Paciente(paciente, 0, consulta.getId());

                new PacienteHandler().newPaciente(paciente);

            }

            RealmResults<Paciente> pacientes = realm.where(Paciente.class).between("id", i * 4, (i * 4) + 4).findAll();
            list.addAll(pacientes);

            consulta.setPacientes(list);

            realm.beginTransaction();

            realm.copyToRealm(consulta);

            realm.commitTransaction();

        }

        realm.close();

    }

/*
    static List<Paciente> listPacientes = new ArrayList<Paciente>() {{
        add(new Paciente("Alfedro Santos da Costa", "(79) 99985-7423/"));
        add(new Paciente("Fernando Dias da Conceição", "(79) 98856-7458/"));
        add(new Paciente("Maria Silva", "(79) 99814-4562/997415-3625"));
        add(new Paciente("Ana Tereza de A. Vasques", "(79) 99814-4562/98145-5573"));
        add(new Paciente("Glaucia Adriana Dantas Pereira", "(79) 9958-4587/"));
        add(new Paciente("Genildson Alvez de Oliveira", "(79) 988574-5624/95874-1236"));
        add(new Paciente("Míriam Síria R. de Souza", "(79) 98145-5573/9958-4587"));
        add(new Paciente("José Lucas Ferreira e Silva", "(79) 98547-5824/"));
        add(new Paciente("Ramon Douglas Neves de Andrade", "(79) 97418-5287/"));
        add(new Paciente("Lorena Alcântara de Farias", "(79) 96523-7415/"));
        add(new Paciente("Kalyne Ribeiro Dantas Q. de Vasconcelos", "(79) 96354-2514/"));
        add(new Paciente("Glenda Eloíse de P. Feitosa", "(79) 95874-1236/"));
        add(new Paciente("Mirna Souza da Silva", "(79) 98745-6341/"));
        add(new Paciente("Nathália Alcântara de Farias ", "(79) 9885241-6685/"));
        add(new Paciente("Ewertom Mascena de Araújo", "(79) 997415-3625/"));
        add(new Paciente("Jhony Kleyton do Nascimento", "(79) 99958-1102/"));


    }};*/

    static List<Paciente> listPacientes = new ArrayList<Paciente>() {{
        add(new Paciente("Alfedro Santos da Costa", "996702363/"));
        add(new Paciente("Fernando Dias da Conceição", "996702363/"));
        add(new Paciente("Maria Silva", "996702363/"));
        add(new Paciente("Ana Tereza de A. Vasques", "996702363/"));
        add(new Paciente("Glaucia Adriana Dantas Pereira", "996702363/"));
        add(new Paciente("Genildson Alvez de Oliveira", "996702363/"));
        add(new Paciente("Míriam Síria R. de Souza", "996702363/"));
        add(new Paciente("José Lucas Ferreira e Silva", "996702363/"));
        add(new Paciente("Ramon Douglas Neves de Andrade", "996702237/"));
        add(new Paciente("Lorena Alcântara de Farias", "996702363/"));
        add(new Paciente("Kalyne Ribeiro Dantas Q. de Vasconcelos", "996702363/"));
        add(new Paciente("Glenda Eloíse de P. Feitosa", "996702363/"));
        add(new Paciente("Mirna Souza da Silva", "996702363/"));
        add(new Paciente("Nathália Alcântara de Farias ", "996702363/"));
        add(new Paciente("Ewertom Mascena de Araújo", "996702363/"));
        add(new Paciente("Jhony Kleyton do Nascimento", "996702363/"));
    }};

}
