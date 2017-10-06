package tcc.acs_cadastro_mobile.models;


import java.io.Serializable;

import io.realm.RealmObject;

public class RealmInt extends RealmObject implements Serializable{
    private String code;

    public RealmInt() {}

    public RealmInt(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
