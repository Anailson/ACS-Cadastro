package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import static tcc.acs_cadastro_mobile.Constants.Citizen.KNOWN;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NAME;

public class Mother extends RealmObject  implements Serializable {
    private boolean known;
    private String name;

    public Mother() {
        this(false, "");
    }

    public Mother(boolean known, String name) {
        this.known = known;
        this.name = name;
    }

    public boolean isKnown() {return known;}

    public void setKnown(boolean known) {this.known = known;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(KNOWN.name(), known);
        json.put(NAME.name(), name);
        return json;
    }
}