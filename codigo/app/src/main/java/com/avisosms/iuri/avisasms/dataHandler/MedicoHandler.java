package com.avisosms.iuri.avisasms.dataHandler;

import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MedicoHandler {

    public static Medico add(Medico medico, Realm realm) {


        int nextkey = 1;
        if (realm.where(Medico.class).max("id") != null)
            nextkey = realm.where(Medico.class).max("id").intValue() + 1;

        medico.setId(nextkey);
        medico.setNome(medico.getNome() + " " + nextkey);
        realm.beginTransaction();
        realm.copyToRealm(medico);
        realm.commitTransaction();

        return medico;
    }

    public static Medico update(Realm realm, Medico medico) {

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(medico);
        realm.commitTransaction();

        return medico;
    }

    public RealmResults<Medico> getAll(Realm realm) {
        RealmResults<Medico> res = realm.where(Medico.class).findAll();
        return res;
    }

    public Medico getById(int id) {
        Realm realm = Realm.getDefaultInstance();
        Medico res = realm.where(Medico.class).equalTo("id", id).findFirst();
        realm.close();
        return res;
    }

    public Medico getByNome(String nome) {
        Realm realm = Realm.getDefaultInstance();
        Medico res = realm.where(Medico.class).equalTo("nome", nome).findFirst();
        realm.close();
        return res;
    }

    public void removeMedico(int id) {
        Realm realm = Realm.getDefaultInstance();
        Medico medico = getById(id);
        realm.beginTransaction();
        medico.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    //Metodo necessário para passar os objetos através das threads
    public static Medico copy(Paciente cliente) {
        Medico novo = new Medico();
        novo.setId(cliente.getId());
        novo.setNome(cliente.getNome());
        novo.setTelefone(cliente.getTelefone());

        return novo;
    }
}
