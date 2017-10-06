package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.RecordDataModel;
import tcc.acs_cadastro_mobile.subModels.Anthropometric;
import tcc.acs_cadastro_mobile.subModels.KidAndPregnant;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;

public class RecordDataPersistence {

    private RecordDataPersistence() {}

    public static RecordDataModel get(RecordDetails details, Anthropometric anthropometric, KidAndPregnant kidAndPregnant){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RecordDataModel object = realm.copyToRealm(new RecordDataModel(details, anthropometric, kidAndPregnant));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static RecordDetails get(long record, String placeCare, String typeCare, String shift, CitizenModel citizen){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RecordDetails object = realm.copyToRealm(new RecordDetails(record, placeCare, typeCare, shift, citizen));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Anthropometric get(float weight, int height, boolean vaccinates){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Anthropometric object = realm.copyToRealm(new Anthropometric(weight, height, vaccinates));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static KidAndPregnant get(String breastFeeding, String dum, boolean plannedPregnancy, int weeks,
                                                  int previous, int childBirth, String homeCare){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        KidAndPregnant object = realm.copyToRealm(new KidAndPregnant(breastFeeding, dum, plannedPregnancy,
                weeks, previous, childBirth, homeCare));
        realm.commitTransaction();
        realm.close();
        return object;
    }
}
