package tcc.acs_cadastro_mobile.persistence;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;

public class CitizenPersistence {

    private CitizenPersistence() {
    }

    public static CitizenModel get(PersonalDataModel personalData, SocialDemographicModel socialDemographicModel,
                                   HealthConditionsModel healthConditions, StreetSituationModel streetSituation) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CitizenModel object = realm.copyToRealm(new CitizenModel(personalData, socialDemographicModel,
                healthConditions, streetSituation));
        realm.commitTransaction();
        return object;
    }

    public static CitizenModel save(PersonalDataModel personalData, SocialDemographicModel socialDemographicData,
                                    HealthConditionsModel healthConditions, StreetSituationModel streetSituation) {
        return save(get(personalData, socialDemographicData, healthConditions, streetSituation));
    }

    public static CitizenModel save(CitizenModel citizen) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        citizen.setStatus(AcsRecordPersistence.INSERT);
        CitizenModel saved = realm.copyToRealmOrUpdate(citizen);
        realm.commitTransaction();
        return saved;
    }

    public static RealmResults<CitizenModel> getAll() {
        return Realm.getDefaultInstance().where(CitizenModel.class).findAll();
    }

    public static CitizenModel get(String name) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CitizenModel.class)
                .equalTo(CitizenModel.NAME, name)
                .findFirst();
    }

    public static CitizenModel get(long numSus) {
        return Realm.getDefaultInstance().where(CitizenModel.class)
                .equalTo(CitizenModel.NUM_SUS, numSus)
                .findAll().first(null);
    }

    public static String[] getNumSus() {
        RealmResults<CitizenModel> list = getAll()
                .where()
                .greaterThan(CitizenModel.NUM_SUS, AcsRecordPersistence.DEFAULT_INT)
                .findAll();
        String[] numSus = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            numSus[i] = String.valueOf(list.get(i).getNumSus());
        }
        return numSus;
    }

    public static List<Long> getNumSusAsList() {
        List<Long> numSus = new ArrayList<>();
        RealmResults<CitizenModel> list = getAll()
                .where()
                .greaterThan(CitizenModel.NUM_SUS, AcsRecordPersistence.DEFAULT_INT)
                .findAll();
        for (CitizenModel citizen : list) {
            numSus.add(citizen.getNumSus());
        }

        return numSus;
    }
}
