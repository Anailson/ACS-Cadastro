package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;

public class AnotherInstitution extends RealmObject implements Serializable {

    private boolean isAnotherInstitution;
    private String value;

    public AnotherInstitution() {
        this(false, "");
    }

    public AnotherInstitution(boolean isAnotherInstitution, String value) {
        this.isAnotherInstitution = isAnotherInstitution;
        this.value = value;
    }

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

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.ANOTHER_INSTITUTION.name(), isAnotherInstitution);
        json.put(Constants.Citizen.DESCRIPTION.name(), value);
        return json;
    }
}
