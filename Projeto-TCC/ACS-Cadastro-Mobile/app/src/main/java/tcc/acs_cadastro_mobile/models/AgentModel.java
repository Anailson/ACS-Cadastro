package tcc.acs_cadastro_mobile.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class AgentModel extends RealmObject implements Serializable {

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

    public AgentModel(JSONObject json) {

        try {
            this.name = json.getString("NAME");
            this.numSus = json.getLong("NUM_SUS");
            this.area = json.getInt("AREA");
            this.equip = json.getInt("EQUIP");
        } catch (JSONException | NullPointerException e) {

            name = AcsRecordPersistence.DEFAULT_STR;
            numSus = area = equip = AcsRecordPersistence.DEFAULT_INT;
        }

    }

    public static List<AgentModel> getList(JSONArray array) throws JSONException {
        List<AgentModel> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(new AgentModel(array.getJSONObject(i)));
        }
        return list;
    }

    public JSONObject asJson() {
        JSONObject json = new JSONObject();

        try {
            json.put("NAME", name);
            json.put("NUM_SUS", numSus);
            json.put("AREA", area);
            json.put("EQUIP", equip);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
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
