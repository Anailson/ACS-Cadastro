package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.subModels.House;
import tcc.acs_cadastro_mobile.subModels.HousingSituation;
import tcc.acs_cadastro_mobile.subModels.Pet;
import tcc.acs_cadastro_mobile.subModels.WaterAndSanitation;

public class HousingConditionsPersistence {

    private HousingConditionsPersistence() {}

    public static HousingConditionsModel get(HousingSituation housingSituation, House house,
                             boolean electricEnergy, WaterAndSanitation waterAndSanitation, Pet pet){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        HousingConditionsModel object = realm.copyToRealm(new HousingConditionsModel(housingSituation,
                house, electricEnergy, waterAndSanitation, pet));
        realm.commitTransaction();
        realm.close();
        return object;
    }


    public static HousingSituation getHousingSituation(String situation, String location, String ownership) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        HousingSituation object = realm.copyToRealm(new HousingSituation(situation, location, ownership));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static House get(String type, int nResidents, int nRooms, String access,
                                 String construction, String constructionType) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        House object = realm.copyToRealm(new House(type, nResidents, nRooms, access, construction, constructionType));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static WaterAndSanitation getWaterAndSanitation(String waterSupply, String waterTreatment, String bathroom) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        WaterAndSanitation object = realm.copyToRealm(new WaterAndSanitation(waterSupply, waterTreatment, bathroom));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Pet get(boolean hasPet, boolean[] pets, int nPets) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Pet object = realm.copyToRealm(new Pet(hasPet, pets, nPets));
        realm.commitTransaction();
        realm.close();
        return object;
    }
}
