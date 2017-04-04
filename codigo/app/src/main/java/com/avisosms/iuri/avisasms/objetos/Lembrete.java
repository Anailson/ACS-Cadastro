package com.avisosms.iuri.avisasms.objetos;

import io.realm.RealmObject;

/**
 * Created by iuri on 6/18/2016.
 */
public class Lembrete extends RealmObject {

    private long id;
    private String descricao;
    private String subDescricao;
    private long dataDe;
    private long dataAte;
    private boolean diaTodo;
    private boolean repetir;
    private String dia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSubDescricao() {
        return subDescricao;
    }

    public void setSubDescricao(String subDescricao) {
        this.subDescricao = subDescricao;
    }

    public long getDataDe() {
        return dataDe;
    }

    public void setDataDe(long dataDe) {
        this.dataDe = dataDe;
    }

    public long getDataAte() {
        return dataAte;
    }

    public void setDataAte(long dataAte) {
        this.dataAte = dataAte;
    }

    public boolean isDiaTodo() {
        return diaTodo;
    }

    public void setDiaTodo(boolean diaTodo) {
        this.diaTodo = diaTodo;
    }

    public boolean isRepetir() {
        return repetir;
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
