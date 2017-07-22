package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;

public class Deficiency extends RealmObject implements Serializable {

    private boolean isDeficiency, hearing, visual, intellectual, phisical, another;


    public Deficiency() {}

    public Deficiency(boolean isDeficiency, boolean[] values) {
        this.isDeficiency = isDeficiency;
        setDeficiencys(values);
    }

    public boolean[] getDeficiencys(){
        return new boolean[]{hearing, visual, intellectual, phisical, another};
    }

    public void setDeficiencys(boolean[] deficiency){
        if(deficiency.length < 5){
            throw new IllegalArgumentException();
        }
        setHearing(deficiency[0]);
        setVisual(deficiency[1]);
        setIntellectual(deficiency[2]);
        setPhisical(deficiency[3]);
        setAnother(deficiency[4]);
    }

    public boolean isDeficiency() {
        return isDeficiency;
    }

    public void setDeficiency(boolean deficiency) {
        isDeficiency = deficiency;
    }

    public boolean isHearing() {
        return hearing;
    }

    public void setHearing(boolean hearing) {
        this.hearing = hearing;
    }

    public boolean isVisual() {
        return visual;
    }

    public void setVisual(boolean visual) {
        this.visual = visual;
    }

    public boolean isIntellectual() {
        return intellectual;
    }

    public void setIntellectual(boolean intellectual) {
        this.intellectual = intellectual;
    }

    public boolean isPhisical() {
        return phisical;
    }

    public void setPhisical(boolean phisical) {
        this.phisical = phisical;
    }

    public boolean isAnother() {
        return another;
    }

    public void setAnother(boolean another) {
        this.another = another;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.DEFICIENCY.name(), isDeficiency);
        json.put(Constants.Citizen.HEARING.name(), hearing);
        json.put(Constants.Citizen.VISUAL.name(), visual);
        json.put(Constants.Citizen.INTELLECTUAL.name(), intellectual);
        json.put(Constants.Citizen.PHYSICAL.name(), phisical);
        json.put(Constants.Citizen.ANOTHER.name(), another);
        return json;
    }
}
