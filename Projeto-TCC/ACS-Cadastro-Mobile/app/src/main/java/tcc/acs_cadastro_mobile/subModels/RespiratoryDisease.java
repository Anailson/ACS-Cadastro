package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.models.ExamsModel;

import java.io.Serializable;

public class RespiratoryDisease extends RealmObject implements Serializable {

    private boolean isRespiratoryDisease, asthma, emphysema, another, dontKnow;

    public boolean[] getRespiratoryDiseases() {
        return new boolean[]{asthma, emphysema, another, dontKnow};
    }

    public RespiratoryDisease() {
        this(false, new boolean[4]);
    }

    public RespiratoryDisease(boolean isRespiratoryDisease, boolean[] diseases) {
        this(isRespiratoryDisease, diseases[0], diseases[1], diseases[2], diseases[3]);
    }

    public RespiratoryDisease(boolean isRespiratoryDisease, boolean asthma, boolean emphysema, boolean another, boolean dontKnow) {
        this.isRespiratoryDisease = isRespiratoryDisease;
        this.asthma = asthma;
        this.emphysema = emphysema;
        this.another = another;
        this.dontKnow = dontKnow;
    }

    public void setRespiratoryDisease(boolean [] diseases){

        if(diseases.length != 4){
            throw new IllegalArgumentException("Array length must be equals at 4. Length = " + diseases.length + ".");
        }
        setAsthma(diseases[0]);
        setEmphysema(diseases[1]);
        setAnother(diseases[2]);
        setDontKnow(diseases[3]);
    }

    public boolean isRespiratoryDisease() {
        return isRespiratoryDisease;
    }

    public void setRespiratoryDisease(boolean respiratoryDisease) {
        isRespiratoryDisease = respiratoryDisease;
    }

    public boolean isAsthma() {
        return asthma;
    }

    public void setAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public boolean isEmphysema() {
        return emphysema;
    }

    public void setEmphysema(boolean emphysema) {
        this.emphysema = emphysema;
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
        json.put(Constants.Citizen.RESPIRATORY_DISEASE.name(), isRespiratoryDisease);
        json.put(Constants.Citizen.ASTHMA.name(), asthma);
        json.put(Constants.Citizen.EMPHYSEMA.name(), emphysema);
        json.put(Constants.Citizen.ANOTHER.name(), another);
        json.put(Constants.Citizen.DONT_KNOWN.name(), dontKnow);
        return json;
    }
}
