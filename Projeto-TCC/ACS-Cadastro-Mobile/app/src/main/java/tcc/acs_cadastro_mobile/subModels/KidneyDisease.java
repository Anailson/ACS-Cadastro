package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;

public class KidneyDisease extends RealmObject implements Serializable {

    private boolean isKidneyDisease, insuficiency, another, dontKnow;

    public KidneyDisease() {
        this(false, new boolean[3]);
    }

    public KidneyDisease(boolean kidneyDiseas, boolean[] diseases) {
        this(kidneyDiseas, diseases[0], diseases[1], diseases[2]);
    }

    public KidneyDisease(boolean isKidneyDisease, boolean insuficiency, boolean another, boolean dontKnow) {
        this.isKidneyDisease = isKidneyDisease;
        this.insuficiency = insuficiency;
        this.another = another;
        this.dontKnow = dontKnow;
    }

    public boolean[] getKidneyDiseases() {
        return new boolean[]{isInsuficiency(), isAnother(), isDontKnow()};
    }

    public void setDisease(boolean diseases[]){
        if(diseases.length != 3){
            throw new IllegalArgumentException("Array length must be equals at 3. Length = " + diseases.length + ".");
        }
        setInsuficiency(diseases[0]);
        setAnother(diseases[1]);
        setDontKnow(diseases[2]);
    }

    public boolean isKidneyDisease() {
        return isKidneyDisease;
    }

    public void setKidneyDisease(boolean kidneyDisease) {
        isKidneyDisease = kidneyDisease;
    }

    public boolean isInsuficiency() {
        return insuficiency;
    }

    public void setInsuficiency(boolean insuficiency) {
        this.insuficiency = insuficiency;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }

    public boolean isDontKnow() {
        return dontKnow;
    }

    public void setDontKnow(boolean dontKnow) {
        this.dontKnow = dontKnow;
    }

    public JSONObject getAsJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.KIDNEY_DISEASE.name(), isKidneyDisease);
        json.put(Constants.Citizen.INSUFFICIENCY.name(), insuficiency);
        json.put(Constants.Citizen.ANOTHER.name(), another);
        json.put(Constants.Citizen.DONT_KNOWN.name(), dontKnow);
        return json;
    }
}
