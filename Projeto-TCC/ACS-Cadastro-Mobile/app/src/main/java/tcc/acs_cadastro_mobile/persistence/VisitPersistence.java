package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;
import tcc.acs_cadastro_mobile.models.VisitModel;

public class VisitPersistence {

    private VisitPersistence() {
    }

    public static VisitModel get(RecordVisitModel details, ReasonsVisitModel reasons) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        VisitModel object = realm.copyToRealm(new VisitModel(details, reasons));
        realm.commitTransaction();
        return object;
    }

    public static VisitModel getByRecord(long record) {
        return Realm.getDefaultInstance()
                .where(VisitModel.class)
                .equalTo(VisitModel.RECORD, record)
                .findAll()
                .first(null);
    }

    public static VisitModel getByNumSus(long numSus) {
        return Realm.getDefaultInstance()
                .where(VisitModel.class)
                .equalTo(VisitModel.NUM_SUS, numSus)
                .findAll()
                .first(null);
    }

    public static RealmResults<VisitModel> getAll() {
        return Realm.getDefaultInstance()
                .where(VisitModel.class)
                .findAll();
    }

    public static VisitModel save(RecordVisitModel details, ReasonsVisitModel reasons) {
        return save(get(details, reasons));
    }

    public static VisitModel save(VisitModel visit) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        VisitModel object = realm.copyToRealmOrUpdate(visit);
        realm.commitTransaction();
        return object;
    }

    public static long getRecordIfBlanck(long record) {
        if (record > AcsRecordPersistence.DEFAULT_INT) return record;
        RealmQuery<VisitModel> query = Realm.getDefaultInstance().where(VisitModel.class);

        VisitModel visit = query.findAllSorted(VisitModel.RECORD, Sort.ASCENDING)
                .first(null);
        if (visit == null || visit.getRecord() > AcsRecordPersistence.DEFAULT_INT) {

            return AcsRecordPersistence.DEFAULT_INT;

        } else {

            return query.lessThanOrEqualTo(VisitModel.RECORD, AcsRecordPersistence.DEFAULT_INT)
                    .findAll()
                    .size() * -1;
        }
    }
}