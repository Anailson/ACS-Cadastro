package tcc.acs_cadastro_mobile.subModels;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;

import java.io.Serializable;

import static tcc.acs_cadastro_mobile.Constants.Accompany.HEIGHT;
import static tcc.acs_cadastro_mobile.Constants.Accompany.VACCINATES;
import static tcc.acs_cadastro_mobile.Constants.Accompany.WEIGHT;


public class Anthropometric extends RealmObject implements Serializable {

    private float weight;
    private int height;
    private boolean vaccinates;

    public Anthropometric() {
        this(0, 0, false);
    }

    public Anthropometric(float weight, int height, boolean vaccinates) {
        this.weight = weight;
        this.height = height;
        this.vaccinates = vaccinates;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int heigth) {
        this.height = heigth;
    }

    public boolean isVaccinates() {
        return vaccinates;
    }

    public void setVaccinates(boolean vaccinates) {
        this.vaccinates = vaccinates;
    }

    public JSONObject asJson() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(WEIGHT.name(), weight);
        json.put(HEIGHT.name(), height);
        json.put(VACCINATES.name(), vaccinates);
        return json;
    }
}
