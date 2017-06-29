package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;


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
}
