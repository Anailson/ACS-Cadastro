package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class ParticularData extends RealmObject implements Serializable {
    private long numSus, numNis;
    private String name, socialName, birthDate;

    public ParticularData() {
        this(0, 0, "", "", "");
    }

    public ParticularData(long numSus, long numNis, String name, String socialName, String birthDate) {
        this.numSus = numSus;
        this.numNis = numNis;
        this.name = name;
        this.socialName = socialName;
        this.birthDate = birthDate;
    }

    public long getNumSus() {
        return numSus;
    }

    public void setNumSus(long numSus) {
        this.numSus = numSus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public long getNumNis() {
        return numNis;
    }

    public void setNumNis(long numNis) {
        this.numNis = numNis;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.NUM_SUS.name(), numSus);
        json.put(Constants.Citizen.NUM_NIS.name(), numNis);
        json.put(Constants.Citizen.NAME.name(), name);
        json.put(Constants.Citizen.SOCIAL_NAME.name(), socialName);
        json.put(Constants.Citizen.BIRTH_DATE.name(), birthDate);
        return json;
    }
}
