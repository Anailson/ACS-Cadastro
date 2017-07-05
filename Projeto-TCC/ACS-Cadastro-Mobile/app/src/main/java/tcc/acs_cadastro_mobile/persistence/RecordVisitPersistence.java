package tcc.acs_cadastro_mobile.persistence;

import android.util.Log;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;

public class RecordVisitPersistence {

    private RecordVisitPersistence() {}

    public static RecordVisitModel get(RecordDetails details, boolean shared){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RecordVisitModel object = realm.copyToRealm(new RecordVisitModel(details, shared));
        realm.commitTransaction();
        return object;
    }

    public static RecordDetails get(long record, String shift, CitizenModel citizen){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RecordDetails object = realm.copyToRealm(new RecordDetails(record, "", "", shift, citizen));
        realm.commitTransaction();
        return object;
    }
}
