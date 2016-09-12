package com.avisosms.iuri.avisasms.objetos;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by iuri on 6/5/2016.
 */
public class Paciente extends RealmObject {

    @PrimaryKey
    private long id;
    @NonNull
    private String nome;
    @Nullable
    private String telefone;
    @Nullable
    private boolean atendido;
    private int ordem;
    @Nullable
    private boolean pago;

    private long idConsulta;

    public Paciente() {
    }

    public Paciente(String nome, String telefone, int ordem, long idConsulta) {

        this.nome = nome;
        this.telefone = telefone;
        this.ordem = ordem;
        this.idConsulta = idConsulta;
    }

    public Paciente(String nome, String telefone) {

        this.nome = nome;
        this.telefone = telefone;

    }

    public Paciente(Paciente p, int ordem, long idConsulta) {

        this.nome = p.nome;
        this.telefone = p.telefone;
        this.ordem = ordem;
        this.idConsulta = idConsulta;
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

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta = idConsulta;
    }
}
