package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.subModels.Conduct;
import tcc.acs_cadastro_mobile.subModels.Forwarding;
import tcc.acs_cadastro_mobile.subModels.NASF;

public class NasfConductPersistence {

    private NasfConductPersistence() {}


    public static NasfConductModel get(boolean nasfConduct, NASF nasf, Conduct conduct, Forwarding forwarding){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        NasfConductModel object = realm.copyToRealm(new NasfConductModel(nasfConduct, nasf, conduct, forwarding));
        realm.commitTransaction();
        return object;
    }

    public static NASF getNasf(boolean[] nasf){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        NASF object = realm.copyToRealm(new NASF(nasf));
        realm.commitTransaction();
        return object;
    }

    public static Conduct getConduct(boolean[] conducts){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Conduct object = realm.copyToRealm(new Conduct(conducts));
        realm.commitTransaction();
        return object;
    }

    public static Forwarding getForwarding(boolean[] forwarding){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Forwarding object = realm.copyToRealm(new Forwarding(forwarding));
        realm.commitTransaction();
        return object;
    }
}
