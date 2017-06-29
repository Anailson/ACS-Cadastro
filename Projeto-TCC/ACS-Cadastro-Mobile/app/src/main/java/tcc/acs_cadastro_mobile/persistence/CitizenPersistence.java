package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
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
        CitizenModel object = realm.copyToRealmOrUpdate(new CitizenModel(personalData, socialDemographicModel,
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
        CitizenModel saved = realm.copyToRealmOrUpdate(citizen);
        realm.commitTransaction();
        return saved;
    }

    public static RealmResults<CitizenModel> getAll() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CitizenModel.class).findAll();
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
        RealmResults<CitizenModel> list = getAll().where()
                .greaterThan(CitizenModel.NUM_SUS, 0).findAll();
        String[] numSus = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            numSus[i] = String.valueOf(list.get(i).getNumSus());
        }
        return numSus;
    }

    public static long getMinorNumSusIfBlank(long numSus) {

        if (numSus > AcsRecordPersistence.DEFAULT_INT) return numSus;

        RealmQuery<CitizenModel> query = Realm.getDefaultInstance().where(CitizenModel.class);
        CitizenModel citizen = query
                .findAllSorted(CitizenModel.NUM_SUS, Sort.ASCENDING)
                .first(null);

        if(citizen == null || citizen.getNumSus() > AcsRecordPersistence.DEFAULT_INT){

            return AcsRecordPersistence.DEFAULT_INT;

        } else {

           return query
                   .lessThanOrEqualTo(CitizenModel.NUM_SUS, AcsRecordPersistence.DEFAULT_INT)
                   .findAll()
                   .size() * -1;
        }
    }
}
