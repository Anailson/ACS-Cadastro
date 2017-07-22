package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import static tcc.acs_cadastro_mobile.Constants.Citizen.BIRTH_DATE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NUM_SUS;
import static tcc.acs_cadastro_mobile.Constants.Citizen.RESPONSIBLE;

public class Responsible extends RealmObject  implements Serializable {
    private boolean isResponsible;
    private long numSus;
    private String birthDate;

    public Responsible() {
        this(false, 0, "");
    }

    public Responsible(boolean isResponsible, long numSus, String birthDate) {
        this.isResponsible = isResponsible;
        this.numSus = numSus;
        this.birthDate = birthDate;
    }

    public boolean isResponsible() {
        return isResponsible;
    }

    public void setResponsible(boolean responsible) {
        isResponsible = responsible;
    }

    public long getNumSus() {
        return numSus;
    }

    public void setNumSus(long numSus) {
        this.numSus = numSus;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(RESPONSIBLE.name(), isResponsible);
        json.put(NUM_SUS.name(), numSus);
        json.put(BIRTH_DATE.name(), birthDate);
        return json;
    }
}
