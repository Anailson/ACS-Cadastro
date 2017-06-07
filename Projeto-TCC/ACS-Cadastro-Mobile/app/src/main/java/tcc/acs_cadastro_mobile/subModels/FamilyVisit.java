package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class FamilyVisit extends RealmObject implements Serializable {

    private boolean isFamilyVisit;
    private String value;

    public boolean isFamilyVisit() {
        return isFamilyVisit;
    }

    public void setFamilyVisit(boolean familyVisit) {
        isFamilyVisit = familyVisit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
