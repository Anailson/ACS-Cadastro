package tcc.acs_cadastro_mobile.persistence;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.models.RecordDataModel;

public class AccompanyPersistence {

    private AccompanyPersistence() {}

    public static AccompanyModel get(RecordDataModel recordData, ConditionsModel conditions,
                                     ExamsModel exams, NasfConductModel nasfConduct){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        AccompanyModel object = realm.copyToRealmOrUpdate(new AccompanyModel(recordData, conditions, exams, nasfConduct));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static AccompanyModel getByNumSus(long numSus){
        AccompanyModel accompany = Realm.getDefaultInstance().where(AccompanyModel.class)
                .equalTo(AccompanyModel.NUM_SUS, numSus)
                .findAll().first(null);
        Realm.getDefaultInstance().close();
        return accompany;
    }

    public static AccompanyModel getByRecord(long record){
        AccompanyModel accompany = Realm.getDefaultInstance()
                .where(AccompanyModel.class)
                .equalTo(AccompanyModel.RECORD, record)
                .findAll().first(null);
        Realm.getDefaultInstance().close();
        return accompany;
    }

    public static RealmResults<AccompanyModel> getAll(){
        return Realm.getDefaultInstance()
                .where(AccompanyModel.class)
                .findAll();
    }

    public static AccompanyModel save(RecordDataModel recordData, ConditionsModel conditions,
                                      ExamsModel exams, NasfConductModel nasfConduct){
        return save(get(recordData, conditions, exams, nasfConduct));
    }

    public static AccompanyModel save(AccompanyModel accompany){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        AccompanyModel object = realm.copyToRealmOrUpdate(accompany);
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static List<AccompanyModel> getAllByStatus(int status) {
        return Realm.getDefaultInstance().where(AccompanyModel.class).equalTo(AccompanyModel.STATUS, status).findAll();
    }

    public static AccompanyModel updateStatus(AccompanyModel accompany, int status){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        accompany.setStatus(status);
        realm.copyToRealmOrUpdate(accompany);
        realm.commitTransaction();
        realm.close();
        return accompany;
    }

    public static List<AccompanyModel> updateStatus(List<AccompanyModel> accompanies, int status) {
        for(AccompanyModel accompany : accompanies){
            updateStatus(accompany, status);
        }
        return accompanies;
    }

    public static void update(List<AccompanyModel> list) {
        updateStatus(list, AcsRecordPersistence.OK);
    }
}