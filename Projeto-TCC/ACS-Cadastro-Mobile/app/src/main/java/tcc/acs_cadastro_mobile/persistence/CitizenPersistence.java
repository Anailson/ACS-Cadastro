package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import io.realm.RealmResults;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;

public class CitizenPersistence{

    private CitizenPersistence() {}

    public static CitizenModel save(CitizenModel citizen){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CitizenModel saved = realm.copyToRealm(citizen);
        realm.commitTransaction();
        return saved;
    }

    public static CitizenModel get(String name){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CitizenModel.class)
                .equalTo(CitizenModel.NAME, name).findFirst();
    }

    public static CitizenModel get(long numSus){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CitizenModel.class)
                .equalTo(CitizenModel.NUM_SUS, numSus)
                .findFirst();
    }

    public static RealmResults<CitizenModel> getAll(){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CitizenModel.class).findAll();
    }

    public static CitizenModel getInstance(PersonalDataModel personalData, SocialDemographicModel socioDemographicData,
                   HealthConditionsModel healthConditions, StreetSituationModel streetSituation){
        Realm realm = Realm.getDefaultInstance();
        return CitizenModel.newInstance(realm, personalData, socioDemographicData, healthConditions, streetSituation);
    }
}
