package tcc.acs_cadastro_mobile.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.RealmInt;
import tcc.acs_cadastro_mobile.subModels.CommunicableDisease;
import tcc.acs_cadastro_mobile.subModels.ConditionDiseases;
import tcc.acs_cadastro_mobile.subModels.TrackingDiseases;

public class ConditionPersistence {

    private ConditionPersistence() {}


    public static ConditionsModel get(ConditionDiseases diseases, CommunicableDisease communicable,
                                      TrackingDiseases tracking, RealmList<RealmInt> cids){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ConditionsModel object = realm.copyToRealm(new ConditionsModel(diseases, communicable, tracking, cids));
        realm.commitTransaction();
        return object;
    }

    public static ConditionDiseases getConditionDiseases(boolean[] conditions){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ConditionDiseases object = realm.copyToRealm(new ConditionDiseases(conditions));
        realm.commitTransaction();
        return object;
    }

    public static CommunicableDisease getCommunicableDisease(boolean[] communicables){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CommunicableDisease object = realm.copyToRealm(new CommunicableDisease(communicables));
        realm.commitTransaction();
        return object;
    }

    public static TrackingDiseases getTrackingDiseases(boolean[] trackings){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TrackingDiseases object = realm.copyToRealm(new TrackingDiseases(trackings));
        realm.commitTransaction();
        return object;
    }

    public static RealmList<RealmInt> getAnotherCids(RealmInt[] cids){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmList<RealmInt> list = new RealmList<>();
        Collections.addAll(list, cids);
        realm.copyToRealm(list);
        realm.commitTransaction();
        return list;
    }
}
