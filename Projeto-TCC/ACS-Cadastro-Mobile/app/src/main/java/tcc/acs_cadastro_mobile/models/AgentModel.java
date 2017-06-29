package tcc.acs_cadastro_mobile.models;

import io.realm.RealmObject;

public class AgentModel extends RealmObject {

    private String name;
    private long numSus;
    private int area, equip;

    public AgentModel() {
        this("Maria Joaquina", 3243423, 1111, 22);
    }

    public AgentModel(String name, long numSus, int area, int equip) {
        this.name = name;
        this.numSus = numSus;
        this.area = area;
        this.equip = equip;
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
