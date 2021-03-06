package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class Interment extends RealmObject implements Serializable {

    private boolean isInterment;
    private String value;

    public Interment() {
        this(false, "");
    }

    public Interment(boolean isInterment, String value) {
        this.isInterment = isInterment;
        this.value = value;
    }

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

    public JSONObject getAsJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.INTERMENT.name(), isInterment);
        json.put(Constants.Citizen.DESCRIPTION.name(), value);
        return json;
    }
}
