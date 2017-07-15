package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class AgentModel extends RealmObject implements Serializable{

    private String name;
    private long numSus;
    private int area, equip;

    public AgentModel() {
        name = AcsRecordPersistence.DEFAULT_STR;
        numSus = area = equip = AcsRecordPersistence.DEFAULT_INT;
    }

    public AgentModel(String name, long numSus, int area, int equip) {
        this.name = name;
        this.numSus = numSus;
        this.area = area;
        this.equip = equip;
    }


    public static AgentModel get(JSONObject object) throws JSONException {
        if(object == null) return null;

        return new AgentModel(
                object.getString("NAME"),
                object.getLong("NUM_SUS"),
                object.getInt("AREA"),
                object.getInt("EQUIP"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumSus() {
        return numSus;
    }

    public void setNumSus(long numSus) {
        this.numSus = numSus;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getEquip() {
        return equip;
    }

    public void setEquip(int equip) {
        this.equip = equip;
    }
}
