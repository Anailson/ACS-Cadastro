package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.models.CitizenModel;

import java.io.Serializable;


public class Hygiene extends RealmObject implements Serializable {

    private boolean isHygiene, bath, sanitary, oral, another;

    public Hygiene() {
        this(false, new boolean[4]);
    }

    public Hygiene(boolean isHygiene, boolean []hygienes) {
        this(isHygiene, hygienes[0], hygienes[1], hygienes[2], hygienes[3]);
    }

    public Hygiene(boolean isHygiene, boolean bath, boolean sanitary, boolean oral, boolean another) {
        this.isHygiene = isHygiene;
        this.bath = bath;
        this.sanitary = sanitary;
        this.oral = oral;
        this.another = another;
    }

    public boolean[] getHygienes(){
        return new boolean[]{bath, sanitary, oral, another};
    }

    public void setHygienes(boolean[] hygienes){
        if(hygienes.length != 4){
            throw new IllegalArgumentException("Array length must be equals at 5. Length = " + hygienes.length + ".");
        }
        setBath(hygienes[0]);
        setSanitary(hygienes[1]);
        setOral(hygienes[2]);
        setAnother(hygienes[3]);
    }

    public boolean isHygiene() {
        return isHygiene;
    }

    public void setHygiene(boolean hygiene) {
        isHygiene = hygiene;
    }

    public boolean isBath() {
        return bath;
    }

    public void setBath(boolean bath) {
        this.bath = bath;
    }

    public boolean isSanitary() {
        return sanitary;
    }

    public void setSanitary(boolean sanitary) {
        this.sanitary = sanitary;
    }

    public boolean isOral() {
        return oral;
    }

    public void setOral(boolean oral) {
        this.oral = oral;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.HYGIENE.name(), isHygiene);
        json.put(Constants.Citizen.BATH.name(), bath);
        json.put(Constants.Citizen.SANITARY.name(), sanitary);
        json.put(Constants.Citizen.ORAL.name(), oral);
        json.put(Constants.Citizen.ANOTHER.name(), another);
        return json;
    }
}

