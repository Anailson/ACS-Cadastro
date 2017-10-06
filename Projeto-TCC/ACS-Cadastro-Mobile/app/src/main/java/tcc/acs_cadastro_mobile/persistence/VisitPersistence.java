package tcc.acs_cadastro_mobile.persistence;

import java.util.List;

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
        realm.close();
        return object;
    }

    public static VisitModel getByRecord(long record) {

        VisitModel visit = Realm.getDefaultInstance()
                .where(VisitModel.class)
                .equalTo(VisitModel.RECORD, record)
                .findAll()
                .first(null);
        Realm.getDefaultInstance().close();
        return visit;
    }

    public static VisitModel getByNumSus(long numSus) {
        VisitModel visit = Realm.getDefaultInstance()
                .where(VisitModel.class)
                .equalTo(VisitModel.NUM_SUS, numSus)
                .findAll()
                .first(null);
        Realm.getDefaultInstance().close();
        return visit;
    }

    public static RealmResults<VisitModel> getAll() {
        RealmResults<VisitModel> visit = Realm.getDefaultInstance()
                .where(VisitModel.class)
                .findAll();
        Realm.getDefaultInstance().close();
        return visit;
    }

    public static VisitModel save(RecordVisitModel details, ReasonsVisitModel reasons) {
        return save(get(details, reasons));
    }

    public static VisitModel save(VisitModel visit) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        VisitModel object = realm.copyToRealmOrUpdate(visit);
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static void update(List<VisitModel> list) {
        for(VisitModel visit: list){
            update(visit);
        }
    }

    public static VisitModel update(VisitModel visit){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        visit.setStatus(AcsRecordPersistence.OK);
        VisitModel updated = realm.copyToRealmOrUpdate(visit);
        realm.commitTransaction();
        return updated;
    }
}