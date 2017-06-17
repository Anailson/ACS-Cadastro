package tcc.acs_cadastro_mobile.persistence;

import android.util.Log;

import io.realm.Realm;
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

    public static CitizenModel getInstance(PersonalDataModel personalData, SocialDemographicModel socialDemographicModel,
                                           HealthConditionsModel healthConditions, StreetSituationModel streetSituation) {
        Realm realm = Realm.getDefaultInstance();
        return CitizenModel.newInstance(realm, personalData, socialDemographicModel, healthConditions, streetSituation);
    }

    public static CitizenModel save(PersonalDataModel personalData, SocialDemographicModel socialDemographicData,
                                    HealthConditionsModel healthConditions, StreetSituationModel streetSituation) {
        return save(getInstance(personalData, socialDemographicData, healthConditions, streetSituation));
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

    public static long getMinorNumSus(long numSus) {

        if (numSus > 0) return numSus;

        Realm realm = Realm.getDefaultInstance();

        long minorNumSus = realm.where(CitizenModel.class)
                .findAllSorted(CitizenModel.NUM_SUS, Sort.ASCENDING)
                .first().getNumSus();
        if (numSus == 0) {
            numSus = minorNumSus > 0 ? 0 : minorNumSus - 1;
        }
        return numSus;
    }
}
