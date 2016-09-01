package com.avisosms.iuri.avisasms.objetos;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by iuri on 6/9/2016.
 */
public class Medico extends RealmObject {

    @PrimaryKey
    private long id;
    @NonNull
    private String nome;
    @Nullable
    private String telefone;
    @Nullable
    private String especialidade;
    @Nullable
    private String email;
    @Nullable
    private int corIndicativa;
    @Nullable
    private String observacao;

    public Medico() {}

    public Medico(String nome, String especialidade, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;

    }

    public Medico(long id, String nome, String especialidade, String telefone, String email, String observacao, int corIndicativa ) {
        this(nome, especialidade, telefone, email, observacao, corIndicativa);
        this.id = id;
    }

    public Medico(String nome, String especialidade, String telefone, String email, String observacao, int corIndicativa ) {
        this.id = -1;
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.email = email;
        this.observacao = observacao;
        this.corIndicativa = corIndicativa;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getCorIndicativa() {
        return corIndicativa;
    }

    public void setCorIndicativa(int corIndicativa) {
        this.corIndicativa = corIndicativa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
