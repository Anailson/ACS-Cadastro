package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;


public class SexualOrientation extends RealmObject implements Serializable {

    private boolean isSexualOrientation;
    private String value;

    public SexualOrientation() {}

    public SexualOrientation(boolean isSexualOrientation, String value) {
        this.isSexualOrientation = isSexualOrientation;
        this.value = value;
    }

    public boolean isSexualOrientation() {
        return isSexualOrientation;
    }

    public void setSexualOrientation(boolean sexualOrientation) {
        isSexualOrientation = sexualOrientation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
