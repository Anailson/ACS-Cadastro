package com.avisosms.iuri.avisasms.dataHandler;

import com.avisosms.iuri.avisasms.objetos.Paciente;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class PacienteHandler {

    public static Paciente newPaciente(Paciente paciente) {
        Realm realm = Realm.getDefaultInstance();

        int nextkey = 1;
        if (realm.where(Paciente.class).max("id") != null)
            nextkey = realm.where(Paciente.class).max("id").intValue() + 1;
        paciente.setId(nextkey);
        realm.beginTransaction();
        realm.copyToRealm(paciente);
        realm.commitTransaction();
        realm.close();
        return paciente;
    }

    public static Paciente newPaciente(Paciente paciente, Realm realm) {

        int nextkey = 1;
        if (realm.where(Paciente.class).max("id") != null)
            nextkey = realm.where(Paciente.class).max("id").intValue() + 1;
        paciente.setId(nextkey);

        realm.beginTransaction();
        realm.copyToRealm(paciente);
        realm.commitTransaction();

        return paciente;
    }

    public static Paciente newPaciente(Realm realm, Paciente paciente) {

        int nextkey = 1;
        if (realm.where(Paciente.class).max("id") != null)
            nextkey = realm.where(Paciente.class).max("id").intValue() + 1;
        paciente.setId(nextkey);
        realm.beginTransaction();
        realm.copyToRealm(paciente);
        realm.commitTransaction();

        return paciente;
    }

    public RealmList<Paciente> getAll() {
        Realm realm = Realm.getDefaultInstance();
        RealmList<Paciente> clientes = new RealmList<>();
        RealmResults<Paciente> res = realm.where(Paciente.class).findAll();
        clientes.addAll(res);
        realm.close();
        return clientes;
    }

    public Paciente getById(int id) {
        Realm realm = Realm.getDefaultInstance();
        Paciente res = realm.where(Paciente.class).equalTo("id", id).findFirst();
        realm.close();
        return res;
    }

    public Paciente getByNome(String nome) {
        Realm realm = Realm.getDefaultInstance();
        Paciente res = realm.where(Paciente.class).equalTo("nome", nome).findFirst();
        realm.close();
        return res;
    }

    public void removeCliente(int id) {
        Realm realm = Realm.getDefaultInstance();
        Paciente cliente = getById(id);
        realm.beginTransaction();
        cliente.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    //Metodo necessário para passar os objetos através das threads
    public static Paciente copy(Paciente cliente){
        Paciente novo = new Paciente();
        novo.setId(cliente.getId());
        novo.setNome(cliente.getNome());
        novo.setTelefone(cliente.getTelefone());

        return novo;
    }
}
