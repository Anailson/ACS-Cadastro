package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
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
        return object;
    }

    public static AccompanyModel getByNumSus(long numSus){
        return Realm.getDefaultInstance().where(AccompanyModel.class)
                .equalTo(AccompanyModel.NUM_SUS, numSus)
                .findAll().first(null);
    }

    public static AccompanyModel getByRecord(long record){
        return Realm.getDefaultInstance()
                .where(AccompanyModel.class)
                .equalTo(AccompanyModel.RECORD, record)
                .findAll().first(null);
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
        return object;
    }

    public static long getRecordIfBlack(long record ){

        if(record > AcsRecordPersistence.DEFAULT_INT) return record;

        RealmQuery<AccompanyModel> query = Realm.getDefaultInstance().where(AccompanyModel.class);
        AccompanyModel accompany = query.findAllSorted(AccompanyModel.RECORD, Sort.ASCENDING)
                .first(null);
        if(accompany == null || accompany.getRecord() > AcsRecordPersistence.DEFAULT_INT){
            return AcsRecordPersistence.DEFAULT_INT;
        } else {
            return query.lessThanOrEqualTo(AccompanyModel.RECORD, AcsRecordPersistence.DEFAULT_INT)
                    .findAll().size() * -1;
        }
    }
}
