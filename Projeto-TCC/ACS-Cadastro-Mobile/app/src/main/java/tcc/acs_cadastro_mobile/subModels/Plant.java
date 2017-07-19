package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class Plant extends RealmObject implements Serializable {

    private boolean isPlants;
    private String value;

    public Plant() {
        this(false, "");
    }

    public Plant(boolean isPlants, String value) {
        this.isPlants = isPlants;
        this.value = value;
    }

    public boolean isPlants() {
        return isPlants;
    }

    public void setPlants(boolean plants) {
        isPlants = plants;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JSONObject getAsJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.PLANT.name(), isPlants);
        json.put(Constants.Citizen.DESCRIPTION.name(), value);
        return json;
    }
}
