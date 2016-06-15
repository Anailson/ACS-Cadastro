package com.avisosms.iuri.avisasms.objetos;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by iuri on 6/15/2016.
 */
public class Agendamento extends RealmObject{

    private long id;
    private long dataDoAtendimentoEmMilissegundo;
    private Medico medico;
    private RealmList<Paciente> pacientes;

    public Agendamento(){}

}
