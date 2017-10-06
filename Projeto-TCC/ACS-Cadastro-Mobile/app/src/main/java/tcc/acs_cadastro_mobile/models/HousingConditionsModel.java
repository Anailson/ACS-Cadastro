package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.subModels.House;
import tcc.acs_cadastro_mobile.subModels.HousingSituation;
import tcc.acs_cadastro_mobile.subModels.Pet;
import tcc.acs_cadastro_mobile.subModels.WaterAndSanitation;

public class HousingConditionsModel extends RealmObject implements Serializable {

    private HousingSituation housingSituation;
    private House house;
    private boolean electricEnergy;
    private WaterAndSanitation waterAndSanitation;
    private Pet pet;

    public HousingConditionsModel() {
        this(new HousingSituation(), new House(), false, new WaterAndSanitation(), new Pet());
    }

    public HousingConditionsModel(HousingSituation housingSituation, House house, boolean electricEnergy,
                                  WaterAndSanitation waterAndSanitation, Pet pet) {
        this.housingSituation = housingSituation;
        this.house = house;
        this.electricEnergy = electricEnergy;
        this.waterAndSanitation = waterAndSanitation;
        this.pet = pet;
    }

    public HousingSituation getHousingSituation(){return housingSituation;}

    public void setHousingSituation(HousingSituation housingSituation) {
        this.housingSituation = housingSituation;
    }

    public House getHouse() {return house;}

    public void setHouse(House house) {this.house = house;}

    public boolean isElectricEnergy() {return electricEnergy;}

    public void setElectricEnergy(boolean electricEnergy) {this.electricEnergy = electricEnergy;}

    public WaterAndSanitation getWaterAndSanitation() {return waterAndSanitation;}

    public void setWaterAndSanitation(WaterAndSanitation waterAndSanitation) {
        this.waterAndSanitation = waterAndSanitation;
    }

    public Pet getPet() {return pet;}

    public void setPet(Pet pet) {this.pet = pet;}

    public String getSituation() {
        return getHousingSituation().getSituation();
    }

    public String getLocation() {
        return getHousingSituation().getLocation();
    }

    public String getOwnership() {
        return getHousingSituation().getOwnership();
    }

    public String getResidenceType() {
        return getHouse().getType();
    }

    public int getTotalResidents() {
        return getHouse().getnResident();
    }

    public int getTotalRooms() {
        return getHouse().getnRoom();
    }

    public String getResidenceAccess() {
        return getHouse().getAccess();
    }

    public String getConstruction() {
        return getHouse().getContruction();
    }

    public String getConstructionType() {
        return getHouse().getContructionType();
    }

    public String getWaterSupply(){
        return getWaterAndSanitation().getWaterSupply();
    }

    public String getWaterTreatment(){
        return getWaterAndSanitation().getWaterTreatment();
    }

    public String getBathroom(){
        return getWaterAndSanitation().getBathroom();
    }

    public boolean[] getPets() {return getPet().getPets();}

    public boolean isHasPets() {return getPet().isHasPet();}

    public int getTotalPets() {return getPet().getnPets();}

}