package com.avisosms.iuri.avisasms.objetos;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by iuri on 6/15/2016.
 */
public class Consulta extends RealmObject{

    @PrimaryKey
    private long id;
    @NonNull
    private long dataDoAtendimentoEmMilissegundo;
    @NonNull
    private Medico medico;
    @Nullable
    private RealmList<Paciente> pacientes;

    public Consulta(long dataDoAtendimentoEmMilissegundo, Medico medico){

        this.setDataDoAtendimentoEmMilissegundo(dataDoAtendimentoEmMilissegundo);
        this.setMedico(medico);

    }

    public Consulta(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDataDoAtendimentoEmMilissegundo() {
        return dataDoAtendimentoEmMilissegundo;
    }

    public void setDataDoAtendimentoEmMilissegundo(long dataDoAtendimentoEmMilissegundo) {
        this.dataDoAtendimentoEmMilissegundo = dataDoAtendimentoEmMilissegundo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public RealmList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(RealmList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
