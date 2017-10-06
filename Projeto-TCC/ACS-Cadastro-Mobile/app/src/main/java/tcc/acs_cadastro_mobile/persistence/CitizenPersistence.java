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
        realm.close();
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
        realm.close();
        return saved;
    }

    public static void update(List<CitizenModel> list) {
        updateStatus(list, AcsRecordPersistence.OK);
    }

    public static CitizenModel update(CitizenModel citizen) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        citizen.setStatus(AcsRecordPersistence.OK);
        CitizenModel update = realm.copyToRealmOrUpdate(citizen);
        realm.commitTransaction();
        realm.close();
        return update;
    }

    public static RealmResults<CitizenModel> getAll() {
        RealmResults<CitizenModel> list = Realm.getDefaultInstance().where(CitizenModel.class).findAll();
        Realm.getDefaultInstance().close();
        return list;
    }

    public static RealmResults<CitizenModel> getAllByStatus(int status){
        RealmResults<CitizenModel> list = Realm.getDefaultInstance()
                .where(CitizenModel.class)
                .equalTo(CitizenModel.STATUS, status)
                .findAll();
        Realm.getDefaultInstance().close();
        return list;
    }

    public static List<CitizenModel> getAllPendents(int status){
        List<CitizenModel> pendents = new ArrayList<>();
        pendents.addAll(getAllByStatus(status));
        return pendents;
    }

    public static CitizenModel get(String name) {
        Realm realm = Realm.getDefaultInstance();
        CitizenModel citizen = realm.where(CitizenModel.class)
                .equalTo(CitizenModel.NAME, name)
                .findFirst();
        realm.close();
        return citizen;
    }

    public static CitizenModel get(long numSus) {
        CitizenModel citizen = Realm.getDefaultInstance().where(CitizenModel.class)
                .equalTo(CitizenModel.NUM_SUS, numSus)
                .findAll().first(null);
        Realm.getDefaultInstance().close();
        return citizen;
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

    public static void updateStatus(CitizenModel citizen, int status){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        citizen.setStatus(status);
        realm.copyToRealmOrUpdate(citizen);
        realm.commitTransaction();
        realm.close();
    }

    public static List<CitizenModel> updateStatus(List<CitizenModel> citizens, int status){
        for(CitizenModel citizen : citizens){
            updateStatus(citizen, status);
        }
        return citizens;
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
