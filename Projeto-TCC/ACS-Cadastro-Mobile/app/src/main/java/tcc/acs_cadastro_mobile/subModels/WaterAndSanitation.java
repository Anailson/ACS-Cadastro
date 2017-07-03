package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class WaterAndSanitation extends RealmObject implements Serializable {

    private String waterSupply, waterTreatment, bathroom;

    public WaterAndSanitation() {
        this("", "", "");
    }

    public WaterAndSanitation(String waterSupply, String waterTreatment, String bathroom) {
        this.waterSupply = waterSupply;
        this.waterTreatment = waterTreatment;
        this.bathroom = bathroom;
    }

    public String getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(String waterSupply) {
        this.waterSupply = waterSupply;
    }

    public String getWaterTreatment() {
        return waterTreatment;
    }

    public void setWaterTreatment(String waterTreatment) {
        this.waterTreatment = waterTreatment;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }
}
