package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;


public class SexualOrientation extends RealmObject implements Serializable {

    private boolean isSexualOrientation;
    private String value;

    public SexualOrientation() {
        this(false, "");
    }

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

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.SEXUAL_ORIENTATION.name(), isSexualOrientation);
        json.put(Constants.Citizen.DESCRIPTION.name(), value);
        return json;
    }
}
