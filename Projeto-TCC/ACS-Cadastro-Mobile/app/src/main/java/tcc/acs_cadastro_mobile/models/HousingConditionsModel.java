package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
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

    public static HousingConditionsModel newInstance(Realm realm, HousingSituation housingSituation,
                     House house, boolean electricEnergy, WaterAndSanitation waterAndSanitation, Pet pet) {
        realm.beginTransaction();
        HousingConditionsModel object = realm.createObject(HousingConditionsModel.class);
        object.setHousingSituation(housingSituation);
        object.setHouse(house);
        object.setElectricEnergy(electricEnergy);
        object.setWaterAndSanitation(waterAndSanitation);
        object.setPet(pet);
        realm.commitTransaction();
        return object;
    }

    public static HousingSituation getHousingSituation(Realm realm, String situation, String location,
                                                       String ownership){
        realm.beginTransaction();
        HousingSituation object = realm.createObject(HousingSituation.class);
        object.setSituation(situation);
        object.setLocation(location);
        object.setOwnership(ownership);
        realm.commitTransaction();
        return object;
    }

    public static House getHouse(Realm realm, String type, int nResidents, int nRooms, String access,
                                 String construction, String constructionType){
        realm.beginTransaction();
        House object = realm.createObject(House.class);
        object.setType(type);
        object.setnResident(nResidents);
        object.setnRoom(nRooms);
        object.setAccess(access);
        object.setContruction(construction);
        object.setContructionType(constructionType);
        realm.commitTransaction();
        return object;
    }

    public static WaterAndSanitation getWaterAndSanitation(Realm realm, String waterSupply,
                                               String waterTreatment, String bathroom){
        realm.beginTransaction();
        WaterAndSanitation object = realm.createObject(WaterAndSanitation.class);
        object.setWaterSupply(waterSupply);
        object.setWaterTreatment(waterTreatment);
        object.setBathroom(bathroom);
        realm.commitTransaction();
        return object;
    }

    public static Pet getPet(Realm realm, boolean hasPet, boolean[] pets, int nPets){
        realm.beginTransaction();
        Pet object = realm.createObject(Pet.class);
        object.setHasPet(hasPet);
        object.setPets(pets);
        object.setnPets(nPets);
        realm.commitTransaction();
        return object;
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