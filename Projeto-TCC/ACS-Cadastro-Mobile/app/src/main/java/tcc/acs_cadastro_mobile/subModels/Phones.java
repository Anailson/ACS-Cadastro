package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;


public class Phones extends RealmObject implements Serializable {

    private String home, reference;

    public Phones() {
        this("", "");
    }

    public Phones(String home, String reference) {
        this.home = home;
        this.reference = reference;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public JSONObject asJson() {
        JSONObject json = new JSONObject();
        try{
            json.put(Constants.Residence.HOME.name(), home);
            json.put(Constants.Residence.REFERENCE.name(), reference);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
