package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.models.CitizenModel;

import java.io.Serializable;

import static tcc.acs_cadastro_mobile.Constants.Citizen.DESCRIPTION;
import static tcc.acs_cadastro_mobile.Constants.Citizen.STREET;


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

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(STREET.name(), isStreetSituation);
        json.put(DESCRIPTION.name(), value);
        return json;
    }
}
