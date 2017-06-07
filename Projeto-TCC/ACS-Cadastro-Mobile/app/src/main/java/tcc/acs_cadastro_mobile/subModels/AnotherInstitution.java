package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class AnotherInstitution extends RealmObject implements Serializable {

    private boolean isAnotherInstitution;
    private String value;

    public boolean isAnotherInstitution() {
        return isAnotherInstitution;
    }

    public void setAnotherInstitution(boolean anotherInstitution) {
        isAnotherInstitution = anotherInstitution;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
