package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.subModels.House;
import tcc.acs_cadastro_mobile.subModels.HousingSituation;
import tcc.acs_cadastro_mobile.subModels.Pet;
import tcc.acs_cadastro_mobile.subModels.WaterAndSanitation;

public class HousingConditionsPersistence {

    private HousingConditionsPersistence() {}

    public static HousingConditionsModel getHousingConditionsModel(HousingSituation housingSituation,
                   House house, boolean electricEnergy, WaterAndSanitation waterAndSanitation, Pet pet){
        Realm realm = Realm.getDefaultInstance();
        return HousingConditionsModel.newInstance(realm, housingSituation, house, electricEnergy, waterAndSanitation, pet);
    }


    public static HousingSituation getHousingSituation(String situation, String location, String ownership) {
        Realm realm = Realm.getDefaultInstance();
        return HousingConditionsModel.getHousingSituation(realm, situation, location, ownership);
    }

    public static House getHouse(String type, int nResidents, int nRooms, String access,
                                 String construction, String constructionType) {
        Realm realm = Realm.getDefaultInstance();
        return HousingConditionsModel.getHouse(realm, type, nResidents, nRooms, access, construction, constructionType);
    }

    public static WaterAndSanitation getWaterAndSanitation(String waterSupply, String waterTreatment, String bathromm) {
        Realm realm = Realm.getDefaultInstance();
        return HousingConditionsModel.getWaterAndSanitation(realm, waterSupply, waterTreatment, bathromm);
    }

    public static Pet getPet(boolean hasPet, boolean[] pets, int nPets) {
        Realm realm = Realm.getDefaultInstance();
        return HousingConditionsModel.getPet(realm, hasPet, pets, nPets);
    }
}
