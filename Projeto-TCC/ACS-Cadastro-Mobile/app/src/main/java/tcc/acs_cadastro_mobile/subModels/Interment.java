package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

/**
 * Created by anail on 05/06/2017.
 */

public class Interment extends RealmObject implements Serializable {

    private boolean isInterment;
    private String value;

    public boolean isInterment() {
        return isInterment;
    }

    public void setInterment(boolean interment) {
        isInterment = interment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
