package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.models.CitizenModel;

import java.io.Serializable;

public class FamilyVisit extends RealmObject implements Serializable {

    private boolean isFamilyVisit;
    private String value;

    public FamilyVisit() {
        this(false, "");
    }

    public FamilyVisit(boolean isFamilyVisit, String value) {
        this.isFamilyVisit = isFamilyVisit;
        this.value = value;
    }

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

    public JSONObject getAsJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.FAMILY_VISIT.name(), isFamilyVisit);
        json.put(Constants.Citizen.DESCRIPTION.name(), value);
        return json;
    }
}
