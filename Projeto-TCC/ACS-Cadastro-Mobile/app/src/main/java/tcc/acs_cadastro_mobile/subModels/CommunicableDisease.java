package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;

import static tcc.acs_cadastro_mobile.Constants.Accompany.DENGUE;
import static tcc.acs_cadastro_mobile.Constants.Accompany.DST;
import static tcc.acs_cadastro_mobile.Constants.Accompany.LEPROSY;
import static tcc.acs_cadastro_mobile.Constants.Accompany.TUBERCULOSIS;

public class CommunicableDisease extends RealmObject implements Serializable {

    private boolean tuberculosis, leprosy, dengue, dst;

    public CommunicableDisease() {
        this(new boolean[4]);
    }

    public CommunicableDisease(boolean[] communicable) {
        this(communicable[0], communicable[1], communicable[2], communicable[3]);

        if(communicable.length != 4){
            throw new IllegalArgumentException("Length of conditions array must be 4 instead " + communicable.length);
        }
    }

    public CommunicableDisease(boolean tuberculosis, boolean leprosy, boolean dengue, boolean dst) {
        this.tuberculosis = tuberculosis;
        this.leprosy = leprosy;
        this.dengue = dengue;
        this.dst = dst;
    }

    public boolean[] getValues(){
        return new boolean[]{tuberculosis, leprosy, dengue, dst};
    }

    public boolean isTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public boolean isLeprosy() {
        return leprosy;
    }

    public void setLeprosy(boolean leprosy) {
        this.leprosy = leprosy;
    }

    public boolean isDengue() {
        return dengue;
    }

    public void setDengue(boolean dengue) {
        this.dengue = dengue;
    }

    public boolean isDst() {
        return dst;
    }

    public void setDst(boolean dst) {
        this.dst = dst;
    }

    public JSONObject asJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(TUBERCULOSIS.name(), tuberculosis);
            json.put(LEPROSY.name(), leprosy);
            json.put(DENGUE.name(), dengue);
            json.put(DST.name(), dst);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
