package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;

import static tcc.acs_cadastro_mobile.Constants.Citizen.BIRTH_DATE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NAME;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NUM_NIS;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NUM_SUS;
import static tcc.acs_cadastro_mobile.Constants.Citizen.SOCIAL_NAME;

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
        json.put(NUM_SUS.name(), numSus);
        json.put(NUM_NIS.name(), numNis);
        json.put(NAME.name(), name);
        json.put(SOCIAL_NAME.name(), socialName);
        json.put(BIRTH_DATE.name(), birthDate);
        return json;
    }
}
