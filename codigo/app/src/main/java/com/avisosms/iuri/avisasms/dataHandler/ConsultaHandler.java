package com.avisosms.iuri.avisasms.dataHandler;

import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ConsultaHandler {
    public static Medico newMedico(Medico medico) {

        Realm realm = Realm.getDefaultInstance();

        int nextkey = 1;
        if (realm.where(Paciente.class).max("id") != null)
            nextkey = realm.where(Paciente.class).max("id").intValue() + 1;

        medico.setId(nextkey);
        realm.beginTransaction();
        realm.copyToRealm(medico);
        realm.commitTransaction();
        realm.close();

        return medico;
    }

    public RealmList<Medico> getAll() {
        Realm realm = Realm.getDefaultInstance();
        RealmList<Medico> medicos = new RealmList<>();
        RealmResults<Medico> res = realm.where(Medico.class).findAll();
        medicos.addAll(res);
        realm.close();
        return medicos;
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
    public static Medico copy(Paciente cliente){
        Medico novo = new Medico();
        novo.setId(cliente.getId());
        novo.setNome(cliente.getNome());
        novo.setTelefone(cliente.getTelefone());

        return novo;
    }
}
