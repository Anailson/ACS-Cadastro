package tcc.acs_cadastro_mobile.models;


import java.io.Serializable;

import io.realm.RealmObject;

public class RealmInt extends RealmObject implements Serializable{
    private int code;

    public RealmInt() {}

    public RealmInt(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
