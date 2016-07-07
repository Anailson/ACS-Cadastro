package com.avisosms.iuri.avisasms.objetos;

import io.realm.RealmObject;

/**
 * Created by iuri on 6/5/2016.
 */
public class Paciente extends RealmObject {

    private long id;
    private String nome;
    private String telefone;//se

    private boolean atendido;
    private int ordem;

    public Paciente() {
    }

    public Paciente(String nome, String telefone, int ordem) {

        this.nome = nome;
        this.telefone = telefone;
        this.ordem = ordem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
}
