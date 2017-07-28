package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;


public class HousingSituation extends RealmObject implements Serializable {

    private String situation, location, ownership;

    public HousingSituation() {
        this("", "", "");
    }

    public HousingSituation(String situation, String location, String ownership) {
        this.situation = situation;
        this.location = location;
        this.ownership = ownership;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public JSONObject asJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(Constants.Residence.SITUATION.name(), situation);
            json.put(Constants.Residence.LOCATION.name(), location);
            json.put(Constants.Residence.OWNERSHIP.name(), ownership);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
