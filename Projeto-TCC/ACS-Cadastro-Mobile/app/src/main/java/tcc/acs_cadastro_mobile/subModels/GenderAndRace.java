package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class GenderAndRace extends RealmObject  implements Serializable {
    private String gender, race;

    public GenderAndRace() {
        this("", "");
    }

    public GenderAndRace(String gender, String race) {
        this.gender = gender;
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.GENDER.name(), gender);
        json.put(Constants.Citizen.RACE.name(), race);
        return json;
    }
}
