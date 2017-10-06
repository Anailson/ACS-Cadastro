package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.subModels.ActiveSearch;
import tcc.acs_cadastro_mobile.subModels.AnotherReasons;
import tcc.acs_cadastro_mobile.subModels.Following;

public class ReasonsVisitPersistence {

    private ReasonsVisitPersistence() {
    }

    public static ReasonsVisitModel get(ActiveSearch search, Following following, AnotherReasons another, String result) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ReasonsVisitModel object = realm.copyToRealm(new ReasonsVisitModel(search, following, another, result));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static ActiveSearch getActiveSearchs(boolean[] search) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ActiveSearch object = realm.copyToRealm(new ActiveSearch(search));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Following getFollowing(boolean[] followings) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Following object = realm.copyToRealm(new Following(followings));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static AnotherReasons getAnotherReasons(boolean[] reasons) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        AnotherReasons object = realm.copyToRealm(new AnotherReasons(reasons));
        realm.commitTransaction();
        realm.close();
        return object;
    }
}
