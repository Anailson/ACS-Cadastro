package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;


public class StreetSituation extends RealmObject implements Serializable {

    private boolean isStreetSituation;
    private String value;

    public StreetSituation() {
        this(false, "");
    }

    public StreetSituation(boolean isStreetSituation, String value) {
        this.isStreetSituation = isStreetSituation;
        this.value = value;
    }

    public boolean isStreetSituation() {
        return isStreetSituation;
    }

    public void setStreetSituation(boolean streetSituation) {
        isStreetSituation = streetSituation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
