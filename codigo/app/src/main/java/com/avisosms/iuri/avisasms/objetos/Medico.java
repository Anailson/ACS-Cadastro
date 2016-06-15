package com.avisosms.iuri.avisasms.objetos;

import io.realm.RealmObject;

/**
 * Created by iuri on 6/9/2016.
 */
public class Medico extends RealmObject {

    private long id;
    private String nome;
    private String telefone;
    private String especialidade;
    private int corIndicativa;
    private String observacao;

    public Medico() {}

    public Medico(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;

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
}
