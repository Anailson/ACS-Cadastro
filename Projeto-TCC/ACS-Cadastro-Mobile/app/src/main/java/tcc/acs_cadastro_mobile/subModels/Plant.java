package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

public class Plant extends RealmObject implements Serializable {

    private boolean isPlants;
    private String value;

    public Plant() {
        this(false, "");
    }

    public Plant(boolean isPlants, String value) {
        this.isPlants = isPlants;
        this.value = value;
    }

    public boolean isPlants() {
        return isPlants;
    }

    public void setPlants(boolean plants) {
        isPlants = plants;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
