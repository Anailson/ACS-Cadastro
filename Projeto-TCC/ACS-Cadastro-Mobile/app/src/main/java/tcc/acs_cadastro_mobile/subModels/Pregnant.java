package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;

public class Pregnant extends RealmObject implements Serializable {
    private boolean isPregnant;
    private String maternity;

    public Pregnant() {
        this(false, "");
    }

    public Pregnant(boolean isPregnant, String maternity) {
        this.isPregnant = isPregnant;
        this.maternity = maternity;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public String getMaternity() {
        return maternity;
    }

    public void setMaternity(String maternity) {
        this.maternity = maternity;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.PREGNANT.name(), isPregnant);
        json.put(Constants.Citizen.DESCRIPTION.name(), maternity);
        return json;
    }
}
