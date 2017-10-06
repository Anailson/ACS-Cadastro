package tcc.acs_cadastro_mobile.persistence;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;
import tcc.acs_cadastro_mobile.models.ResidenceModel;

public class ResidencePersistence  {

    private ResidencePersistence() {}

    public static ResidenceModel getInstance(AddressDataModel addressData, HousingConditionsModel housingConditions,
                             RealmList<HousingHistoricalModel> housingHistorical){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ResidenceModel residence = new ResidenceModel(addressData, housingConditions, housingHistorical);
        realm.copyToRealmOrUpdate(residence);
        realm.close();
        realm.commitTransaction();
        return residence;
    }

    public static ResidenceModel save(AddressDataModel addressData, HousingConditionsModel housingConditions,
                            RealmList<HousingHistoricalModel>  housingHistorical) {
        return save(getInstance(addressData, housingConditions, housingHistorical));
    }

    public static ResidenceModel save(ResidenceModel residence) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ResidenceModel saved = realm.copyToRealmOrUpdate(residence);
        realm.commitTransaction();
        realm.close();
        return saved;
    }

    public static RealmResults<ResidenceModel> getAll(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ResidenceModel> results = realm.where(ResidenceModel.class).findAll();
        realm.close();
        return results;
    }

    public static ResidenceModel get(String name){
        return getAll().where().equalTo(ResidenceModel.STREET_NAME, name).findFirst();
    }

    public static ResidenceModel get(long cep){
        return getAll().where().equalTo(ResidenceModel.CEP, cep).findFirst();
    }

    public static void update(List<ResidenceModel> list) {
        for (ResidenceModel residence : list){
            update(residence);
        }
    }

    public static ResidenceModel update(ResidenceModel residence){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        residence.setStatus(AcsRecordPersistence.OK);
        ResidenceModel updated = realm.copyToRealmOrUpdate(residence);
        realm.commitTransaction();
        return updated;
    }

}
